package junjange.dev.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.roundToInt

data class ScreenSize(
    val width: Int = 0,
    val height: Int = 0,
)

@Composable
@ReadOnlyComposable
fun ScreenSize.toDpSize(): ScreenSize {
    val density = LocalDensity.current.density
    val widthInDp = (this.width / density).roundToInt()
    val heightInDp = (this.height / density).roundToInt()

    return ScreenSize(widthInDp, heightInDp)
}

val LocalScreenSize = compositionLocalOf { ScreenSize() }
