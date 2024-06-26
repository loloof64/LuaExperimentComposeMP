package services

import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import luaexperiment.composeapp.generated.resources.Res
import luaexperiment.composeapp.generated.resources.open_dialog_title
import luaexperiment.composeapp.generated.resources.save_dialog_title
import org.jetbrains.compose.resources.stringResource
import java.io.File
import javax.swing.JFileChooser
import javax.swing.SwingUtilities
import javax.swing.filechooser.FileNameExtensionFilter

@Composable
actual fun ShowSaveTextFileChooserButton(
    buttonIcon: @Composable () -> Unit,
    getContentToSave: () -> String,
    getSuggestedFilename: () -> String,
    onSuccess: (String) -> Unit,
    onError: (Exception) -> Unit
) {
    val dialogTitle = stringResource(Res.string.save_dialog_title)
    var currentPath by rememberSaveable { mutableStateOf(File(System.getProperty("user.home"))) }
    IconButton(onClick = {
        openSaveTextFileChooser(
            suggestedName = getSuggestedFilename(),
            title = dialogTitle,
            currentPath = currentPath,
            content = getContentToSave(),
            onSuccess = { newFileName, newPath ->
                currentPath = newPath
                onSuccess(newFileName)
            },
            onError = onError
        )
    }) {
        buttonIcon()
    }
}

@Composable
actual fun ShowOpenTextFileChooserButton(
    buttonIcon: @Composable () -> Unit,
    onSuccess: (String) -> Unit,
    onError: (Exception) -> Unit
) {
    val dialogTitle = stringResource(Res.string.open_dialog_title)
    var currentPath by rememberSaveable { mutableStateOf(File(System.getProperty("user.home"))) }

    IconButton(onClick = {
        openLoadTextFileChooser(
            title = dialogTitle,
            currentPath = currentPath,
            onSuccess = { content, newPath ->
                currentPath = newPath
                onSuccess(content)
            },
            onError = onError,
        )
    }) {
        buttonIcon()
    }
}

private fun openSaveTextFileChooser(
    title: String,
    suggestedName: String,
    content: String,
    currentPath: File,
    onSuccess: (String, File) -> Unit,
    onError: (Exception) -> Unit
) {
    SwingUtilities.invokeLater {
        try {
            val extFilter = FileNameExtensionFilter(
                "Text files", "txt"
            )
            val fileChooser = JFileChooser().apply {
                fileSelectionMode = JFileChooser.FILES_ONLY
                dialogTitle = title
                fileFilter = extFilter
                selectedFile = File(currentPath, suggestedName)
            }

            val result = fileChooser.showSaveDialog(null)
            if (result == JFileChooser.APPROVE_OPTION) {
                val selectedFile = fileChooser.selectedFile
                selectedFile.writeText(content)
                onSuccess(selectedFile.name, selectedFile.parentFile)
            }
        } catch (e: Exception) {
            onError(e)
        }
    }
}

private fun openLoadTextFileChooser(
    title: String,
    currentPath: File,
    onSuccess: (String, File) -> Unit,
    onError: (Exception) -> Unit
) {
    SwingUtilities.invokeLater {
        try {
            val extFilter = FileNameExtensionFilter(
                "Text files", "txt"
            )
            val fileChooser = JFileChooser().apply {
                fileSelectionMode = JFileChooser.FILES_ONLY
                dialogTitle = title
                fileFilter = extFilter
                selectedFile = currentPath
            }

            val result = fileChooser.showOpenDialog(null)
            if (result == JFileChooser.APPROVE_OPTION) {
                val selectedFile = fileChooser.selectedFile
                onSuccess(selectedFile.readText(), selectedFile)
            }
        } catch (e: Exception) {
            onError(e)
        }
    }
}