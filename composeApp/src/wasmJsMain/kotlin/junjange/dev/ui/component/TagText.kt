package junjange.dev.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun TagText(
    text: String,
    textColor: Color,
    fontWeight: FontWeight,
    fontSize: TextUnit,
    height: Dp,
    color: Color,
    modifier: Modifier = Modifier,
    inverse: Boolean = false,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier =
                Modifier
                    .height(height)
                    .aspectRatio(5f / 1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (inverse) {
                Canvas(modifier = Modifier.height(height).width(height / 2), onDraw = {
                    val trianglePath =
                        Path().apply {
                            moveTo(0f, size.height / 2f)
                            lineTo(size.height / 2f, size.height)
                            lineTo(size.height / 2f, 0f)
                            close()
                        }
                    drawPath(
                        path = trianglePath,
                        color = color,
                    )
                })
            }
            Box(
                modifier =
                    Modifier
                        .background(color = color)
                        .fillMaxHeight()
                        .weight(1f),
            )
            if (inverse.not()) {
                Canvas(modifier = Modifier.height(height), onDraw = {
                    val trianglePath =
                        Path().apply {
                            moveTo(size.height / 2f, size.height / 2f)
                            lineTo(0f, size.height)
                            lineTo(0f, 0f)
                            close()
                        }
                    drawPath(
                        path = trianglePath,
                        color = color,
                    )
                })
            }
        }
        Text(
            text = text,
            color = textColor,
            fontWeight = fontWeight,
            fontSize = fontSize,
            modifier = Modifier.padding(start = height / 4, bottom = height / 8),
        )
    }
}
