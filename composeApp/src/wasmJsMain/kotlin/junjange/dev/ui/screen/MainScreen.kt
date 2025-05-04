package junjange.dev.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import junjange.dev.ui.ThemeMode
import junjange.dev.ui.component.HomeDrawer
import junjange.dev.ui.component.HomeTopBar
import junjange.dev.ui.model.Section
import junjange.dev.ui.section.AboutSection
import junjange.dev.ui.section.HomeSection
import junjange.dev.ui.state.DeviceState
import junjange.dev.ui.state.rememberDeviceState
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onThemeChanged: (ThemeMode) -> Unit,
) {
    val scope = rememberCoroutineScope()

    val deviceState = rememberDeviceState()
    val listState = rememberLazyListState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            gesturesEnabled = false,
            drawerContent = {
                HomeDrawer(
                    onClickItem = {
                        scope.launch {
                            drawerState.close()
                            listState.animateScrollToItem(it.ordinal)
                        }
                    },
                    onDismissRequest = {
                        scope.launch {
                            drawerState.close()
                        }
                    },
                )
            },
            drawerState = drawerState,
        ) {
            HomeContent(
                listState = listState,
                deviceState = deviceState,
                modifier = modifier,
                onThemeChanged = onThemeChanged,
                onTitleClick = {
                    scope.launch {
                        listState.animateScrollToItem(Section.Home.ordinal)
                    }
                },
                onSectionClicked = {
                    scope.launch {
                        listState.animateScrollToItem(it.ordinal)
                    }
                },
                onMenuClick = {
                    scope.launch {
                        drawerState.open()
                    }
                },
            )
        }
    }
}

@Composable
fun HomeContent(
    listState: LazyListState,
    deviceState: DeviceState,
    modifier: Modifier = Modifier,
    onThemeChanged: (ThemeMode) -> Unit,
    onTitleClick: () -> Unit,
    onSectionClicked: (Section) -> Unit,
    onMenuClick: () -> Unit,
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Scaffold(
            modifier = modifier,
            topBar = {
                HomeTopBar(
                    onThemeChanged = onThemeChanged,
                    deviceState = deviceState,
                    onTitleClick = onTitleClick,
                    onMenuClick = onMenuClick,
                    onSectionClicked = onSectionClicked,
                )
            },
        ) { innerPadding ->
            LazyColumn(
                state = listState,
                modifier =
                    modifier.then(
                        Modifier
                            .padding(innerPadding)
                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                            .fillMaxSize(),
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item { HomeSection(onSectionClicked = onSectionClicked) }
                item { AboutSection() }
            }
        }
    }
}
