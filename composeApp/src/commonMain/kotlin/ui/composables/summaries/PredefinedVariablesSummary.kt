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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import luaexperiment.composeapp.generated.resources.*
import luaexperiment.composeapp.generated.resources.Res
import luaexperiment.composeapp.generated.resources.cancel_button
import luaexperiment.composeapp.generated.resources.variable_name_label
import luaexperiment.composeapp.generated.resources.variable_type_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun PredefinedVariablesSummary(
    values: List<SummaryLineValues>,
    onDismiss: () -> Unit,
    insertVariableName: (String) -> Unit
) {
    val cancelString = stringResource(Res.string.cancel_button)
    val variableNameString = stringResource(Res.string.variable_name_label)
    val variableType = stringResource(Res.string.variable_type_label)
    val variableDescription = stringResource(Res.string.variable_description_label)
    val headingMessage = stringResource(Res.string.predefined_variables_heading_message)

    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {},
        confirmButton = {
            TextButton(
                onClick = onDismiss,
            ) {
                Text(cancelString)
            }
        },
        text = {
            Column {
                Text(headingMessage)
                Spacer(modifier = Modifier.height(8.dp))
                SummaryTable(
                    modifier = Modifier.fillMaxWidth(),
                    header = listOf(variableNameString, variableType, variableDescription),
                    lines = values,
                    columnSizes = listOf(Weight(1.3f), Weight(1f), Weight(3f)),
                    onCellSelected = { columnIndex, lineValues ->
                        insertVariableName(lineValues[0])
                    },
                    softWrap = true,
                    commonHeight = 40.dp,
                )
            }
        }
    )
}