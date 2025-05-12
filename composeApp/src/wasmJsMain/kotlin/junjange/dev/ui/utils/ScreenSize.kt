package junjange.dev.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.roundToInt

data class ScreenSize(
    val width: Int = 0,
    val height: Int = 0,
)

val LocalScreenSize = compositionLocalOf { ScreenSize() }

@Composable
@ReadOnlyComposable
fun ScreenSize.asDp(): ScreenSize {
    val density = LocalDensity.current.density
    return ScreenSize(
        width = (width / density).roundToInt(),
        height = (height / density).roundToInt(),
    )
}
