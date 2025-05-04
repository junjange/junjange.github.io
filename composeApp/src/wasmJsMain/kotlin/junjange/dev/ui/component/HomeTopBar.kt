package junjange.dev.ui.component

import androidx.compose.runtime.Composable
import junjange.dev.ui.ThemeMode
import junjange.dev.ui.model.Section
import junjange.dev.ui.state.DeviceState
import junjange.dev.ui.state.isMobile

@Composable
fun HomeTopBar(
    deviceState: DeviceState,
    onThemeChanged: (ThemeMode) -> Unit,
    onTitleClick: () -> Unit,
    onSectionClicked: (Section) -> Unit,
    onMenuClick: () -> Unit,
) {
    if (deviceState.isMobile) {
        TopMenuBar(
            onTitleClick = onTitleClick,
            onMenuClick = onMenuClick,
            onThemeChanged = onThemeChanged,
        )
    } else {
        TopHeader(
            onThemeChanged = onThemeChanged,
            onSectionClicked = onSectionClicked,
        )
    }
}
