package junjange.dev.ui.section

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.LocalScreenSize
import junjange.dev.ui.MOBILE_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.component.AppButton
import junjange.dev.ui.component.CardImage
import junjange.dev.ui.model.Project
import junjange.dev.ui.state.contentPadding
import junjange.dev.ui.state.isMobile
import junjange.dev.ui.state.isPc
import junjange.dev.ui.state.rememberDeviceState
import junjange.dev.ui.theme.DarkGray
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.contributions
import junjange_dev.composeapp.generated.resources.link
import junjange_dev.composeapp.generated.resources.project
import junjange_dev.composeapp.generated.resources.role
import junjange_dev.composeapp.generated.resources.techStack
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProjectSection(modifier: Modifier = Modifier) {
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
        modifier =
            modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(deviceState.contentPadding()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(Res.string.project),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            lineHeight = 42.sp,
            modifier = Modifier.padding(horizontal = MOBILE_CONTENT_HORIZONTAL_PADDING.dp),
        )

        Spacer(modifier = Modifier.height(48.dp))

        if (deviceState.isMobile) {
            Column {
                Project.featuredProjects.forEach { project ->
                    ProjectCard(project = project)
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        } else {
            Row {
                Project.featuredProjects.forEach { project ->
                    ProjectCard(
                        modifier =
                            Modifier
                                .weight(1f),
                        project = project,
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                }
            }
        }
    }

    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        userScrollEnabled = false,
        modifier =
            Modifier
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(bottom = 64.dp),
    ) {
        items(List(100) { Project.entries }.flatten()) { project ->
            AppButton(project = project)
        }
    }
}

@Composable
fun ProjectCard(
    project: Project,
    modifier: Modifier = Modifier,
) {
    project.info ?: return
    val uriHandler = LocalUriHandler.current
    val deviceState = rememberDeviceState()
    val screenSize = LocalScreenSize.current

    Column(
        modifier =
            modifier
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(12.dp),
                    ambientColor = DarkGray.copy(0.01f),
                    spotColor = DarkGray.copy(0.01f),
                    clip = false,
                ).background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(12.dp),
                ).padding(20.dp),
    ) {
        CardImage(
            logo = project.logoRes,
            size = 92.dp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(project.titleRes),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Text(
            text = stringResource(project.info.subtitleRes),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
        )

        Text(
            text = stringResource(project.info.periodRes),
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
        )

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(thickness = 1.dp)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(Res.string.link),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )

        if (deviceState.isPc && screenSize.width <= 2300) {
            Column(verticalArrangement = Arrangement.Center) {
                project.info.links.forEach { (label, url) ->
                    Text(
                        text = stringResource(label),
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { uriHandler.openUri(url) },
                        style =
                            MaterialTheme.typography.bodySmall.copy(
                                textDecoration = TextDecoration.Underline,
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                            ),
                    )
                }
            }
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                project.info.links.forEachIndexed { index, (label, url) ->
                    Text(
                        text = stringResource(label),
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { uriHandler.openUri(url) },
                        style =
                            MaterialTheme.typography.bodySmall.copy(
                                textDecoration = TextDecoration.Underline,
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                            ),
                    )
                    if (index != project.info.links.lastIndex) {
                        Text(
                            text = " | ",
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(Res.string.techStack),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(project.info.techStackRes),
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(Res.string.role),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(project.info.roleRes),
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(Res.string.contributions),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            fontSize = 14.sp,
            text = stringResource(project.info.contributionsRes),
            style = MaterialTheme.typography.bodySmall,
            lineHeight = 18.sp,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
        )
    }
}
