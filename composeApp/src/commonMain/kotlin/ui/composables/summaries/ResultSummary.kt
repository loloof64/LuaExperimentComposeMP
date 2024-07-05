package ui.composables.summaries

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import luaexperiment.composeapp.generated.resources.*
import luaexperiment.composeapp.generated.resources.Res
import luaexperiment.composeapp.generated.resources.ok_button
import luaexperiment.composeapp.generated.resources.variable_name_label
import luaexperiment.composeapp.generated.resources.variable_value_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun ResultSummary(values: List<SummaryLineValues>, onDismiss: () -> Unit) {

    val okString = stringResource(Res.string.ok_button)
    val variableNameString = stringResource(Res.string.variable_name_label)
    val variableValueString = stringResource(Res.string.variable_value_label)
    val headingMessage = stringResource(Res.string.results_heading_message)

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
            Column {
                Text(headingMessage)
                Spacer(modifier = Modifier.height(8.dp))
                SummaryTable(
                    modifier = Modifier.fillMaxWidth(),
                    header = listOf(variableNameString, variableValueString),
                    lines = values,
                    columnSizes = listOf(Weight(1f), Weight(1f)),
                )
            }

        }
    )
}