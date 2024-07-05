package ui.composables.summaries

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

typealias SummaryLineValues = List<String>

sealed class SummaryColumnSize

/*
Its size will be computed based on remaining space and the other weights.
 */
data class Weight(val value: Float) : SummaryColumnSize()

/*
Its size will always be the given size.
 */
data class Fixed(val value: Dp) : SummaryColumnSize()

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SummaryTable(
    modifier: Modifier = Modifier,
    header: SummaryLineValues?,
    lines: List<SummaryLineValues>,
    columnSizes: List<SummaryColumnSize>,
    commonHeight: Dp = 35.dp,
    softWrap: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onCellSelected: (Int, SummaryLineValues) -> Unit = { _columnIndex, _content: SummaryLineValues -> }
) {
    LazyColumn(modifier = modifier) {
        if (header != null) {
            stickyHeader {
                SummaryHeader(header = header, columnSizes = columnSizes)
            }
        }

        items(lines) { currentLine ->
            SummaryLine(
                content = currentLine, columnSizes = columnSizes, onCellSelected = onCellSelected, softWrap = softWrap,
                overflow = overflow, commonHeight = commonHeight,
            )
        }
    }
}

@Composable
fun SummaryHeader(
    modifier: Modifier = Modifier,
    header: SummaryLineValues,
    columnSizes: List<SummaryColumnSize>,
    backgroundColor: Color = MaterialTheme.colors.primary,
    textColor: Color = MaterialTheme.colors.onPrimary,
    linesColor: Color = MaterialTheme.colors.secondary,
    commonHeight: Dp = 20.dp,
) {
    SummaryContentBase(
        modifier = modifier,
        content = header,
        columnSizes = columnSizes,
        backgroundColor = backgroundColor,
        textColor = textColor,
        linesColor = linesColor,
        contentAlignment = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        commonHeight = commonHeight,
    )
}

@Composable
fun SummaryLine(
    modifier: Modifier = Modifier,
    content: SummaryLineValues,
    columnSizes: List<SummaryColumnSize>,
    backgroundColor: Color = MaterialTheme.colors.onPrimary,
    textColor: Color = MaterialTheme.colors.primary,
    linesColor: Color = MaterialTheme.colors.secondary,
    commonHeight: Dp = 35.dp,
    softWrap: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onCellSelected: (Int, SummaryLineValues) -> Unit = { _columnIndex, _content: SummaryLineValues -> }
) {
    SummaryContentBase(
        modifier = modifier,
        content = content,
        columnSizes = columnSizes,
        backgroundColor = backgroundColor,
        textColor = textColor,
        linesColor = linesColor,
        contentAlignment = TextAlign.Start,
        fontWeight = FontWeight.Medium,
        commonHeight = commonHeight,
        softWrap = softWrap,
        overflow = overflow,
        onCellSelected = onCellSelected,
    )
}

@Composable
private fun SummaryContentBase(
    modifier: Modifier = Modifier,
    content: SummaryLineValues,
    columnSizes: List<SummaryColumnSize>,
    contentAlignment: TextAlign,
    backgroundColor: Color,
    textColor: Color,
    fontWeight: FontWeight,
    linesColor: Color,
    commonHeight: Dp,
    softWrap: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onCellSelected: (Int, SummaryLineValues) -> Unit = { _columnIndex, _content: SummaryLineValues -> }
) {
    Row(modifier = modifier) {
        for (i in content.indices) {
            val currentText = content[i]
            val baseModifier = when (val size = columnSizes[i]) {
                is Weight -> Modifier.weight(size.value)
                is Fixed -> Modifier.width(size.value)
            }
            Surface(
                modifier = baseModifier,
                color = backgroundColor,
                border = BorderStroke(3.dp, linesColor)
            ) {
                Text(
                    modifier = Modifier.padding(6.dp).height(commonHeight).pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            onCellSelected(i, content)
                        })
                    },
                    text = currentText,
                    color = textColor,
                    fontWeight = fontWeight,
                    textAlign = contentAlignment,
                    softWrap = softWrap,
                    overflow = overflow
                )
            }
        }
    }
}

@Preview
@Composable
fun SummaryTablePreview() {
    val columnSizes = listOf(Fixed(80.dp), Weight(0.3f), Weight(1f))
    val header = listOf("Name", "Age", "Gender")
    val lines = listOf(
        listOf("My name is CPU", "20", "I don't have a genre as I am a CPU !"),
        listOf("Linda", "60", "Female"),
        listOf("John", "35", "Male"),
        listOf("Emma", "28", "Female"),
        listOf("Alex", "42", "Non-binary"),
        listOf("Sophia", "55", "Female"),
        listOf("Michael", "30", "Male"),
        listOf("Olivia", "22", "Female"),
        listOf("Daniel", "48", "Male"),
        listOf("Ava", "33", "Female"),
        listOf("William", "39", "Male"),
        listOf("Mia", "26", "Female"),
        listOf("James", "52", "Male"),
        listOf("Charlotte", "31", "Female"),
        listOf("Ethan", "29", "Male"),
        listOf("Amelia", "37", "Female"),
        listOf("Benjamin", "45", "Male"),
        listOf("Harper", "24", "Female"),
        listOf("Lucas", "41", "Male"),
        listOf("Evelyn", "36", "Female"),
        listOf("Noah", "27", "Male"),
        listOf("Isabella", "34", "Female"),
        listOf("Mason", "50", "Male"),
        listOf("Sophia", "23", "Female"),
        listOf("Logan", "38", "Male"),
        listOf("Aria", "29", "Female"),
        listOf("Jackson", "44", "Male"),
        listOf("Zoe", "32", "Female"),
        listOf("Liam", "47", "Male"),
        listOf("Lily", "25", "Female")
    )

    SummaryTable(
        header = header,
        lines = lines,
        columnSizes = columnSizes,
    )
}

@Preview
@Composable
fun SummaryHeaderPreview() {
    val header = listOf("Name", "Age", "Gender")
    val columnSizes = listOf(Fixed(80.dp), Weight(0.3f), Weight(1f))


    SummaryHeader(
        header = header,
        columnSizes = columnSizes,
    )
}

@Preview
@Composable
fun SummaryLinePreview() {
    val line = listOf("My name is CPU", "20", "I don't have a genre as I am a CPU !")
    val columnSizes = listOf(Fixed(80.dp), Weight(0.3f), Weight(1f))


    SummaryLine(
        content = line,
        columnSizes = columnSizes,
    )
}