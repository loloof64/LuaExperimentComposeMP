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
    Image(Execute, null)
}

private var _Execute: ImageVector? = null

val Execute: ImageVector
		get() {
			if (_Execute != null) {
				return _Execute!!
			}
_Execute = ImageVector.Builder(
                name = "Execute",
                defaultWidth = 800.dp,
                defaultHeight = 800.dp,
                viewportWidth = 100f,
                viewportHeight = 100f
            ).apply {
				path(
    				fill = SolidColor(Color(0xFF555555)),
    				fillAlpha = 1.0f,
    				stroke = SolidColor(Color(0xFF000000)),
    				strokeAlpha = 1.0f,
    				strokeLineWidth = 1.5f,
    				strokeLineCap = StrokeCap.Butt,
    				strokeLineJoin = StrokeJoin.Miter,
    				strokeLineMiter = 1.0f,
    				pathFillType = PathFillType.NonZero
				) {
					moveTo(43f, 2f)
					lineToRelative(-2f, 10f)
					lineToRelative(-11f, 5f)
					lineToRelative(-8f, -6f)
					lineToRelative(-10f, 10f)
					lineToRelative(6f, 9f)
					lineToRelative(-4f, 11f)
					lineToRelative(-11f, 2f)
					lineToRelative(0f, 13f)
					lineToRelative(11f, 2f)
					lineToRelative(4f, 10f)
					lineToRelative(-6f, 10f)
					lineToRelative(10f, 10f)
					lineToRelative(9f, -6f)
					lineToRelative(10f, 4f)
					lineToRelative(2f, 11f)
					lineToRelative(14f, 0f)
					lineToRelative(2f, -12f)
					lineToRelative(8f, -4f)
					lineToRelative(10f, 7f)
					lineTo(87f, 79f)
					lineTo(80f, 68f)
					lineTo(85f, 59f)
					lineTo(97f, 57f)
					lineTo(97f, 43f)
					lineTo(85f, 41f)
					lineTo(82f, 31f)
					lineTo(88f, 21f)
					lineTo(78f, 11f)
					lineTo(69f, 17f)
					lineTo(58f, 12f)
					lineTo(56f, 2f)
					close()
					moveToRelative(6f, 20f)
					curveTo(63f, 22f, 75f, 34f, 75f, 48f)
					curveTo(75f, 63f, 63f, 74f, 49f, 74f)
					curveTo(35f, 74f, 23f, 63f, 23f, 48f)
					curveTo(23f, 34f, 35f, 22f, 49f, 22f)
					close()
}
}.build()
return _Execute!!
		}

