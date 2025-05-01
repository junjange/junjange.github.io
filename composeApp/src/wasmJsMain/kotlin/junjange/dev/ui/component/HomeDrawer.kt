package junjange.dev.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import junjange.dev.ui.model.Section
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeDrawer(
    modifier: Modifier = Modifier,
    onClickItem: (Section) -> Unit,
    onDismissRequest: () -> Unit,
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        ModalDrawerContent(
            modifier = modifier,
            onClickItem = onClickItem,
            onDismissRequest = onDismissRequest,
        )
    }
}

@Composable
fun ModalDrawerContent(
    onClickItem: (Section) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalDrawerSheet(
        modifier = modifier.fillMaxSize(),
        drawerShape = RoundedCornerShape(0.dp),
        drawerContainerColor = MaterialTheme.colorScheme.primaryContainer,
        drawerContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    ) {
        Column(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .verticalScroll(rememberScrollState()),
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.End),
                onClick = onDismissRequest,
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = null,
                )
            }
            Section.entries.forEach {
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = stringResource(it.title),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    },
                    selected = false,
                    onClick = { onClickItem(it) },
                )
            }
        }
    }
}
