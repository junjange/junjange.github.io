package junjange.dev.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    TopHeader(
        onThemeChanged = onThemeChanged,
        onSectionClicked = onSectionClicked,
        modifier =
            Modifier.then(
                if (deviceState.isMobile) {
                    Modifier.height(1.dp)
                } else {
                    Modifier
                },
            ),
    )
    if (deviceState.isMobile) {
        TopMenuBar(
            onTitleClick = onTitleClick,
            onMenuClick = onMenuClick,
            onThemeChanged = onThemeChanged,
        )
    }
}
