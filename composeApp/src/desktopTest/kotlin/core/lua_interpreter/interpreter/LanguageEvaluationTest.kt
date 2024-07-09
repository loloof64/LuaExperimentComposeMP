package core.lua_interpreter.interpreter

import LuaLexer
import LuaParser
import core.lua_interpreter.PredefinedVariable
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.jetbrains.compose.resources.stringResource
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
    fun testSwapVariables() {
        val script = """a,b,c = 3,4,5
        a,b,c = b,c,a""".trimIndent()

        executeCode(script)

        assert(errorsToShow.isEmpty())
        assertEquals(4, resultsToShow["a"])
        assertEquals(5, resultsToShow["b"])
        assertEquals(3, resultsToShow["c"])
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