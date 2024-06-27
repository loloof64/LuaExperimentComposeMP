package core.lua_interpreter.interpreter

import LuaBaseListener
import LuaParser
import kotlin.math.pow

data class UnrecognizedValueTypeException(val value: String) : Exception()

data class InvalidNumberException(val value: String) : Exception()

data class UnrecognizedUnaryExpressionException(val value: String) : Exception()

data class UnrecognizedBinaryExpressionException(val left: String, val right: String) : Exception()

data class UndefinedVariableException(val variableName: String) : Exception()

class EvalListener : LuaBaseListener() {
    
    private val variables = mutableMapOf<String, Any>()
    private val tempAssignementNames  = mutableListOf<String>()
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
                  throw InvalidNumberException(ctx.number()!!.text) 
                }
            }
            ctx.prefixexp() != null -> {
                val value = ctx.prefixexp()
                when {
                    value?.NAME() != null -> {
                        if (variables.containsKey(value.NAME().text)) {
                            variables[value.NAME().text]!!
                        } else {
                            throw UndefinedVariableException(value.NAME().text)
                        }
                    }
                    value?.exp() != null -> evaluateExpression(value.exp())
                    else -> throw UnrecognizedValueTypeException(value?.text ?: "")
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
                    
                    
                    else -> throw UnrecognizedBinaryExpressionException(ctx.exp(0)?.text ?: "", ctx.exp(1)?.text ?: "")
                }
            }
            ctx.exp()?.size == 1 -> {
                val value = ctx.exp(0)
                if (value == null) throw Exception("Value could not be parsed !")
                when {
                    ctx.NOT() != null -> !(evaluateExpression(value) as Boolean)
                    ctx.MINUS() != null -> -(evaluateExpression(value) as Int)
                    ctx.SQUIG() != null -> (evaluateExpression(value) as Int).inv()
                    else -> throw UnrecognizedUnaryExpressionException(ctx.exp(0)?.text ?: "")
                }
            }
            else -> throw UnrecognizedValueTypeException(ctx.text)
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
                if (evaluateCondition(ctx.exp(i+1))) {
                    executeBlock(ctx.block(i+1))
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
            }
            else if (stat.ifstat() != null) {
                exitIfstat(stat.ifstat())
            }
        }
    }
}