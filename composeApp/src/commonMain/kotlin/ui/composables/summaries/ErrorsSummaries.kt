package ui.composables.summaries

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import luaexperiment.composeapp.generated.resources.Res
import luaexperiment.composeapp.generated.resources.error_description_label
import luaexperiment.composeapp.generated.resources.error_position_label
import luaexperiment.composeapp.generated.resources.ok_button
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorsSummaries(values: List<SummaryLineValues>, onDismiss: () -> Unit) {

    val okString = stringResource(Res.string.ok_button)
    val positionString = stringResource(Res.string.error_position_label)
    val descriptionString = stringResource(Res.string.error_description_label)
    var errorDetailsDialogOpened by rememberSaveable { mutableStateOf(false) }
    var errorDetailsMessage by rememberSaveable { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {},
        confirmButton = {
            TextButton(
                onClick = onDismiss,
            ) {
                Text(okString)
            }
        },
        text = {
            SummaryTable(
                modifier = Modifier.fillMaxWidth(),
                header = listOf(positionString, descriptionString),
                lines = values,
                columnSizes = listOf(Fixed(80.dp), Weight(1f)),
                onCellSelected = {columnIndex, lineContent ->
                    if (columnIndex == 1) {
                        errorDetailsMessage = "${lineContent.first()}\n${lineContent.last()}"
                        errorDetailsDialogOpened = true
                    }
                }
            )
        }
    )

    if (errorDetailsDialogOpened) {
        AlertDialog(
            onDismissRequest = { errorDetailsDialogOpened = false },
            dismissButton = {},
            confirmButton = {
                TextButton(onClick = { errorDetailsDialogOpened = false }) {
                    Text(okString)
                }
            }, text = {
                Text(text = errorDetailsMessage, modifier = Modifier.verticalScroll(rememberScrollState()))
            }
        )
    }
}