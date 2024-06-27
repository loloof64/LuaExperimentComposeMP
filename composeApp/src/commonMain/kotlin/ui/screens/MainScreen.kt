package ui.screens

import Execute
import LuaLexer
import LuaParser
import Open
import Save
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.lua_interpreter.interpreter.EvalListener
import kotlinx.coroutines.launch
import luaexperiment.composeapp.generated.resources.*
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.jetbrains.compose.resources.stringResource
import services.ShowOpenTextFileChooserButton
import services.ShowSaveTextFileChooserButton

@Composable
fun MainScreen() {
    var textContent by rememberSaveable { mutableStateOf("") }
    var saveFilename by rememberSaveable { mutableStateOf("example.txt") }

    val saveErrorString = stringResource(Res.string.save_error)
    val saveSuccessString = stringResource(Res.string.save_success)
    val openErrorString = stringResource(Res.string.open_error)
    val openSuccessString = stringResource(Res.string.open_success)

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

    fun executeScript() {
        try {
            val input = CharStreams.fromString(textContent)
            val lexer = LuaLexer(input)
            val tokens = CommonTokenStream(lexer)
            val parser = LuaParser(tokens)
            val tree = parser.start_()
            val walker = ParseTreeWalker()
            val listener = EvalListener()
            walker.walk(listener, tree)
            println(listener.getVariables())
        } catch (e: Exception) {
            e.printStackTrace()
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