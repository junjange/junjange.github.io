package junjange.dev.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import junjange.dev.ui.LocalThemeMode
import junjange.dev.ui.ThemeMode
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMenuBar(
    onTitleClick: () -> Unit,
    onMenuClick: () -> Unit,
    onThemeChanged: (ThemeMode) -> Unit,
    modifier: Modifier = Modifier,
) {
    val themeMode = LocalThemeMode.current

    TopAppBar(
        title = {
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
