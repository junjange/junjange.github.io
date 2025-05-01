package junjange.dev.ui

import androidx.compose.runtime.compositionLocalOf
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.ic_dark_mode
import junjange_dev.composeapp.generated.resources.ic_light_mode
import org.jetbrains.compose.resources.DrawableResource

enum class ThemeMode(
    val iconRes: DrawableResource,
) {
    Light(Res.drawable.ic_light_mode),
    Dark(Res.drawable.ic_dark_mode),
    ;

    fun toggle(): ThemeMode =
        when (this) {
            Light -> Dark
            Dark -> Light
        }
}

val LocalThemeMode = compositionLocalOf { ThemeMode.Light }
