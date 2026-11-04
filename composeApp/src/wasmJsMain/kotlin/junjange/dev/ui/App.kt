package junjange.dev.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import junjange.dev.ui.model.LocalScreenSize
import junjange.dev.ui.model.LocalThemeMode
import junjange.dev.ui.model.Route
import junjange.dev.ui.model.ScreenSize
import junjange.dev.ui.model.ThemeMode
import junjange.dev.ui.screen.FeedbackScreen
import junjange.dev.ui.screen.MainScreen
import junjange.dev.ui.theme.JUNJNAGETheme
import kotlinx.browser.window
import org.w3c.dom.events.Event

@Composable
fun App() {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    var themeMode by remember { mutableStateOf(if (isSystemInDarkTheme) ThemeMode.Dark else ThemeMode.Light) }
    var screenSize by remember { mutableStateOf(ScreenSize()) }
    var route by remember { mutableStateOf(Route.fromHash(window.location.hash)) }
    var openedInApp by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        val listener: (Event) -> Unit = { route = Route.fromHash(window.location.hash) }
        window.addEventListener("hashchange", listener)
        onDispose { window.removeEventListener("hashchange", listener) }
    }

    Layout(
        content = {
            AppContent(
                screenSize = screenSize,
                themeMode = themeMode,
                route = route,
                onThemeChanged = { mode -> themeMode = mode },
                onNavigate = { target ->
                    openedInApp = true
                    window.location.hash = target.hash
                },
                onBack = {
                    if (openedInApp) {
                        openedInApp = false
                        window.history.back()
                    } else {
                        window.history.replaceState(null, "", window.location.pathname + window.location.search)
                        route = Route.Main
                    }
                },
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
    route: Route,
    onThemeChanged: (ThemeMode) -> Unit,
    onNavigate: (Route) -> Unit,
    onBack: () -> Unit,
) {
    val mainListState = rememberLazyListState()

    CompositionLocalProvider(
        LocalScreenSize provides screenSize,
        LocalThemeMode provides themeMode,
    ) {
        JUNJNAGETheme(isDarkTheme = themeMode == ThemeMode.Dark) {
            Crossfade(targetState = route, animationSpec = tween(durationMillis = 250), label = "route") { current ->
                when (current) {
                    Route.Main ->
                        MainScreen(
                            listState = mainListState,
                            modifier = Modifier.fillMaxSize(),
                            onThemeChanged = onThemeChanged,
                            onFeedbackClick = { onNavigate(Route.Feedback) },
                        )

                    Route.Feedback ->
                        FeedbackScreen(
                            onBack = onBack,
                            onThemeChanged = onThemeChanged,
                            modifier = Modifier.fillMaxSize(),
                        )
                }
            }
        }
    }
}

const val DESKTOP_CONTENT_WIDTH = 1280

const val CONTENT_MAX_WIDTH = 1120

const val DESKTOP_CONTENT_HORIZONTAL_PADDING = 40
const val DESKTOP_CONTENT_VERTICAL_PADDING = 120

const val TABLET_CONTENT_HORIZONTAL_PADDING = 40
const val TABLET_CONTENT_VERTICAL_PADDING = 96
const val TABLET_CONTENT_MIN_HEIGHT = 768

const val MOBILE_CONTENT_HORIZONTAL_PADDING = 24
const val MOBILE_CONTENT_VERTICAL_PADDING = 72
const val MOBILE_CONTENT_MIN_HEIGHT = 640
