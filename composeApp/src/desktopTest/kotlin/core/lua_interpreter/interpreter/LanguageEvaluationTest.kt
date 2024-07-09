package core.lua_interpreter.interpreter

import LuaLexer
import LuaParser
import core.lua_interpreter.PredefinedVariable
import core.lua_interpreter.PredifinedBoolean
import core.lua_interpreter.PredifinedInt
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class LanguageEvaluationTest {

    private val resultsToShow = mutableMapOf<String, Any?>()

    private val errorsToShow = mutableListOf<Exception>()

    @Before
    fun clearVariables() {
        errorsToShow.clear()
        resultsToShow.clear()
    }

    @Test
    fun `swap variables`() {
        val script = """a,b,c = 3,4,5
        a,b,c = b,c,a""".trimIndent()

        executeCode(script)

        assert(errorsToShow.isEmpty())
        assertEquals(4, resultsToShow["a"])
        assertEquals(5, resultsToShow["b"])
        assertEquals(3, resultsToShow["c"])
    }

    @Test
    fun `simple if statement`() {
        val script = "if true then a = 100 elseif false then b = 2 else c = 3 end"

        executeCode(script)

        assert(errorsToShow.isEmpty())
        assertEquals(100, resultsToShow["a"])
    }

    @Test
    fun `complex if statement`() {
        val script = """op = 25
        a,b = 1,2
        if op == 1 then e = a+b elseif op == 2 then f = a-b elseif op == 25 then h = a*b elseif op == 100 then i = -50 else g = a*b end""".trimIndent()

        executeCode(script)

        assert(errorsToShow.isEmpty())
        assertEquals(25, resultsToShow["op"])
        assertEquals(1, resultsToShow["a"])
        assertEquals(2, resultsToShow["b"])
        assertEquals(2, resultsToShow["h"])
        assertEquals(null, resultsToShow["e"])
        assertEquals(null, resultsToShow["f"])
        assertEquals(null, resultsToShow["i"])
        assertEquals(null, resultsToShow["g"])
    }

    @Test
    fun `nested if statement`() {
        val script="""a,b, result = 3,4,100
        if a < 0 then result = a else if b > 25 then e = 2 else e = 25 end end""".trimIndent()

        executeCode(script)

        assert(errorsToShow.isEmpty())
        assertEquals(3, resultsToShow["a"])
        assertEquals(4, resultsToShow["b"])
        assertEquals(25, resultsToShow["e"])
        assertEquals(100, resultsToShow["result"])
    }

    @Test
    fun `if statement without else`() {
        val script = "if true then a = 2 end"

        executeCode(script)

        assert(errorsToShow.isEmpty())
        assertEquals(2, resultsToShow["a"])
    }

    @Test
    fun `injected variables`() {
        val script = """veteranStart = 75
        if age >= veteranStart then veteran = true else veteran = false end
        if female then gift = 100 else gift = 75 end""".trimIndent()
        val injectedVariables = listOf(
            PredifinedInt(name = "age", value = 80),
            PredifinedBoolean(name = "female", value = true),
        )

        executeCode(script = script, predefinedValues = injectedVariables)

        assert(errorsToShow.isEmpty())
        assertEquals(80, resultsToShow["age"])
        assertEquals(true, resultsToShow["female"])
        assertEquals(75, resultsToShow["veteranStart"])
        assertEquals(true, resultsToShow["veteran"])
        assertEquals(100, resultsToShow["gift"])
    }

    @Test
    fun `discarded comment and variable`() {
        val script = """--[[
        This is a simple program in the tiny Lua subset.
        ]]--
        a, b, c = 2, 3 -- c variable will be discarded
        if a > b then result = a else result = b end""".trimIndent()

        executeCode(script)

        assert(errorsToShow.isEmpty())
        assertEquals(2, resultsToShow["a"])
        assertEquals(3, resultsToShow["b"])
        assertEquals(null, resultsToShow["c"])
        assertEquals(3, resultsToShow["result"])


    }

    @Test
    fun `assignment without comma`() {
        val script = "a b c d = e f g h i j k l m n o p q r s t u v w x y z"

        executeCode(script)

        assert(errorsToShow.isNotEmpty())
    }

    @Test
    fun `assignment without variable name`() {
        val script = "a, = 10"

        executeCode(script)

        assert(errorsToShow.isNotEmpty())
    }

    @Test
    fun `undefined variable`() {
        val script = """a, b, c = 2, 3
        if a > c then result = a else result = b end""".trimIndent()

        executeCode(script)

        assert(errorsToShow.isNotEmpty())
    }

    @Test
    fun `unrecognized symbol`() {
        val script = """a, b, c = 2+10, #3
        if a > c si result = a else result = b end""".trimEnd()

        executeCode(script)

        assert(errorsToShow.isNotEmpty())
    }

    @Test
    fun `if statement without end`() {
        val script = """a, b, c = 2+10, 3
        if a > b then result = a else result = b""".trimEnd()

        executeCode(script)

        assert(errorsToShow.isNotEmpty())
    }

    @Test
    fun `elseif clause without then`() {
        val script = """a, b = 2, 3
        if a > b then result = a elseif result = d else result = b end""".trimEnd()

        executeCode(script)

        assert(errorsToShow.isNotEmpty())
    }

    @Test
    fun `if statement has at least one missing statements block`() {
        val script = """a, b = 2, 3
        if a > b then result = a elseif result = d then end""".trimEnd()

        executeCode(script)

        assert(errorsToShow.isNotEmpty())
    }

    @Test
    fun `if statement has at least one invalid statements block`() {
        val script = """a,b = 3,4
        if then a else b end""".trimEnd()

        executeCode(script)

        assert(errorsToShow.isNotEmpty())
    }

    @Test
    fun `if statement has at least a condition which is not a boolean`() {
        val script = """a = 10
        if a and then result = 2 else result = 3 end""".trimEnd()

        executeCode(script)

        assert(errorsToShow.isNotEmpty())
    }

    @Test
    fun `if statement cannot be used as an expression`() {
        val script = "a = if true then result = 15 end"

        executeCode(script)

        assert(errorsToShow.isNotEmpty())
    }

    private fun executeCode(script: String, predefinedValues: List<PredefinedVariable> = listOf()) {
        try {
            val input = CharStreams.fromString(script)
            val lexer = LuaLexer(input)
            lexer.removeErrorListeners()
            lexer.addErrorListener(CustomErrorListener(::handleScriptSyntaxError))
            val tokens = CommonTokenStream(lexer)
            val parser = LuaParser(tokens)
            parser.removeErrorListeners()
            parser.addErrorListener(CustomErrorListener(::handleScriptSyntaxError))
            val tree = parser.start_()
            val visitor = EvalVisitor(predefinedValues)
            visitor.visit(tree)
            if (errorsToShow.isEmpty()) {
                visitor.getVariables().toSortedMap().forEach { current ->
                    resultsToShow[current.key] = current.value
                }
            }
        } catch (e: ParserError) {
            handleParserError(e)
        } catch (e: Exception) {
            handleMiscError(e)
        }
    }

    private fun handleScriptSyntaxError(message: String, token: String, line: Int, column: Int) {
        errorsToShow.add(Exception(message))
    }

    private fun handleParserError(error: ParserError) {
        errorsToShow.add(error)
    }

    private fun handleMiscError(ex: Exception) {
        errorsToShow.add(ex)
    }
}