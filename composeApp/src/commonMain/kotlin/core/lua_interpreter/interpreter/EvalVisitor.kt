package core.lua_interpreter.interpreter

import LuaBaseVisitor
import LuaParser
import org.antlr.v4.runtime.*
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

class EvalVisitor : LuaBaseVisitor<Any?>() {

    fun getVariables() = variables.toMap()

    override fun visitAssign(ctx: LuaParser.AssignContext?): Any? {
        if (ctx == null) throw NullPointerException()

        val expListValue = ctx.explist()
        val thereIsNoExpression = expListValue.isEmpty
        if (thereIsNoExpression) throw InvalidAssignementStatementException(ctx)

        // we must first evaluate all values from expressions list
        val values = visitExplist(expListValue) as List<Any?>

        // then we can attribute values to variables
        val names = visitNamelist(ctx.namelist()) as List<String>
        for (i in names.indices) {
            val variableName = names[i]
            variables[variableName] = if (values.size >= i + 1) values[i] else null
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
        if (ctx == null) throw NullPointerException()
        val conditions = ctx.exp()
        val blocks = ctx.block()

        for (i in conditions.indices) {
            if (evaluateCondition(conditions[i])) {
                return visit(blocks[i])
            }
        }

        // Si aucune condition n'est vraie et qu'il y a un bloc else
        if (blocks.size > conditions.size) {
            return visit(blocks.last())
        }

        return null
    }


    override fun visitTrueExpr(ctx: LuaParser.TrueExprContext?): Any? {
        return true
    }

    override fun visitFalseExpr(ctx: LuaParser.FalseExprContext?): Any? {
        return false
    }


    override fun visitExponentExpr(ctx: LuaParser.ExponentExprContext?): Any? {
        if (ctx == null) throw NullPointerException()

        val left = ctx.exp(0).let { visit(it) } as Int?
        val right = ctx.exp(1).let { visit(it) } as Int?

        if (left == null || right == null) throw UndefinedVariableException(ctx)

        return left.toDouble().pow(right.toDouble()).toInt()
    }

    override fun visitUnaryExpr(ctx: LuaParser.UnaryExprContext?): Any? {
        if (ctx == null) throw NullPointerException()

        val value = ctx.exp().let { visit(it) } as Any?
        val operator = ctx.op?.type ?: -1

        return when (operator) {
            LuaParser.NOT -> !(value as Boolean)
            LuaParser.MINUS -> -(value as Int)
            else -> throw Exception("Unrecognized unary operator '$operator'")
        }
    }

    override fun visitMulDivModuloExpr(ctx: LuaParser.MulDivModuloExprContext?): Any? {
        if (ctx == null) throw NullPointerException()

        val left = ctx.exp(0).let { visit(it) } as Int?
        val right = ctx.exp(1).let { visit(it) } as Int?
        val operator = ctx.op?.type ?: -1

        if (left == null || right == null) throw UndefinedVariableException(ctx)


        return when (operator) {
            LuaParser.STAR -> left * right
            LuaParser.SLASH -> left / right
            LuaParser.PER -> left % right
            LuaParser.SS -> left / right
            else -> throw Exception("Unrecognized mulDivModulo operator '$operator'")
        }
    }

    override fun visitPlusMinusExpr(ctx: LuaParser.PlusMinusExprContext?): Any? {
        if (ctx == null) throw NullPointerException()

        val left = ctx.exp(0).let { visit(it) } as Int?
        val right = ctx.exp(1).let { visit(it) } as Int?
        val operator = ctx.op?.type ?: -1

        if (left == null || right == null) throw UndefinedVariableException(ctx)

        return when (operator) {
            LuaParser.PLUS -> left + right
            LuaParser.MINUS -> left - right
            else -> throw Exception("Unrecognized plusMinus operator '$operator'")
        }
    }

    override fun visitBooleanBinaryLogicalExpr(ctx: LuaParser.BooleanBinaryLogicalExprContext?): Any? {
        if (ctx == null) throw NullPointerException()

        val left = ctx.exp(0).let { visit(it) } as Int?
        val right = ctx.exp(1).let { visit(it) } as Int?
        val operator = ctx.op?.type ?: -1

        if (left == null || right == null) throw UndefinedVariableException(ctx)

        return when (operator) {
            LuaParser.LT -> left < right
            LuaParser.GT -> left > right
            LuaParser.LE -> left <= right
            LuaParser.GE -> left >= right
            LuaParser.EE -> left == right
            LuaParser.SQEQ -> left != right
            else -> throw Exception("Unrecognized boolean binary operator '$operator'")
        }
    }

    override fun visitBooleanAndExpr(ctx: LuaParser.BooleanAndExprContext?): Any? {
        if (ctx == null) throw NullPointerException()

        val left = ctx.exp(0).let { visit(it) } as Boolean?
        val right = ctx.exp(1).let { visit(it) } as Boolean?

        if (left == null || right == null) throw UndefinedVariableException(ctx)

        return left && right
    }

    override fun visitBooleanOrExpr(ctx: LuaParser.BooleanOrExprContext?): Any? {
        if (ctx == null) throw NullPointerException()

        val left = ctx.exp(0).let { visit(it) } as Boolean?
        val right = ctx.exp(1).let { visit(it) } as Boolean?

        if (left == null || right == null) throw UndefinedVariableException(ctx)

        return left || right
    }

    override fun visitIntBinaryLogicalExpr(ctx: LuaParser.IntBinaryLogicalExprContext?): Any? {
        if (ctx == null) throw NullPointerException()

        val left = ctx.exp(0).let { visit(it) } as Int?
        val right = ctx.exp(1).let { visit(it) } as Int?
        val operator = ctx.op?.type ?: -1

        if (left == null || right == null) throw UndefinedVariableException(ctx)
        
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

    private fun evaluateCondition(ctx: LuaParser.ExpContext?): Boolean {
        return visit(ctx) as? Boolean ?: false
    }

    private val variables = mutableMapOf<String, Any?>()
}