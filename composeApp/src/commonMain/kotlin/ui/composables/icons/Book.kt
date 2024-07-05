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
    Image(Book, null)
}

private var _Book: ImageVector? = null

val Book: ImageVector
		get() {
			if (_Book != null) {
				return _Book!!
			}
_Book = ImageVector.Builder(
                name = "Book",
                defaultWidth = 800.dp,
                defaultHeight = 800.dp,
                viewportWidth = 64f,
                viewportHeight = 64f
            ).apply {
				group {
					path(
    					fill = SolidColor(Color(0xFFF9EBB2)),
    					fillAlpha = 1.0f,
    					stroke = null,
    					strokeAlpha = 1.0f,
    					strokeLineWidth = 1.0f,
    					strokeLineCap = StrokeCap.Butt,
    					strokeLineJoin = StrokeJoin.Miter,
    					strokeLineMiter = 1.0f,
    					pathFillType = PathFillType.NonZero
					) {
						moveTo(56f, 62f)
						horizontalLineTo(10f)
						curveToRelative(-2.209f, 0f, -4f, -1.791f, -4f, -4f)
						reflectiveCurveToRelative(1.791f, -4f, 4f, -4f)
						horizontalLineToRelative(46f)
						verticalLineTo(62f)
						close()
}
					group {
						path(
    						fill = SolidColor(Color(0xFF45AAB8)),
    						fillAlpha = 1.0f,
    						stroke = null,
    						strokeAlpha = 1.0f,
    						strokeLineWidth = 1.0f,
    						strokeLineCap = StrokeCap.Butt,
    						strokeLineJoin = StrokeJoin.Miter,
    						strokeLineMiter = 1.0f,
    						pathFillType = PathFillType.NonZero
						) {
							moveTo(6f, 4f)
							verticalLineToRelative(49.537f)
							curveTo(7.062f, 52.584f, 8.461f, 52f, 10f, 52f)
							horizontalLineToRelative(2f)
							verticalLineTo(2f)
							horizontalLineTo(8f)
							curveTo(6.896f, 2f, 6f, 2.896f, 6f, 4f)
							close()
}
						path(
    						fill = SolidColor(Color(0xFF45AAB8)),
    						fillAlpha = 1.0f,
    						stroke = null,
    						strokeAlpha = 1.0f,
    						strokeLineWidth = 1.0f,
    						strokeLineCap = StrokeCap.Butt,
    						strokeLineJoin = StrokeJoin.Miter,
    						strokeLineMiter = 1.0f,
    						pathFillType = PathFillType.NonZero
						) {
							moveTo(56f, 2f)
							horizontalLineTo(14f)
							verticalLineToRelative(50f)
							horizontalLineToRelative(42f)
							horizontalLineToRelative(2f)
							verticalLineToRelative(-2f)
							verticalLineTo(4f)
							curveTo(58f, 2.896f, 57.104f, 2f, 56f, 2f)
							close()
}
}
					group {
						path(
    						fill = SolidColor(Color(0xFF394240)),
    						fillAlpha = 1.0f,
    						stroke = null,
    						strokeAlpha = 1.0f,
    						strokeLineWidth = 1.0f,
    						strokeLineCap = StrokeCap.Butt,
    						strokeLineJoin = StrokeJoin.Miter,
    						strokeLineMiter = 1.0f,
    						pathFillType = PathFillType.NonZero
						) {
							moveTo(60f, 52f)
							verticalLineTo(4f)
							curveToRelative(0f, -2.211f, -1.789f, -4f, -4f, -4f)
							horizontalLineTo(8f)
							curveTo(5.789f, 0f, 4f, 1.789f, 4f, 4f)
							verticalLineToRelative(54f)
							curveToRelative(0f, 3.313f, 2.687f, 6f, 6f, 6f)
							horizontalLineToRelative(49f)
							curveToRelative(0.553f, 0f, 1f, -0.447f, 1f, -1f)
							reflectiveCurveToRelative(-0.447f, -1f, -1f, -1f)
							horizontalLineToRelative(-1f)
							verticalLineToRelative(-8f)
							curveTo(59.104f, 54f, 60f, 53.104f, 60f, 52f)
							close()
							moveTo(6f, 4f)
							curveToRelative(0f, -1.104f, 0.896f, -2f, 2f, -2f)
							horizontalLineToRelative(4f)
							verticalLineToRelative(50f)
							horizontalLineToRelative(-2f)
							curveToRelative(-1.539f, 0f, -2.938f, 0.584f, -4f, 1.537f)
							verticalLineTo(4f)
							close()
							moveTo(56f, 62f)
							horizontalLineTo(10f)
							curveToRelative(-2.209f, 0f, -4f, -1.791f, -4f, -4f)
							reflectiveCurveToRelative(1.791f, -4f, 4f, -4f)
							horizontalLineToRelative(46f)
							verticalLineTo(62f)
							close()
							moveTo(56f, 52f)
							horizontalLineTo(14f)
							verticalLineTo(2f)
							horizontalLineToRelative(42f)
							curveToRelative(1.104f, 0f, 2f, 0.896f, 2f, 2f)
							verticalLineToRelative(46f)
							verticalLineToRelative(2f)
							horizontalLineTo(56f)
							close()
}
						path(
    						fill = SolidColor(Color(0xFF394240)),
    						fillAlpha = 1.0f,
    						stroke = null,
    						strokeAlpha = 1.0f,
    						strokeLineWidth = 1.0f,
    						strokeLineCap = StrokeCap.Butt,
    						strokeLineJoin = StrokeJoin.Miter,
    						strokeLineMiter = 1.0f,
    						pathFillType = PathFillType.NonZero
						) {
							moveTo(43f, 26f)
							horizontalLineTo(23f)
							curveToRelative(-0.553f, 0f, -1f, 0.447f, -1f, 1f)
							reflectiveCurveToRelative(0.447f, 1f, 1f, 1f)
							horizontalLineToRelative(20f)
							curveToRelative(0.553f, 0f, 1f, -0.447f, 1f, -1f)
							reflectiveCurveTo(43.553f, 26f, 43f, 26f)
							close()
}
						path(
    						fill = SolidColor(Color(0xFF394240)),
    						fillAlpha = 1.0f,
    						stroke = null,
    						strokeAlpha = 1.0f,
    						strokeLineWidth = 1.0f,
    						strokeLineCap = StrokeCap.Butt,
    						strokeLineJoin = StrokeJoin.Miter,
    						strokeLineMiter = 1.0f,
    						pathFillType = PathFillType.NonZero
						) {
							moveTo(49f, 20f)
							horizontalLineTo(23f)
							curveToRelative(-0.553f, 0f, -1f, 0.447f, -1f, 1f)
							reflectiveCurveToRelative(0.447f, 1f, 1f, 1f)
							horizontalLineToRelative(26f)
							curveToRelative(0.553f, 0f, 1f, -0.447f, 1f, -1f)
							reflectiveCurveTo(49.553f, 20f, 49f, 20f)
							close()
}
						path(
    						fill = SolidColor(Color(0xFF394240)),
    						fillAlpha = 1.0f,
    						stroke = null,
    						strokeAlpha = 1.0f,
    						strokeLineWidth = 1.0f,
    						strokeLineCap = StrokeCap.Butt,
    						strokeLineJoin = StrokeJoin.Miter,
    						strokeLineMiter = 1.0f,
    						pathFillType = PathFillType.NonZero
						) {
							moveTo(23f, 16f)
							horizontalLineToRelative(12f)
							curveToRelative(0.553f, 0f, 1f, -0.447f, 1f, -1f)
							reflectiveCurveToRelative(-0.447f, -1f, -1f, -1f)
							horizontalLineTo(23f)
							curveToRelative(-0.553f, 0f, -1f, 0.447f, -1f, 1f)
							reflectiveCurveTo(22.447f, 16f, 23f, 16f)
							close()
}
}
					path(
    					fill = SolidColor(Color(0xFF231F20)),
    					fillAlpha = 0.2f,
    					stroke = null,
    					strokeAlpha = 0.2f,
    					strokeLineWidth = 1.0f,
    					strokeLineCap = StrokeCap.Butt,
    					strokeLineJoin = StrokeJoin.Miter,
    					strokeLineMiter = 1.0f,
    					pathFillType = PathFillType.NonZero
					) {
						moveTo(6f, 4f)
						verticalLineToRelative(49.537f)
						curveTo(7.062f, 52.584f, 8.461f, 52f, 10f, 52f)
						horizontalLineToRelative(2f)
						verticalLineTo(2f)
						horizontalLineTo(8f)
						curveTo(6.896f, 2f, 6f, 2.896f, 6f, 4f)
						close()
}
}
}.build()
return _Book!!
		}

