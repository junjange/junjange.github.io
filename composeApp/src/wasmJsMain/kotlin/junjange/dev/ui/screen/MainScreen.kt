package junjange.dev.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
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
import junjange.dev.ui.component.HomeDrawer
import junjange.dev.ui.component.HomeTopBar
import junjange.dev.ui.model.Section
import junjange.dev.ui.model.ThemeMode
import junjange.dev.ui.section.AboutSection
import junjange.dev.ui.section.CareerSection
import junjange.dev.ui.section.ContactSection
import junjange.dev.ui.section.ExperienceSection
import junjange.dev.ui.section.FeedbackPreviewSection
import junjange.dev.ui.section.HomeSection
import junjange.dev.ui.section.ProjectSection
import junjange.dev.ui.state.DeviceState
import junjange.dev.ui.state.rememberDeviceState
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    listState: LazyListState,
    modifier: Modifier = Modifier,
    onThemeChanged: (ThemeMode) -> Unit,
    onFeedbackClick: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    val deviceState = rememberDeviceState()
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
            MainContent(
                listState = listState,
                deviceState = deviceState,
                modifier = modifier,
                onThemeChanged = onThemeChanged,
                onFeedbackClick = onFeedbackClick,
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
fun MainContent(
    listState: LazyListState,
    deviceState: DeviceState,
    modifier: Modifier = Modifier,
    onThemeChanged: (ThemeMode) -> Unit,
    onFeedbackClick: () -> Unit,
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
                item { CareerSection() }
                item { ProjectSection(onSectionClicked = onSectionClicked) }
                item { ExperienceSection() }
                item { FeedbackPreviewSection(onSeeAll = onFeedbackClick) }
                item { ContactSection() }
            }
        }
    }
}
