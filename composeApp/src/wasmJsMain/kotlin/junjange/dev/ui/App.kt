package junjange.dev.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import junjange.dev.ui.screen.HomeScreen
import junjange.dev.ui.theme.JUNJNAGETheme

@Composable
fun App() {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    var themeMode by remember { mutableStateOf(if (isSystemInDarkTheme) ThemeMode.Dark else ThemeMode.Light) }
    var screenSize by remember { mutableStateOf(ScreenSize()) }

    Layout(
        content = {
            AppContent(
                screenSize = screenSize,
                themeMode = themeMode,
                onThemeChanged = { mode -> themeMode = mode },
            )
        },
        measurePolicy = { measurables, constraints ->
            val width = constraints.maxWidth
            val height = constraints.maxHeight

            screenSize = ScreenSize(width, height)

            val placeables = measurePlaceables(measurables, constraints)

            layout(width, height) {
                placePlaceables(placeables)
            }
        },
    )
}

fun measurePlaceables(
    measurables: List<Measurable>,
    constraints: Constraints,
): List<Placeable> = measurables.map { measurable -> measurable.measure(constraints) }

fun Placeable.PlacementScope.placePlaceables(placeables: List<Placeable>) {
    var yPosition = 0
    placeables.forEach { placeable ->
        placeable.placeRelative(x = 0, y = yPosition)
        yPosition += placeable.height
    }
}

@Composable
fun AppContent(
    screenSize: ScreenSize,
    themeMode: ThemeMode,
    onThemeChanged: (ThemeMode) -> Unit,
) {
    CompositionLocalProvider(
        LocalScreenSize provides screenSize,
        LocalThemeMode provides themeMode,
    ) {
        JUNJNAGETheme(isDarkTheme = themeMode == ThemeMode.Dark) {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                onThemeChanged = onThemeChanged,
            )
        }
    }
}

const val PC_CONTENT_HORIZONTAL_PADDING = 128
const val PC_CONTENT_VERTICAL_PADDING = 72
const val PC_CONTENT_WIDTH = 1280

const val MOBILE_CONTENT_HORIZONTAL_PADDING = 24
const val MOBILE_CONTENT_VERTICAL_PADDING = 48
