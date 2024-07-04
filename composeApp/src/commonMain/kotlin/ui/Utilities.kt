package ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

object TextFieldValueSaverSeparators {
    val topSeparator = "@!1,?#"
}

data class TextFieldCursorPosition(val line: Int, val column: Int, val index: Int)

val textFieldValueSaver = Saver<MutableState<TextFieldValue>, String>(
    save = { mutableState ->
        val value = mutableState.value
        val position = getCursorPosition(value)
        "${value.text}${TextFieldValueSaverSeparators.topSeparator}" +
            "${value.selection.min}${TextFieldValueSaverSeparators.topSeparator}" +
            "${position.index}"
    },
    restore = { serialized ->
        val parts = serialized.split((TextFieldValueSaverSeparators.topSeparator))
        val text = parts[0]
        val cursorPosition = Integer.parseInt(parts[1])

        mutableStateOf(TextFieldValue(text = text, selection = TextRange(cursorPosition)))
    }
)

fun getCursorPosition(textFieldValue: TextFieldValue): TextFieldCursorPosition {
    val lines = textFieldValue.text.split("\n")
    val cursorPosition = textFieldValue.selection.min

    var currentLength = 0
    var line = 1
    var column = cursorPosition + 1

    for (lineText in lines) {
        if (currentLength + lineText.length + 1 > cursorPosition) {
            column = cursorPosition - currentLength + 1
            break
        }
        currentLength += lineText.length + 1
        line++
    }

    return TextFieldCursorPosition(line = line, column = column, index = cursorPosition)
}