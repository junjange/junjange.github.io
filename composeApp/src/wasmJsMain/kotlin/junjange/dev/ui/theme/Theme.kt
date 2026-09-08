package junjange.dev.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val lightColorTheme =
    lightColorScheme(
        primary = Purple,
        onPrimary = White,
        primaryContainer = White,
        onPrimaryContainer = Grey900,
        secondaryContainer = Grey100,
        onSecondaryContainer = Grey900,
        surfaceVariant = White,
        onSurfaceVariant = Grey600,
        outlineVariant = Grey200,
        secondary = Purple,
        onSecondary = White,
        background = White,
        onBackground = Grey900,
        surface = White,
        onSurface = Grey900,
    )

val darkColorTheme =
    darkColorScheme(
        primary = Purple,
        onPrimary = White,
        primaryContainer = DarkGray,
        onPrimaryContainer = White,
        secondaryContainer = Gray,
        onSecondaryContainer = White,
        surfaceVariant = Gray,
        onSurfaceVariant = Grey300,
        outlineVariant = DarkOutline,
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
