package junjange.dev.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.LocalScreenSize
import junjange.dev.ui.model.Project
import junjange.dev.ui.model.asDp
import junjange.dev.ui.state.rememberDeviceState
import junjange.dev.ui.theme.TITLE_LETTER_SPACING
import junjange.dev.ui.theme.bodyLineHeight
import junjange.dev.ui.theme.keepWords
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.contributions
import junjange_dev.composeapp.generated.resources.project_intro
import junjange_dev.composeapp.generated.resources.techStack
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProjectDialog(
    project: Project,
    onDismissRequest: () -> Unit,
) {
    val isMobile = rememberDeviceState().value == Device.MOBILE
    val screenHeight = LocalScreenSize.current.asDp().height

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box(
            modifier =
                Modifier
                    .padding(if (isMobile) 12.dp else 32.dp)
                    .widthIn(max = DIALOG_MAX_WIDTH)
                    .fillMaxWidth()
                    .heightIn(max = (screenHeight * 0.9f).dp)
                    .clip(RoundedCornerShape(CARD_RADIUS))
                    .background(MaterialTheme.colorScheme.primaryContainer),
        ) {
            ProjectDialogContent(project = project, isMobile = isMobile)
            IconButton(
                onClick = onDismissRequest,
                modifier =
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .size(40.dp)
                        .background(Color.Black.copy(alpha = 0.45f), CircleShape),
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp),
                )
            }
        }
    }
}

@Composable
private fun ProjectDialogContent(
    project: Project,
    isMobile: Boolean,
) {
    val horizontalPadding = if (isMobile) 24.dp else 40.dp

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Image(
            painter = painterResource(project.graphicRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().aspectRatio(1.8f),
        )

        Column(modifier = Modifier.padding(horizontal = horizontalPadding, vertical = if (isMobile) 28.dp else 40.dp)) {
            ProjectSummary(project = project, isMobile = isMobile)

            if (project.links.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                ProjectLinks(project = project)
            }

            DialogBlock(title = stringResource(Res.string.project_intro)) {
                Text(
                    text = stringResource(project.descriptionRes).keepWords(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 16.sp,
                    lineHeight = 16.sp.bodyLineHeight(),
                )
            }

            DialogBlock(title = stringResource(Res.string.contributions)) {
                ContributionList(contributions = stringResource(project.contributionsRes))
            }

            DialogBlock(title = stringResource(Res.string.techStack)) {
                TechStackChips(techStack = stringResource(project.techStackRes))
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProjectSummary(
    project: Project,
    isMobile: Boolean,
) {
    Text(
        text = stringResource(project.titleRes),
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontWeight = FontWeight.Bold,
        fontSize = if (isMobile) 26.sp else 32.sp,
        lineHeight = if (isMobile) 34.sp else 42.sp,
        letterSpacing = TITLE_LETTER_SPACING,
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = stringResource(project.subtitleRes).keepWords(),
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontSize = 17.sp,
        lineHeight = 17.sp.bodyLineHeight(),
    )
    Spacer(modifier = Modifier.height(20.dp))
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Chip(text = stringResource(project.roleRes), accent = true)
        Chip(text = stringResource(project.periodRes))
    }
}

@Composable
private fun DialogBlock(
    title: String,
    content: @Composable () -> Unit,
) {
    Spacer(modifier = Modifier.height(40.dp))
    Text(
        text = title,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontWeight = FontWeight.Bold,
        fontSize = 19.sp,
        letterSpacing = TITLE_LETTER_SPACING,
    )
    Spacer(modifier = Modifier.height(14.dp))
    content()
}

@Composable
private fun ContributionList(contributions: String) {
    val items =
        contributions
            .split("\n")
            .map { it.trim().removePrefix("-").trim() }
            .filter { it.isNotEmpty() }

    SurfaceCard(modifier = Modifier.fillMaxWidth(), padding = 24.dp) {
        items.forEachIndexed { index, item ->
            if (index != 0) Spacer(modifier = Modifier.height(18.dp))
            val parts = item.split(" - ", limit = 2)
            Row {
                Text(
                    text = "${index + 1}",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 16.sp.bodyLineHeight(),
                    modifier = Modifier.width(24.dp),
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = parts[0].keepWords(),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        lineHeight = 16.sp.bodyLineHeight(),
                    )
                    if (parts.size == 2) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = parts[1].keepWords(),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 15.sp,
                            lineHeight = 15.sp.bodyLineHeight(),
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TechStackChips(techStack: String) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        techStack.split(",").map { it.trim() }.filter { it.isNotEmpty() }.forEach { tech ->
            Text(
                text = tech,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                modifier =
                    Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(999.dp))
                        .padding(horizontal = 14.dp, vertical = 8.dp),
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProjectLinks(project: Project) {
    val uriHandler = LocalUriHandler.current

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        project.links.forEach { (label, url) ->
            Text(
                text = "${stringResource(label)} ↗",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                modifier =
                    Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                        .pointerHoverIcon(PointerIcon.Hand)
                        .clickable { uriHandler.openUri(url) }
                        .padding(horizontal = 16.dp, vertical = 12.dp),
            )
        }
    }
}

private val DIALOG_MAX_WIDTH = 760.dp
