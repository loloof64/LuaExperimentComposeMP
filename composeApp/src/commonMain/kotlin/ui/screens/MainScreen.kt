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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.lua_interpreter.interpreter.*
import kotlinx.coroutines.launch
import luaexperiment.composeapp.generated.resources.*
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.jetbrains.compose.resources.stringResource
import ui.composables.summaries.ErrorsSummaries
import ui.composables.summaries.ResultSummary
import ui.composables.summaries.SummaryLineValues
import ui.getCursorPosition
import ui.textFieldValueSaver

@Composable
fun MainScreen() {

    var textContent by rememberSaveable(saver = textFieldValueSaver) { mutableStateOf(TextFieldValue()) }
    var saveFilename by rememberSaveable { mutableStateOf("example.txt") }

    var errorsDialogOpened by rememberSaveable { mutableStateOf(false) }
    val errorsToShow by rememberSaveable { mutableStateOf(mutableListOf<SummaryLineValues>()) }
    var resultSummaryOpened by rememberSaveable{ mutableStateOf(false) }
    val resultsToShow by rememberSaveable{ mutableStateOf(mutableListOf<SummaryLineValues>()) }

    val saveErrorString = stringResource(Res.string.save_error)
    val saveSuccessString = stringResource(Res.string.save_success)
    val openErrorString = stringResource(Res.string.open_error)
    val openSuccessString = stringResource(Res.string.open_success)

    val luaMiscErrorString = stringResource(Res.string.parser_error_misc)
    val luaMiscErrorUnknownTokenString = stringResource(Res.string.parser_error_misc_unknown_token)
    val luaUnrecognizedTokenExceptionString = stringResource(Res.string.parser_error_unrecognized_token)
    val luaUndefinedVariableExceptionString = stringResource(Res.string.parser_error_undefined_variable)
    val luaWrongTokenExceptionAlternativesString = stringResource(Res.string.parser_error_wrong_token_alternatives)
    val luaIfStatementMissingAtLeastOneBlockString = stringResource(Res.string.parser_error_missing_statements_block)
    val luaAssignementExceptionString = stringResource(Res.string.parser_error_invalid_assignements)

    val errorEOFString = stringResource(Res.string.parser_error_eof)
    val errorVariableNameString = stringResource(Res.string.parser_error_variable)
    val errorIntegerString = stringResource(Res.string.parser_error_integer)

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val cursorPosition = remember(textContent) {
        val position = getCursorPosition(textContent)
        "${position.line}:${position.column}"
    }


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
        textContent = TextFieldValue(content)
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

    fun getContentToSave(): String = textContent.text

    fun handleTextChange(newValue: TextFieldValue) {
        textContent = newValue
    }

    fun handleParserError(error: ParserError) {
        val description = when (error) {
            is UndefinedVariableException -> luaUndefinedVariableExceptionString.format(error.getFaultySource())
            is MissingSomeStatementBlocksInIfExpressionException -> luaIfStatementMissingAtLeastOneBlockString
            is InvalidAssignementStatementException -> luaAssignementExceptionString
        }.replace("<EOF>", errorEOFString)
            .replace("NAME", errorVariableNameString)
            .replace("INT", errorIntegerString)
        val position = "${error.getStartLine()}:${error.getStartColumn() + 1}"

        errorsToShow.add(listOf(position, description))
    }

    fun handleScriptSyntaxError(message: String, token: String, line: Int, column: Int) {
        val description = when {
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
                luaWrongTokenExceptionAlternativesString.format(token, expectedToken)
            }

            message.contains("missing NAME at ") -> {
                luaAssignementExceptionString
            }

            message.contains("missing '") -> {
                val messageParts = message.split("missing '")
                val expectedToken = messageParts.drop(1).first().split("'").first()
                luaWrongTokenExceptionAlternativesString.format(token, expectedToken)
            }

            message.contains("token recognition error at") -> {
                luaUnrecognizedTokenExceptionString.format(token)
            }

            else -> luaMiscErrorString.format(token)
        }.replace("<EOF>", errorEOFString)
            .replace("NAME", errorVariableNameString)
            .replace("INT", errorIntegerString)
        val position = "$line:$column"

        errorsToShow.add(listOf(position, description))
    }

    fun handleMiscError(ex: Exception) {
        errorsToShow.add(listOf("", luaMiscErrorUnknownTokenString))
        ex.printStackTrace()
    }

    fun executeScript() {
        errorsToShow.clear()
        resultsToShow.clear()

        try {
            val input = CharStreams.fromString(textContent.text)
            val lexer = LuaLexer(input)
            lexer.removeErrorListeners()
            lexer.addErrorListener(CustomErrorListener(::handleScriptSyntaxError))
            val tokens = CommonTokenStream(lexer)
            val parser = LuaParser(tokens)
            parser.removeErrorListeners()
            parser.addErrorListener(CustomErrorListener(::handleScriptSyntaxError))
            val tree = parser.start_()
            val visitor = EvalVisitor()
            visitor.visit(tree)
            if (errorsToShow.isNotEmpty()) {
                errorsDialogOpened = true
            } else {
                visitor.getVariables().toSortedMap().forEach{current ->
                    resultsToShow.add(listOf(current.key, current.value.toString()))
                }
                resultSummaryOpened = true
            }
        } catch (e: ParserError) {
            handleParserError(e)
            errorsDialogOpened = true
        } catch (e: Exception) {
            handleMiscError(e)
            errorsDialogOpened = true
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
        Column(
            modifier = Modifier.padding(it).fillMaxSize(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top
        ) {
            TextField(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                value = textContent,
                onValueChange = ::handleTextChange
            )
            Text(cursorPosition, modifier = Modifier.fillMaxWidth().padding(4.dp), textAlign = TextAlign.End)
        }

        if (errorsDialogOpened) {
            ErrorsSummaries(onDismiss = { errorsDialogOpened = false }, values = errorsToShow)
        }

        if (resultSummaryOpened) {
            ResultSummary(onDismiss = { resultSummaryOpened = false}, values = resultsToShow)
        }
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