package junjange.dev.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.LocalThemeMode
import junjange.dev.ui.PC_CONTENT_WIDTH
import junjange.dev.ui.ThemeMode
import junjange.dev.ui.model.Section
import junjange.dev.ui.state.DeviceState
import junjange.dev.ui.state.isPc
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeTopBar(
    deviceState: DeviceState,
    onThemeChanged: (ThemeMode) -> Unit,
    onTitleClick: () -> Unit,
    onSectionClicked: (Section) -> Unit,
    onMenuClick: () -> Unit,
) {
    MobileTopBar(
        modifier =
            Modifier.then(
                if (deviceState.isPc) {
                    Modifier.height(0.dp)
                } else {
                    Modifier
                },
            ),
        onTitleClick = onTitleClick,
        onMenuClick = onMenuClick,
        onThemeChanged = onThemeChanged,
    )

    if (deviceState.isPc) {
        PcTopBar(
            onThemeChanged = onThemeChanged,
            onSectionClicked = onSectionClicked,
        )
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

    TopAppBar(
        title = {
            LogoImage(onClick = onTitleClick)
        },
        actions = {
            IconButton(onClick = { onThemeChanged(themeMode.toggle()) }) {
                Icon(
                    painter = painterResource(themeMode.iconRes),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp),
                )
            }
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                )
            }
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
        modifier = modifier.padding(end = 8.dp),
        expandedHeight = HEADER_HEIGHT.dp,
    )
}

@Composable
fun PcTopBar(
    modifier: Modifier = Modifier,
    onThemeChanged: (ThemeMode) -> Unit,
    onSectionClicked: (Section) -> Unit,
) {
    val themeMode = LocalThemeMode.current

    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier =
                Modifier
                    .width(PC_CONTENT_WIDTH.dp)
                    .height(HEADER_HEIGHT.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LogoImage(onClick = { onSectionClicked(Section.Home) })
            Spacer(Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.spacedBy(48.dp),
            ) {
                Section.entries.forEach {
                    TextButton(
                        onClick = { onSectionClicked(it) },
                    ) {
                        Text(
                            text = stringResource(it.title),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            IconButton(onClick = { onThemeChanged(themeMode.toggle()) }) {
                Icon(
                    painter = painterResource(themeMode.iconRes),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

const val HEADER_HEIGHT = 72
