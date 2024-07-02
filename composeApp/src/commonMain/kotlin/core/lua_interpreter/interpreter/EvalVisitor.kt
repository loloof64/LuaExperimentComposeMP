package core.lua_interpreter.interpreter

import LuaBaseVisitor
import LuaParser
import org.antlr.v4.runtime.*
import java.lang.IllegalArgumentException
import kotlin.math.pow

data class CustomErrorListener(val onError: (String, String, Int, Int) -> Unit) : BaseErrorListener() {
    override fun syntaxError(
        recognizer: Recognizer<*, *>?,
        offendingSymbol: Any?,
        line: Int,
        charPositionInLine: Int,
        msg: String?,
        e: RecognitionException?
    ) {
        if (recognizer is Parser) {
            onError(
                msg ?: "#Unknown message#",
                if (offendingSymbol == null || offendingSymbol !is Token) "#Unknown token#" else offendingSymbol.text,
                line,
                charPositionInLine + 1
            )
        } else {
            val tokenDelimitersInMsg = msg?.withIndex()?.filter { it.value == '\'' }?.map { it.index }?.takeLast(2)
            val token = tokenDelimitersInMsg?.let {
                msg.substring(tokenDelimitersInMsg[0] + 1, tokenDelimitersInMsg[1])
            } ?: "#Unknown token#"
            onError(
                msg ?: "#Unknown message#",
                token,
                line,
                charPositionInLine + 1
            )
        }
    }
}

sealed class ParserError(open val context: ParserRuleContext) : Exception() {
    fun getStartLine(): Int = context.start.line
    fun getStartColumn(): Int = context.start.charPositionInLine

    fun getFaultySource(): String = context.text
}

data class UndefinedVariableException(override val context: ParserRuleContext) : ParserError(context)
data class MissingSomeStatementBlocksInIfExpressionException(override val context: ParserRuleContext) :
    ParserError(context)

data class InvalidAssignementStatementException(override val context: ParserRuleContext) : ParserError(context)

val no_else_if_validated = Double.NEGATIVE_INFINITY

class EvalVisitor : LuaBaseVisitor<Any?>() {
    
    fun getVariables() = variables.toMap()

    override fun visitAssign(ctx: LuaParser.AssignContext?): Any? {
        if (ctx == null) throw IllegalArgumentException("ctx is null !")
        
        val expListValue = ctx.explist()
        val thereIsNoExpression = expListValue.isEmpty
        if (thereIsNoExpression) throw InvalidAssignementStatementException(ctx)
        
        // we must first evaluate all values from expressions list
        val values = visitExplist(expListValue) as List<Any?>
        
        // then we can attributes values to variables
        val names = visitNamelist(ctx.namelist()) as List<String>
        for (i in 0 until names.size) {
            val variableName = names[i]
            variables[variableName] = if (values.size >= i+1) values[i] else null
        }
        
        return null
    }

    override fun visitNamelist(ctx: LuaParser.NamelistContext?): Any? {
        return ctx?.NAME()?.map {
            it.text
        }
    }

    override fun visitExplist(ctx: LuaParser.ExplistContext?): Any? {
        return ctx?.exp()?.map {
            visit(it)
        }
    }

    override fun visitIfstat(ctx: LuaParser.IfstatContext?): Any? {
        val conditionMet = visit(ctx?.ifCond) as Boolean
        if (conditionMet) return visit(ctx?.normalExec)
        else {
            val elseIfResult = visit(ctx?.elseIfAlts())
            return if (elseIfResult != no_else_if_validated) elseIfResult else visit(ctx?.elseStat())
        }
    }

    override fun visitElseIfAlts(ctx: LuaParser.ElseIfAltsContext?): Any? {
        for (i in 0 until (ctx?.elseIfCond?.childCount ?: 0)) {
            val conditionMet = visit(ctx?.elseIfCond?.getChild(i)) as Boolean
            if (conditionMet) {
                return visit(ctx?.elseIfExec?.getChild(i))
            }
        }
        return no_else_if_validated
    }

    override fun visitElseStat(ctx: LuaParser.ElseStatContext?): Any? {
        return visit(ctx?.endExec)
    }

    override fun visitTrueExpr(ctx: LuaParser.TrueExprContext?): Any? {
        return true
    }

    override fun visitFalseExpr(ctx: LuaParser.FalseExprContext?): Any? {
        return false
    }


    override fun visitExponentExpr(ctx: LuaParser.ExponentExprContext?): Any? {
        val left = visit(ctx?.exp(0)) as Int
        val right = visit(ctx?.exp(1)) as Int
        
        return left.toDouble().pow(right.toDouble()).toInt()
    }

    override fun visitUnaryExpr(ctx: LuaParser.UnaryExprContext?): Any? {
        val value = visit(ctx?.exp())
        val operator = ctx?.op?.type ?: -1
        
        return when (operator) {
            LuaParser.NOT -> !(value as Boolean)
            LuaParser.MINUS -> -(value as Int)
            else -> throw  Exception("Unrecognized unary operator '$operator'")
        }
    }

    override fun visitMulDivModuloExpr(ctx: LuaParser.MulDivModuloExprContext?): Any? {
        val left = visit(ctx?.exp(0)) as Int
        val right = visit(ctx?.exp(1)) as Int
        val operator = ctx?.op?.type ?: -1
        
        return  when (operator) {
            LuaParser.STAR -> left * right
            LuaParser.SLASH -> left / right
            LuaParser.PER -> left % right
            LuaParser.SS -> left / right
            else -> throw  Exception("Unrecognized mulDivModulo operator '$operator'")
        }
    }

    override fun visitPlusMinusExpr(ctx: LuaParser.PlusMinusExprContext?): Any? {
        val left = visit(ctx?.exp(0)) as Int
        val right = visit(ctx?.exp(1)) as Int
        val operator = ctx?.op?.type ?: -1
        
        return when (operator) {
            LuaParser.PLUS -> left + right
            LuaParser.MINUS -> left - right
            else -> throw  Exception("Unrecognized plusMinus operator '$operator'")
        }
    }

    override fun visitBooleanBinaryLogicalExpr(ctx: LuaParser.BooleanBinaryLogicalExprContext?): Any? {
        val left = visit(ctx?.exp(0)) as Int
        val right = visit(ctx?.exp(1)) as Int
        val operator = ctx?.op?.type ?: -1
        
        return when (operator) {
            LuaParser.LT -> left < right
            LuaParser.GT -> left > right
            LuaParser.LE -> left <= right
            LuaParser.GE -> left >= right
            LuaParser.EE -> left == right
            LuaParser.SQEQ -> left != right
            else -> throw  Exception("Unrecognized boolean binary operator '$operator'")
        }
    }

    override fun visitBooleanAndExpr(ctx: LuaParser.BooleanAndExprContext?): Any? {
        val left = visit(ctx?.exp(0)) as Boolean
        val right = visit(ctx?.exp(1)) as Boolean

        return left && right
    }

    override fun visitBooleanOrExpr(ctx: LuaParser.BooleanOrExprContext?): Any? {
        val left = visit(ctx?.exp(0)) as Boolean
        val right = visit(ctx?.exp(1)) as Boolean

        return left || right
    }

    override fun visitIntBinaryLogicalExpr(ctx: LuaParser.IntBinaryLogicalExprContext?): Any? {
        val left = visit(ctx?.exp(0)) as Int
        val right = visit(ctx?.exp(1)) as Int
        val operator = ctx?.op?.type ?: -1
        return when (operator) {
            LuaParser.AMP -> left and right
            LuaParser.PIPE -> left or right
            LuaParser.SQUIG -> left xor right
            LuaParser.LL -> left shl right
            LuaParser.GG -> left shr right
            else -> throw Exception("Unrecognized integer binary operator '$operator'")
        }
    }

    override fun visitVariablePrefix(ctx: LuaParser.VariablePrefixContext?): Any? {
        return variables[ctx?.text]
    }

    override fun visitParenthesisPrefix(ctx: LuaParser.ParenthesisPrefixContext?): Any? {
        return visit(ctx?.exp())
    }

    override fun visitIntegerValue(ctx: LuaParser.IntegerValueContext?): Any? {
        return ctx?.text?.toInt()
    }

    private val variables = mutableMapOf<String, Any?>()
}