package junjange.dev.ui.section

import androidx.compose.foundation.Image
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.component.DefaultOutlinedButton
import junjange.dev.ui.component.ProjectDialog
import junjange.dev.ui.component.SectionContainer
import junjange.dev.ui.component.SectionHeader
import junjange.dev.ui.component.hoverLift
import junjange.dev.ui.component.reveal
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.Project
import junjange.dev.ui.model.Section
import junjange.dev.ui.state.rememberDeviceState
import junjange.dev.ui.theme.TITLE_LETTER_SPACING
import junjange.dev.ui.theme.bodyLineHeight
import junjange.dev.ui.theme.keepWords
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.project_headline
import junjange_dev.composeapp.generated.resources.section_project
import junjange_dev.composeapp.generated.resources.project_collapse
import junjange_dev.composeapp.generated.resources.project_more
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProjectSection(
    onSectionClicked: (Section) -> Unit,
    modifier: Modifier = Modifier,
) {
    val deviceState = rememberDeviceState()
    val isDesktop = deviceState.value == Device.DESKTOP
    var moreSteps by rememberSaveable { mutableStateOf(0) }
    var selectedProject by remember { mutableStateOf<Project?>(null) }
    val columnCount = getProjectColumnCount(device = deviceState.value)
    val count = getInitialProjectCount(device = deviceState.value) + moreSteps * getProjectCountIncrement(device = deviceState.value)

    SectionContainer(modifier = modifier) {
        SectionHeader(
            eyebrow = stringResource(Res.string.section_project),
            headline = stringResource(Res.string.project_headline),
        )

        Spacer(modifier = Modifier.height(if (isDesktop) 64.dp else 40.dp))

        val rowGap = if (isDesktop) 48.dp else 40.dp
        val showMore = { moreSteps += 1 }

        Column(verticalArrangement = Arrangement.spacedBy(rowGap)) {
            Project.entries.take(count).chunked(columnCount).forEach { rowProjects ->
                ProjectRow(
                    projects = rowProjects,
                    columnCount = columnCount,
                    onClick = { selectedProject = it },
                )
            }
        }

        if (count < Project.entries.size) {
            Spacer(modifier = Modifier.height(rowGap))
            MorePreview(
                projects = Project.entries.drop(count).take(columnCount),
                columnCount = columnCount,
                height = if (isDesktop) PEEK_HEIGHT_DESKTOP else PEEK_HEIGHT_COMPACT,
                onShowMore = showMore,
            )
        } else {
            Spacer(modifier = Modifier.height(48.dp))
            DefaultOutlinedButton(
                text = stringResource(Res.string.project_collapse),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    moreSteps = 0
                    onSectionClicked(Section.Project)
                },
            )
        }
    }

    selectedProject?.let { project ->
        ProjectDialog(
            project = project,
            onDismissRequest = { selectedProject = null },
        )
    }
}

@Composable
private fun ProjectRow(
    projects: List<Project>,
    columnCount: Int,
    modifier: Modifier = Modifier,
    animate: Boolean = true,
    onClick: (Project) -> Unit,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(CARD_HORIZONTAL_GAP)) {
        projects.forEachIndexed { index, project ->
            ProjectCard(
                project = project,
                modifier = Modifier.weight(1f).then(if (animate) Modifier.reveal(delayMillis = index * 100) else Modifier),
                onClick = { onClick(project) },
            )
        }
        repeat(columnCount - projects.size) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun MorePreview(
    projects: List<Project>,
    columnCount: Int,
    height: Dp,
    onShowMore: () -> Unit,
) {
    val background = MaterialTheme.colorScheme.primaryContainer

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(height)
                .clipToBounds(),
    ) {
        ProjectRow(
            projects = projects,
            columnCount = columnCount,
            animate = false,
            modifier = Modifier.fillMaxWidth().wrapContentHeight(align = Alignment.Top, unbounded = true),
            onClick = { onShowMore() },
        )
        Box(
            modifier =
                Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            0f to background.copy(alpha = 0.1f),
                            0.55f to background.copy(alpha = 0.9f),
                            1f to background,
                        ),
                    ).clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onShowMore,
                    ),
        )
        DefaultOutlinedButton(
            text = stringResource(Res.string.project_more),
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = onShowMore,
        )
    }
}

@Composable
private fun ProjectCard(
    project: Project,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val thumbnailShape = RoundedCornerShape(20.dp)

    Column(
        modifier =
            modifier
                .hoverLift()
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable(interactionSource = interactionSource, indication = null, onClick = onClick),
    ) {
        Image(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(THUMBNAIL_ASPECT_RATIO)
                    .clip(thumbnailShape)
                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant, thumbnailShape),
            painter = painterResource(project.graphicRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(project.titleRes),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = TITLE_LETTER_SPACING,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(project.subtitleRes).keepWords(),
            fontSize = 15.sp,
            lineHeight = 15.sp.bodyLineHeight(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

private fun getProjectColumnCount(device: Device): Int =
    when (device) {
        Device.DESKTOP -> 3
        Device.TABLET -> 2
        else -> 1
    }

private fun getInitialProjectCount(device: Device): Int =
    when (device) {
        Device.DESKTOP -> 6
        Device.TABLET -> 4
        else -> 3
    }

private fun getProjectCountIncrement(device: Device): Int =
    when (device) {
        Device.DESKTOP -> 3
        Device.TABLET -> 4
        else -> 3
    }

private const val THUMBNAIL_ASPECT_RATIO = 1.5f
private val CARD_HORIZONTAL_GAP = 24.dp
private val PEEK_HEIGHT_DESKTOP = 220.dp
private val PEEK_HEIGHT_COMPACT = 200.dp
