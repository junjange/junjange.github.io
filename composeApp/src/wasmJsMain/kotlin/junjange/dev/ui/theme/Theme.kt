package junjange.dev.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val lightColorTheme =
    lightColorScheme(
        primary = Black,
        onPrimary = White,
        primaryContainer = White,
        onPrimaryContainer = DarkGray,
        secondaryContainer = DarkGray,
        onSecondaryContainer = White,
        surfaceVariant = White,
        secondary = Purple,
        onSecondary = White,
        background = White,
        onBackground = DarkGray,
        surface = Gray,
        onSurface = White,
    )

val darkColorTheme =
    darkColorScheme(
        primary = White,
        onPrimary = White,
        primaryContainer = DarkGray,
        onPrimaryContainer = White,
        secondaryContainer = Gray,
        onSecondaryContainer = White,
        surfaceVariant = Gray,
        secondary = Purple,
        onSecondary = White,
        background = DarkGray,
        onBackground = White,
        surface = DarkGray,
        onSurface = White,
    )

@Composable
fun JUNJNAGETheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (isDarkTheme) darkColorTheme else lightColorTheme,
        typography = PretendardTypography(),
        content = content,
    )
}
