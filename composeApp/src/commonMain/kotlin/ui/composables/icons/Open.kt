import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
private fun VectorPreview() {
    Image(Open, null)
}

private var _Open: ImageVector? = null

val Open: ImageVector
		get() {
			if (_Open != null) {
				return _Open!!
			}
_Open = ImageVector.Builder(
                name = "Open",
                defaultWidth = 800.dp,
                defaultHeight = 800.dp,
                viewportWidth = 1920f,
                viewportHeight = 1920f
            ).apply {
				path(
    				fill = SolidColor(Color(0xFF000000)),
    				fillAlpha = 1.0f,
    				stroke = null,
    				strokeAlpha = 1.0f,
    				strokeLineWidth = 1.0f,
    				strokeLineCap = StrokeCap.Butt,
    				strokeLineJoin = StrokeJoin.Miter,
    				strokeLineMiter = 1.0f,
    				pathFillType = PathFillType.EvenOdd
				) {
					moveTo(572.501f, 747f)
					lineToRelative(-254.933f, 815.893f)
					lineToRelative(-101.867f, -31.786f)
					lineToRelative(278.507f, -890.774f)
					horizontalLineToRelative(1105.813f)
					verticalLineToRelative(-320f)
					horizontalLineTo(783.808f)
					lineTo(612.181f, 107f)
					horizontalLineTo(0.021f)
					verticalLineToRelative(1546.667f)
					curveToRelative(0f, 88.213f, 71.787f, 160f, 160f, 160f)
					horizontalLineToRelative(1388.054f)
					curveToRelative(75.946f, 0f, 141.973f, -54.08f, 156.906f, -128.64f)
					lineTo(1892.608f, 747f)
					horizontalLineTo(572.501f)
					close()
}
}.build()
return _Open!!
		}

