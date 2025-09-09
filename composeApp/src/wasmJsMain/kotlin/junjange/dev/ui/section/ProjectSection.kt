package junjange.dev.ui.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.component.DefaultOutlinedButton
import junjange.dev.ui.component.ProjectDialog
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.LocalScreenSize
import junjange.dev.ui.model.Project
import junjange.dev.ui.model.Section
import junjange.dev.ui.model.asDp
import junjange.dev.ui.state.DeviceState
import junjange.dev.ui.state.contentPadding
import junjange.dev.ui.state.rememberDeviceState
import junjange.dev.ui.theme.DarkGray
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.from_junjange
import junjange_dev.composeapp.generated.resources.project
import junjange_dev.composeapp.generated.resources.project_collapse
import junjange_dev.composeapp.generated.resources.project_more
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.ceil

@Composable
fun ProjectSection(
    onSectionClicked: (Section) -> Unit,
    modifier: Modifier = Modifier,
) {
    val deviceState = rememberDeviceState()
    val screenSize = LocalScreenSize.current
    var count by rememberSaveable { mutableStateOf(INIT_PROJECT_COUNT) }
    val displayedProjects = Project.entries.take(count)
    var selectedProject by remember { mutableStateOf<Project?>(null) }
    val columnCount = getProjectColumnCount(device = deviceState.value)

    val (projectHeight, totalHeight) =
        calculateProjectGridDimensions(
            screenWidth = screenSize.asDp().width,
            columnCount = columnCount,
            displayedProjectsSize = displayedProjects.size,
            deviceState = deviceState,
        )

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
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(Res.string.from_junjange),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(48.dp))

        Box(
            Modifier
                .fillMaxWidth()
                .height(totalHeight.dp),
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(columnCount),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(PROJECT_CARD_SPACED_BY_PADDING.dp),
                horizontalArrangement = Arrangement.spacedBy(PROJECT_CARD_SPACED_BY_PADDING.dp),
                userScrollEnabled = false,
            ) {
                items(displayedProjects) { project ->
                    ProjectCard(
                        project = project,
                        onClick = { selectedProject = project },
                    )
                }
            }
            if (count < Project.entries.size) {
                ProjectMoreButton(
                    modifier = Modifier.height((projectHeight + PROJECT_CARD_SPACED_BY_PADDING).dp),
                    onClick = {
                        count += getProjectCountIncrement(device = deviceState.value)
                    },
                )
            }

            if (selectedProject != null) {
                ProjectDialog(
                    project = selectedProject!!,
                    onDismissRequest = { selectedProject = null },
                )
            }
        }

        if (count >= Project.entries.size) {
            DefaultOutlinedButton(
                text = stringResource(Res.string.project_collapse),
                modifier = modifier,
                onClick = {
                    count = INIT_PROJECT_COUNT
                    onSectionClicked(Section.Project)
                },
            )
        }
    }
}

@Composable
private fun ProjectCard(
    project: Project,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 24.dp,
    onClick: () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(cornerRadius),
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier =
            modifier.then(
                Modifier
                    .aspectRatio(1f)
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(cornerRadius),
                        clip = false,
                        ambientColor = DarkGray.copy(0.01f),
                        spotColor = DarkGray.copy(0.01f),
                    ).clip(RoundedCornerShape(cornerRadius))
                    .clickable { onClick() },
            ),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize(),
        ) {
            Image(
                modifier = Modifier.weight(IMAGE_WEIGHT),
                painter = painterResource(project.graphicRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier =
                    Modifier
                        .weight(TEXT_WEIGHT)
                        .padding(16.dp),
            ) {
                Text(
                    text = stringResource(project.titleRes),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(project.subtitleRes),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        }
    }
}

@Composable
private fun BoxScope.ProjectMoreButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier =
            modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    brush =
                        Brush.verticalGradient(
                            colors =
                                listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f),
                                    MaterialTheme.colorScheme.secondaryContainer,
                                ),
                        ),
                ).clickable(enabled = false, onClick = {}),
    ) {
        DefaultOutlinedButton(
            text = stringResource(Res.string.project_more),
            modifier =
                Modifier
                    .align(Alignment.Center),
            onClick = onClick,
        )
    }
}

@Composable
private fun calculateProjectGridDimensions(
    screenWidth: Int,
    columnCount: Int,
    displayedProjectsSize: Int,
    deviceState: DeviceState,
): Pair<Float, Float> {
    val horizontalPadding =
        deviceState.contentPadding().calculateStartPadding(LayoutDirection.Ltr) +
            deviceState.contentPadding().calculateEndPadding(LayoutDirection.Ltr)

    val rowCount = ceil(displayedProjectsSize / columnCount.toFloat()).toInt()
    val projectHeight =
        (screenWidth - horizontalPadding.value - ((columnCount - 1) * PROJECT_CARD_SPACED_BY_PADDING)) / columnCount
    val totalHeight = (projectHeight + PROJECT_CARD_SPACED_BY_PADDING) * rowCount

    return Pair(projectHeight, totalHeight)
}

private fun getProjectColumnCount(device: Device): Int =
    when (device) {
        Device.DESKTOP -> 3
        else -> 2
    }

private fun getProjectCountIncrement(device: Device): Int =
    when (device) {
        Device.DESKTOP -> 3
        else -> 4
    }

private const val INIT_PROJECT_COUNT = 6
private const val IMAGE_WEIGHT = 6f
private const val TEXT_WEIGHT = 4f
private const val PROJECT_CARD_SPACED_BY_PADDING = 32
