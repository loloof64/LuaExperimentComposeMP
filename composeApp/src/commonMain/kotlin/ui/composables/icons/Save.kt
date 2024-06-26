import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
private fun VectorPreview() {
    Image(Save, null)
}

private var _Save: ImageVector? = null

val Save: ImageVector
		get() {
			if (_Save != null) {
				return _Save!!
			}
_Save = ImageVector.Builder(
                name = "Save",
                defaultWidth = 800.dp,
                defaultHeight = 800.dp,
                viewportWidth = 21f,
                viewportHeight = 21f
            ).apply {
				group {
					group(
						scaleX = 1f,
						scaleY = 1f,
						translationX = -419f,
						translationY = -640f,
						pivotX = 0f,
						pivotY = 0f,
) {
						group(
							scaleX = 1f,
							scaleY = 1f,
							translationX = 56f,
							translationY = 160f,
							pivotX = 0f,
							pivotY = 0f,
) {
							path(
    							fill = SolidColor(Color(0xFF000000)),
    							fillAlpha = 1.0f,
    							stroke = null,
    							strokeAlpha = 1.0f,
    							strokeLineWidth = 1f,
    							strokeLineCap = StrokeCap.Butt,
    							strokeLineJoin = StrokeJoin.Miter,
    							strokeLineMiter = 1.0f,
    							pathFillType = PathFillType.EvenOdd
							) {
								moveTo(370.21875f, 484f)
								curveTo(370.2188f, 483.448f, 370.6891f, 483f, 371.2688f, 483f)
								curveTo(371.8483f, 483f, 372.3188f, 483.448f, 372.3188f, 484f)
								curveTo(372.3188f, 484.552f, 371.8483f, 485f, 371.2688f, 485f)
								curveTo(370.6891f, 485f, 370.2188f, 484.552f, 370.2188f, 484f)
								lineTo(370.21875f, 484f)
								close()
								moveTo(381.9f, 497f)
								curveTo(381.9f, 497.552f, 381.4296f, 498f, 380.85f, 498f)
								lineTo(379.8f, 498f)
								lineTo(379.8f, 494f)
								curveTo(379.8f, 492.895f, 378.8603f, 492f, 377.7f, 492f)
								lineTo(369.3f, 492f)
								curveTo(368.1397f, 492f, 367.2f, 492.895f, 367.2f, 494f)
								lineTo(367.2f, 498f)
								lineTo(366.15f, 498f)
								curveTo(365.5704f, 498f, 365.1f, 497.552f, 365.1f, 497f)
								lineTo(365.1f, 487.044f)
								curveTo(365.1f, 486.911f, 365.1556f, 486.784f, 365.2533f, 486.691f)
								lineTo(367.2f, 484.837f)
								lineTo(367.2f, 486f)
								curveTo(367.2f, 487.105f, 368.1397f, 488f, 369.3f, 488f)
								lineTo(377.7f, 488f)
								curveTo(378.8603f, 488f, 379.8f, 487.105f, 379.8f, 486f)
								lineTo(379.8f, 482f)
								lineTo(380.85f, 482f)
								curveTo(381.4296f, 482f, 381.9f, 482.448f, 381.9f, 483f)
								lineTo(381.9f, 497f)
								close()
								moveTo(377.7f, 498f)
								lineTo(369.3f, 498f)
								lineTo(369.3f, 495f)
								curveTo(369.3f, 494.448f, 369.7704f, 494f, 370.35f, 494f)
								lineTo(376.65f, 494f)
								curveTo(377.2296f, 494f, 377.7f, 494.448f, 377.7f, 495f)
								lineTo(377.7f, 498f)
								close()
								moveTo(369.3f, 482.837f)
								lineTo(370.17885f, 482f)
								lineTo(377.7f, 482f)
								lineTo(377.7f, 485f)
								curveTo(377.7f, 485.552f, 377.2296f, 486f, 376.65f, 486f)
								lineTo(370.35f, 486f)
								curveTo(369.7704f, 486f, 369.3f, 485.552f, 369.3f, 485f)
								lineTo(369.3f, 482.837f)
								close()
								moveTo(381.9f, 480f)
								lineTo(369.7347f, 480f)
								curveTo(369.4565f, 480f, 369.1898f, 480.105f, 368.9923f, 480.293f)
								lineTo(363.30765f, 485.707f)
								curveTo(363.1103f, 485.895f, 363f, 486.149f, 363f, 486.414f)
								lineTo(363f, 498f)
								curveTo(363f, 499.105f, 363.9398f, 500f, 365.1f, 500f)
								lineTo(381.9f, 500f)
								curveTo(383.0602f, 500f, 384f, 499.105f, 384f, 498f)
								lineTo(384f, 482f)
								curveTo(384f, 480.895f, 383.0602f, 480f, 381.9f, 480f)
								lineTo(381.9f, 480f)
								close()
}
}
}
}
}.build()
return _Save!!
		}

