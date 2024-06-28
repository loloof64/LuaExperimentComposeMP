package core.lua_interpreter.interpreter

import LuaBaseListener
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
        onError(
            msg ?: "#Unknown message#",
            if (offendingSymbol == null || offendingSymbol !is Token) "#Unknown token#" else offendingSymbol.text,
            line,
            charPositionInLine
        )
    }

    /*
    override fun reportContextSensitivity(
        recognizer: Parser?,
        dfa: DFA?,
        startIndex: Int,
        stopIndex: Int,
        prediction: Int,
        configs: ATNConfigSet?
    ) {
        recognizer?.let { parser ->
            val tokenStream = parser.tokenStream
            val startToken = tokenStream.get(startIndex)
            val stopToken = tokenStream.get(stopIndex)

            val tokenText = tokenStream.getText(Interval(startToken.startIndex, stopToken.stopIndex))
            val line = startToken.line
            val charPositionInLine = startToken.charPositionInLine

            onError(
                "Context sensitivity",
                tokenText,
                line,
                charPositionInLine
            )
        }
    }

    override fun reportAttemptingFullContext(
        recognizer: Parser?,
        dfa: DFA?,
        startIndex: Int,
        stopIndex: Int,
        conflictingAlts: BitSet?,
        configs: ATNConfigSet?
    ) {
        recognizer?.let { parser ->
            val tokenStream = parser.tokenStream
            val startToken = tokenStream.get(startIndex)
            val stopToken = tokenStream.get(stopIndex)

            val tokenText = tokenStream.getText(Interval(startToken.startIndex, stopToken.stopIndex))
            val line = startToken.line
            val charPositionInLine = startToken.charPositionInLine

            onError(
                "Attempting full context",
                tokenText,
                line,
                charPositionInLine
            )
        }
    }
    */
}

sealed class ParserError(open val context: ParserRuleContext) : Exception() {
    fun getStartLine(): Int = context.start.line
    fun getEndLine(): Int = context.stop.line
    fun getStartColumn(): Int = context.start.charPositionInLine
    fun getEndColumn(): Int = context.stop.charPositionInLine + context.stop.text.length

    fun getFaultySource(): String = context.text
}

data class UndefinedVariableException(override val context: ParserRuleContext) : ParserError(context)

class EvalListener : LuaBaseListener() {

    private val variables = mutableMapOf<String, Any>()
    private val tempAssignementNames = mutableListOf<String>()
    private val tempAssignementValues = mutableListOf<Any>()

    fun getVariables() = variables.toMap()

    override fun exitAssign(ctx: LuaParser.AssignContext?) {
        if (ctx == null) return

        val nameList = ctx.namelist()
        val expList = ctx.explist()

        for (i in 0 until nameList.NAME().size) {
            val variableName = nameList.NAME(i).text
            if (expList.exp().size <= i) break
            val exp = expList.exp(i)
            val value = evaluateExpression(exp)
            variables[variableName] = value
        }
    }

    private fun evaluateExpression(ctx: LuaParser.ExpContext): Any {
        return when {
            ctx.FALSE() != null -> false
            ctx.TRUE() != null -> true
            ctx.number() != null -> {
                try {
                    ctx.number()!!.text.toInt()
                } catch (e: NumberFormatException) {
                    throw Exception("Invalid number ${ctx.number()!!.text} !")
                }
            }

            ctx.prefixexp() != null -> {
                val value = ctx.prefixexp()
                when {
                    value?.NAME() != null -> {
                        if (variables.containsKey(value.NAME().text)) {
                            variables[value.NAME().text]!!
                        } else {
                            throw UndefinedVariableException(ctx)
                        }
                    }

                    value?.exp() != null -> evaluateExpression(value.exp())
                    else -> throw Exception("Unrecognized value type !")
                }
            }

            ctx.exp()?.size == 2 -> {
                val left = ctx.exp(0)?.let { evaluateExpression(it) }
                val right = ctx.exp(1)?.let { evaluateExpression(it) }

                if (left == null || right == null) throw Exception("Value could not be parsed !")

                when {
                    ctx.CARET() != null -> (left as Int).toDouble().pow(right as Int).toInt()

                    ctx.PLUS() != null -> (left as Int) + (right as Int)
                    ctx.MINUS() != null -> (left as Int) - (right as Int)
                    ctx.STAR() != null -> (left as Int) * (right as Int)
                    ctx.SLASH() != null -> (left as Int) / (right as Int)
                    ctx.PER() != null -> (left as Int) % (right as Int)
                    ctx.SS() != null -> (left as Int) / (right as Int)

                    ctx.LT() != null -> (left as Int) < (right as Int)
                    ctx.GT() != null -> (left as Int) > (right as Int)
                    ctx.LE() != null -> (left as Int) <= (right as Int)
                    ctx.GE() != null -> (left as Int) >= (right as Int)
                    ctx.EE() != null -> (left as Int) == (right as Int)
                    ctx.SQEQ() != null -> (left as Int) != (right as Int)

                    ctx.AND() != null -> (left as Boolean) && (right as Boolean)
                    ctx.OR() != null -> (left as Boolean) || (right as Boolean)

                    ctx.AMP() != null -> (left as Int) and (right as Int)
                    ctx.PIPE() != null -> (left as Int) or (right as Int)
                    ctx.SQUIG() != null -> (left as Int) xor (right as Int)
                    ctx.LL() != null -> (left as Int) shl (right as Int)
                    ctx.GG() != null -> (left as Int) shr (right as Int)


                    else -> throw Exception("Unrecognized binary operator !")
                }
            }

            ctx.exp()?.size == 1 -> {
                val value = ctx.exp(0)
                if (value == null) throw Exception("Value could not be parsed !")
                when {
                    ctx.NOT() != null -> !(evaluateExpression(value) as Boolean)
                    ctx.MINUS() != null -> -(evaluateExpression(value) as Int)
                    ctx.SQUIG() != null -> (evaluateExpression(value) as Int).inv()
                    else -> throw Exception("Unrecognized unary operator !")
                }
            }

            else -> throw Exception("Unrecognized value type !")
        }
    }

    override fun enterNamelist(ctx: LuaParser.NamelistContext?) {
        if (ctx == null) return

        tempAssignementNames.clear()
        tempAssignementValues.clear()

        ctx.NAME().forEach {
            tempAssignementNames.add(it.text)
        }
    }

    override fun exitExplist(ctx: LuaParser.ExplistContext?) {
        if (ctx == null) return
        ctx.exp().forEach { expresion ->
            tempAssignementValues.add(evaluateExpression(expresion))
        }
    }

    override fun exitIfstat(ctx: LuaParser.IfstatContext?) {
        if (ctx == null) return

        var conditionMet = false

        if (evaluateCondition(ctx.exp(0))) {
            executeBlock(ctx.block(0))
            conditionMet = true
        } else {
            for (i in 0 until ctx.ELSEIF().size) {
                if (evaluateCondition(ctx.exp(i + 1))) {
                    executeBlock(ctx.block(i + 1))
                    conditionMet = true
                    break
                }
            }
        }
        if (!conditionMet && ctx.ELSE() != null) {
            executeBlock(ctx.block(ctx.block().size - 1))
        }
    }

    private fun evaluateCondition(ctx: LuaParser.ExpContext): Boolean {
        val result = evaluateExpression(ctx)
        if (result is Boolean) return result
        return true
    }

    private fun executeBlock(ctx: LuaParser.BlockContext) {
        for (stat in ctx.stat()) {
            if (stat.assign() != null) {
                exitAssign(stat.assign())
            } else if (stat.ifstat() != null) {
                exitIfstat(stat.ifstat())
            }
        }
    }
}