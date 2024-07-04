package ui.composables.summaries

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import luaexperiment.composeapp.generated.resources.Res
import luaexperiment.composeapp.generated.resources.ok_button
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorsSummaries(values: List<SummaryLineValues>, onDismiss: () -> Unit) {

    val okString = stringResource(Res.string.ok_button)

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        dismissButton = {
            TextButton(
                onClick = onDismiss,
            ) {
                Text(okString)
            }
        },
        text = {
            SummaryTable(
                modifier = Modifier.fillMaxWidth(),
                header = listOf("Position", "Description"),
                lines = values,
                columnSizes = listOf(Fixed(80.dp), Weight(1f))
            )
        }
    )
}