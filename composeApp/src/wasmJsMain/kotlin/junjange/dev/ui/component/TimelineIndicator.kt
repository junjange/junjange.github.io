package junjange.dev.ui.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TimelineIndicator(
    isFirst: Boolean,
    frontHeight: Dp,
    dotSize: Dp,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier =
                Modifier
                    .width(2.dp)
                    .height(frontHeight)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(if (isFirst) 0f else 0.5f),
                        shape = CircleShape,
                    ),
        )

        Spacer(Modifier.height(8.dp))

        Box(
            modifier =
                Modifier
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary.copy(0.5f),
                        shape = CircleShape,
                    ).size(dotSize),
            contentAlignment = Alignment.Center,
        ) {
            // 현재(최상단) 경력: 진행 중을 나타내는 파동 애니메이션
            if (isFirst) {
                val transition = rememberInfiniteTransition(label = "pulse")
                val scale by transition.animateFloat(
                    initialValue = 0.6f,
                    targetValue = 2.4f,
                    animationSpec =
                        infiniteRepeatable(
                            animation = tween(durationMillis = 1600, easing = LinearEasing),
                            repeatMode = RepeatMode.Restart,
                        ),
                    label = "pulseScale",
                )
                val pulseAlpha by transition.animateFloat(
                    initialValue = 0.5f,
                    targetValue = 0f,
                    animationSpec =
                        infiniteRepeatable(
                            animation = tween(durationMillis = 1600, easing = LinearEasing),
                            repeatMode = RepeatMode.Restart,
                        ),
                    label = "pulseAlpha",
                )

                Box(
                    modifier =
                        Modifier
                            .size(dotSize)
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                alpha = pulseAlpha
                            }.background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = CircleShape,
                            ),
                )
            }

            Box(
                modifier =
                    Modifier
                        .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                        .size(6.dp),
            )
        }
        Spacer(Modifier.height(8.dp))

        Box(
            modifier =
                Modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(0.5f),
                        shape = CircleShape,
                    ),
        )
    }
}
