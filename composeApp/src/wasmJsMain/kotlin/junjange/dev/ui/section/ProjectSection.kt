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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.component.DefaultOutlinedButton
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import junjange.dev.ui.CONTENT_MAX_WIDTH
import junjange.dev.ui.component.SectionContainer
import junjange.dev.ui.component.SectionHeader
import junjange.dev.ui.component.hoverLift
import junjange.dev.ui.theme.TITLE_LETTER_SPACING
import junjange.dev.ui.theme.bodyLineHeight
import kotlin.math.min
import junjange.dev.ui.component.ProjectDialog
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.LocalScreenSize
import junjange.dev.ui.model.Project
import junjange.dev.ui.model.Section
import junjange.dev.ui.model.asDp
import junjange.dev.ui.state.DeviceState
import junjange.dev.ui.state.contentPadding
import junjange.dev.ui.state.rememberDeviceState
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.from_junjange
import junjange_dev.composeapp.generated.resources.project
import junjange_dev.composeapp.generated.resources.project_collapse
import junjange_dev.composeapp.generated.resources.project_detail
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

    SectionContainer(modifier = modifier) {
        SectionHeader(
            title = stringResource(Res.string.project),
            description = stringResource(Res.string.from_junjange),
        )

        Spacer(modifier = Modifier.height(if (deviceState.value == Device.DESKTOP) 48.dp else 32.dp))

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
            Spacer(modifier = Modifier.height(24.dp))
            DefaultOutlinedButton(
                text = stringResource(Res.string.project_collapse),
                modifier = Modifier.align(Alignment.CenterHorizontally),
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
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier =
            modifier.then(
                Modifier
                    .aspectRatio(CARD_ASPECT_RATIO)
                    .hoverLift()
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .clickable(interactionSource = interactionSource, indication = null, onClick = onClick)
                    .padding(16.dp),
            ),
    ) {
        Image(
            modifier =
                Modifier
                    .weight(IMAGE_WEIGHT)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
            painter = painterResource(project.graphicRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier =
                Modifier
                    .weight(TEXT_WEIGHT)
                    .padding(horizontal = 8.dp, vertical = 16.dp),
        ) {
            Text(
                text = stringResource(project.titleRes),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                letterSpacing = TITLE_LETTER_SPACING,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(project.subtitleRes),
                fontSize = 15.sp,
                lineHeight = 15.sp.bodyLineHeight(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            run {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(Res.string.project_detail),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(999.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp),
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
                                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.85f),
                                    MaterialTheme.colorScheme.primaryContainer,
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
    val contentWidth = min(screenWidth - horizontalPadding.value, CONTENT_MAX_WIDTH.toFloat())

    val rowCount = ceil(displayedProjectsSize / columnCount.toFloat()).toInt()
    val projectWidth = (contentWidth - ((columnCount - 1) * PROJECT_CARD_SPACED_BY_PADDING)) / columnCount
    val projectHeight = projectWidth / CARD_ASPECT_RATIO
    val totalHeight = (projectHeight + PROJECT_CARD_SPACED_BY_PADDING) * rowCount

    return Pair(projectHeight, totalHeight)
}

private fun getProjectColumnCount(device: Device): Int =
    when (device) {
        Device.DESKTOP -> 3
        Device.TABLET -> 2
        else -> 1
    }

private fun getProjectCountIncrement(device: Device): Int =
    when (device) {
        Device.DESKTOP -> 3
        Device.TABLET -> 4
        else -> 3
    }

private const val INIT_PROJECT_COUNT = 6
private const val IMAGE_WEIGHT = 5.4f
private const val TEXT_WEIGHT = 4.6f
private const val PROJECT_CARD_SPACED_BY_PADDING = 24
private const val CARD_ASPECT_RATIO = 0.8f
