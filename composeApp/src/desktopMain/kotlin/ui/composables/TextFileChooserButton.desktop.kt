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
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*
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
    var currentPath by rememberSaveable {
        mutableStateOf<String?>(
            readExplorerPathFromProperties() ?: System.getProperty("user.home")
        )
    }
    IconButton(onClick = {
        openSaveTextFileChooser(
            suggestedName = getSuggestedFilename(),
            title = dialogTitle,
            currentPath = File(currentPath ?: System.getProperty("user.home")),
            content = getContentToSave(),
            onSuccess = { newFileName, newPath ->
                currentPath = newPath.absolutePath
                registerExplorerPathIntoProperties(newPath.parentFile.absolutePath)
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
    var currentPath by rememberSaveable {
        mutableStateOf<String?>(
            readExplorerPathFromProperties() ?: System.getProperty("user.home")
        )
    }

    IconButton(onClick = {
        openLoadTextFileChooser(
            title = dialogTitle,
            // we give a dummy file name, so that the file chooser will point to the right directory
            currentPath = File(currentPath ?: System.getProperty("user.home"), "example.txt"),
            onSuccess = { content, newPath ->
                currentPath = newPath.absolutePath
                registerExplorerPathIntoProperties(newPath.parentFile.absolutePath)
                onSuccess(content)
            },
            onError = onError,
        )
    }) {
        buttonIcon()
    }
}

private fun readExplorerPathFromProperties(): String? {
    val path = getPropertiesFilePath()
    File(path).parentFile.mkdirs()
    File(path).createNewFile()
    val properties = Properties()
    FileInputStream(path).use {
        properties.load(it)
    }
    return properties.getProperty("saveFileExplorerPath")
}

private fun registerExplorerPathIntoProperties(newPath: String) {
    val path = getPropertiesFilePath()
    File(path).parentFile.mkdirs()
    File(path).createNewFile()

    val properties = Properties()
    properties.setProperty("saveFileExplorerPath", newPath)


    FileOutputStream(path).use {
        properties.store(it, "")
    }
}

private fun getPropertiesFilePath(): String {
    val homePath = System.getProperty("user.home")
    val separator = System.getProperty("file.separator")
    val basePath = when (System.getProperty("os.name")) {
        "Linux" -> "$homePath$separator.config"
        "Windows" -> "$homePath${separator}AppData${separator}Local"
        else -> throw Exception("OS ${System.getProperty("os.name")} is not supported for this Jetpack Compose Desktop project.")
    }
    return "$basePath${separator}loloof64${separator}LuaExperiment${separator}config.properties"
}

private fun openSaveTextFileChooser(
    title: String,
    suggestedName: String,
    content: String,
    currentPath: File,
    onSuccess: (String, File) -> Unit,
    onError: (Exception) -> Unit
) {
    /////////////////////////////////////////
    println("Save => ${currentPath.absolutePath}")
    ///////////////////////////////////////////
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
    /////////////////////////////////////////
    println("Open => ${currentPath.absolutePath}")
    ///////////////////////////////////////////
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