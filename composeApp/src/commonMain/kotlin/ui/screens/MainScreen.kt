package ui.screens

import Execute
import LuaLexer
import LuaParser
import Open
import Save
import ShowOpenTextFileChooserButton
import ShowSaveTextFileChooserButton
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.lua_interpreter.interpreter.*
import kotlinx.coroutines.launch
import luaexperiment.composeapp.generated.resources.*
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.jetbrains.compose.resources.*

@Composable
fun MainScreen() {

    var textContent by rememberSaveable { mutableStateOf("") }
    var saveFilename by rememberSaveable { mutableStateOf("example.txt") }

    val saveErrorString = stringResource(Res.string.save_error)
    val saveSuccessString = stringResource(Res.string.save_success)
    val openErrorString = stringResource(Res.string.open_error)
    val openSuccessString = stringResource(Res.string.open_success)

    val luaParserErrorLocationString = stringResource(Res.string.parser_error_location)
    val luaMiscErrorString = stringResource(Res.string.parser_error_misc)
    val luaUnrecognizedTokenExceptionString = stringResource(Res.string.parser_error_unrecognized_token)
    val luaWrongTokenExceptionString = stringResource(Res.string.parser_error_wrong_token)
    val luaUndefinedVariableExceptionString = stringResource(Res.string.parser_error_undefined_variable)
    val luaWrongTokenExceptionAlternativesString = stringResource(Res.string.parser_error_wrong_token_alternatives)

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    fun handleSaveSuccess(newFilename: String) {
        saveFilename = newFilename
        scope.launch {
            snackbarHostState.showSnackbar(saveSuccessString)
        }
    }

    fun handleSaveError(error: Exception) {
        error.printStackTrace()
        scope.launch {
            snackbarHostState.showSnackbar(saveErrorString)
        }
    }

    fun handleOpenSuccess(content: String) {
        textContent = content
        scope.launch {
            snackbarHostState.showSnackbar(openSuccessString)
        }
    }

    fun handleOpenError(error: Exception) {
        error.printStackTrace()
        scope.launch {
            snackbarHostState.showSnackbar(openErrorString)
        }
    }

    fun getSuggestedSaveFilename(): String = saveFilename

    fun getContentToSave(): String = textContent

    fun handleTextChange(newValue: String) {
        textContent = newValue
    }

    fun handleParserError(error: ParserError) {
        val messagePart1 = when (error) {
            is UndefinedVariableException -> luaUndefinedVariableExceptionString.format(error.getFaultySource())
        }
        val messagePart2 = luaParserErrorLocationString.format(
            error.getStartLine(),
            error.getStartColumn(),
        )
        println("$messagePart2 => $messagePart1")
    }

    fun handleScriptSyntaxError(message: String, token: String, line: Int, column: Int) {
        val messagePart1 = when {
            message.contains("mismatched input") -> {
                val messageParts = message.split("expecting ")
                val expectedToken = messageParts[1]
                luaWrongTokenExceptionAlternativesString.format(token, expectedToken)
            }

            message.contains("extraneous input") -> {
                val messagePartsV1 = message.split("expecting '") // for misc tokens
                val messagePartsV2 = message.split("expecting ") // for <EOF>
                val expectedToken = if (messagePartsV1.size > 1) messagePartsV1.drop(1).first().dropLast(1)
                else messagePartsV2.drop(1).first()
                luaWrongTokenExceptionString.format(token, expectedToken)
            }

            message.contains("missing") -> {
                val messageParts = message.split("missing '")
                val expectedToken = messageParts.drop(1).first().split("'").first()
                luaWrongTokenExceptionString.format(token, expectedToken)
            }

            message.contains("token recognition error at") -> {
                luaUnrecognizedTokenExceptionString.format(token)
            }

            else -> luaMiscErrorString.format(token)
        }
        val messagePart2 = luaParserErrorLocationString.format(
            line,
            column,
        )
        println("$messagePart2 => $messagePart1")
    }

    fun executeScript() {
        try {
            val input = CharStreams.fromString(textContent)
            val lexer = LuaLexer(input)
            lexer.removeErrorListeners()
            lexer.addErrorListener(CustomErrorListener(::handleScriptSyntaxError))
            val tokens = CommonTokenStream(lexer)
            val parser = LuaParser(tokens)
            parser.removeErrorListeners()
            parser.addErrorListener(CustomErrorListener(::handleScriptSyntaxError))
            val tree = parser.start_()
            val walker = ParseTreeWalker()
            val listener = EvalListener()
            walker.walk(listener, tree)
            println(listener.getVariables())
        } catch (e: ParserError) {
            handleParserError(e)
        }
    }

    Scaffold(topBar = {
        AppBar(
            getSuggestedSaveFileName = ::getSuggestedSaveFilename,
            getContentToSave = ::getContentToSave,
            onSaveSuccess = ::handleSaveSuccess,
            onSaveError = ::handleSaveError,
            onOpenSuccess = ::handleOpenSuccess,
            onOpenError = ::handleOpenError,
            executeScript = ::executeScript,
        )
    }) {
        TextField(
            modifier = Modifier.padding(it).fillMaxSize(),
            value = textContent,
            onValueChange = ::handleTextChange
        )
    }
}

@Composable
fun AppBar(
    getSuggestedSaveFileName: () -> String,
    getContentToSave: () -> String,
    onSaveSuccess: (String) -> Unit,
    onSaveError: (Exception) -> Unit,
    onOpenSuccess: (String) -> Unit,
    onOpenError: (Exception) -> Unit,
    executeScript: () -> Unit = {},
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth().padding(4.dp), backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        contentPadding = PaddingValues(4.dp),
        elevation = 5.dp,
    ) {
        Text(text = "Lua experiment")
        Spacer(modifier = Modifier.weight(1f))
        ShowSaveTextFileChooserButton(
            buttonIcon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Save,
                    contentDescription = stringResource(Res.string.save)
                )
            },
            getSuggestedFilename = { getSuggestedSaveFileName() },
            getContentToSave = { getContentToSave() },
            onSuccess = { onSaveSuccess(it) },
            onError = { onSaveError(it) },
        )
        ShowOpenTextFileChooserButton(
            buttonIcon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Open,
                    contentDescription = stringResource(Res.string.open)
                )
            },
            onSuccess = { onOpenSuccess(it) },
            onError = { onOpenError(it) },
        )
        IconButton(
            onClick = executeScript,
        ) {
            Icon(
                imageVector = Execute,
                modifier = Modifier.size(24.dp),
                contentDescription = stringResource(Res.string.execute)
            )
        }
    }
}