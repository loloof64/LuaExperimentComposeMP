import androidx.compose.runtime.Composable

@Composable
expect fun ShowSaveTextFileChooserButton(
    buttonIcon: @Composable () -> Unit,
    getContentToSave: () -> String,
    getSuggestedFilename: () -> String,
    onSuccess: (String) -> Unit,
    onError: (Exception) -> Unit
)

@Composable
expect fun ShowOpenTextFileChooserButton(
    buttonIcon: @Composable () -> Unit,
    onSuccess: (String) -> Unit,
    onError: (Exception) -> Unit
)