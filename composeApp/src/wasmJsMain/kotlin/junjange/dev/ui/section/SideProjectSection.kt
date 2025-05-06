package junjange.dev.ui.section

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.MOBILE_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.component.AppButton
import junjange.dev.ui.model.SideProject
import junjange.dev.ui.state.contentPadding
import junjange.dev.ui.state.isMobile
import junjange.dev.ui.state.rememberDeviceState
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.side_project_lover
import org.jetbrains.compose.resources.stringResource

@Composable
fun SideProjectSection(modifier: Modifier = Modifier) {
    val deviceState = rememberDeviceState()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        while (true) {
            listState.animateScrollBy(
                value = if (deviceState.isMobile) 3f else 1f,
                animationSpec =
                    tween(
                        durationMillis = 10,
                        delayMillis = 0,
                        easing = LinearEasing,
                    ),
            )
        }
    }

    Column(
        modifier = modifier.padding(deviceState.contentPadding()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            stringResource(Res.string.side_project_lover),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            lineHeight = 42.sp,
            modifier = Modifier.padding(horizontal = MOBILE_CONTENT_HORIZONTAL_PADDING.dp),
        )
    }

    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        userScrollEnabled = false,
    ) {
        items(List(100) { SideProject.entries }.flatten()) { sideProject ->
            AppButton(
                sideProject = sideProject,
                size = 128.dp,
            )
        }
    }
    Spacer(modifier = Modifier.height(64.dp))
}
