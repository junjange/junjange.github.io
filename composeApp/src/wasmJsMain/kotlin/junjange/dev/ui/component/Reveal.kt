package junjange.dev.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.dp
import junjange.dev.ui.model.LocalScreenSize

@Composable
fun Modifier.reveal(delayMillis: Int = 0): Modifier {
    val screenHeight = LocalScreenSize.current.height
    var visible by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = REVEAL_DURATION, delayMillis = delayMillis, easing = FastOutSlowInEasing),
        label = "reveal",
    )

    return this
        .onGloballyPositioned { coordinates ->
            if (!visible && (screenHeight <= 0 || coordinates.positionInWindow().y < screenHeight * REVEAL_THRESHOLD)) {
                visible = true
            }
        }.graphicsLayer {
            alpha = progress
            translationY = (1f - progress) * REVEAL_OFFSET.toPx()
        }
}

private const val REVEAL_DURATION = 700
private const val REVEAL_THRESHOLD = 0.92f
private val REVEAL_OFFSET = 40.dp
