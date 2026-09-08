package junjange.dev.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.CONTENT_MAX_WIDTH
import junjange.dev.ui.DESKTOP_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.LocalThemeMode
import junjange.dev.ui.model.Section
import junjange.dev.ui.model.ThemeMode
import junjange.dev.ui.state.DeviceState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeTopBar(
    deviceState: DeviceState,
    modifier: Modifier = Modifier,
    onThemeChanged: (ThemeMode) -> Unit,
    onTitleClick: () -> Unit,
    onSectionClicked: (Section) -> Unit,
    onMenuClick: () -> Unit,
) {
    when (deviceState.value) {
        Device.DESKTOP ->
            DesktopTopBar(
                modifier = modifier,
                onThemeChanged = onThemeChanged,
                onSectionClicked = onSectionClicked,
            )

        Device.TABLET, Device.MOBILE ->
            MobileTopBar(
                modifier = modifier,
                onTitleClick = onTitleClick,
                onMenuClick = onMenuClick,
                onThemeChanged = onThemeChanged,
            )

        Device.UNKNOWN -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileTopBar(
    modifier: Modifier = Modifier,
    onTitleClick: () -> Unit,
    onMenuClick: () -> Unit,
    onThemeChanged: (ThemeMode) -> Unit,
) {
    val themeMode = LocalThemeMode.current

    Column(modifier = modifier) {
        TopAppBar(
            title = {
                LogoImage(onClick = onTitleClick)
            },
            actions = {
                IconButton(onClick = { onThemeChanged(themeMode.toggle()) }) {
                    Icon(
                        painter = painterResource(themeMode.iconRes),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(24.dp),
                    )
                }
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        contentDescription = null,
                    )
                }
            },
            colors =
                TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
            modifier = Modifier.padding(end = 8.dp),
            expandedHeight = HEADER_HEIGHT.dp,
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    }
}

@Composable
fun DesktopTopBar(
    modifier: Modifier = Modifier,
    onThemeChanged: (ThemeMode) -> Unit,
    onSectionClicked: (Section) -> Unit,
) {
    val themeMode = LocalThemeMode.current

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .height(HEADER_HEIGHT.dp),
        contentAlignment = Alignment.Center,
    ) {
        HorizontalDivider(
            modifier = Modifier.align(Alignment.BottomCenter),
            color = MaterialTheme.colorScheme.outlineVariant,
        )
        Row(
            modifier =
                Modifier
                    .padding(horizontal = DESKTOP_CONTENT_HORIZONTAL_PADDING.dp, vertical = 12.dp)
                    .widthIn(max = CONTENT_MAX_WIDTH.dp)
                    .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            LogoImage(onClick = { onSectionClicked(Section.Home) })
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Section.entries.forEach {
                    TextButton(onClick = { onSectionClicked(it) }) {
                        Text(
                            text = stringResource(it.title),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                        )
                    }
                }
                IconButton(onClick = { onThemeChanged(themeMode.toggle()) }) {
                    Icon(
                        painter = painterResource(themeMode.iconRes),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        }
    }
}

const val HEADER_HEIGHT = 72
