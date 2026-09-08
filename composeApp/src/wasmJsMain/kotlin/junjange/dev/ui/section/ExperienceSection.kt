package junjange.dev.ui.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.component.CardImage
import junjange.dev.ui.component.Chip
import junjange.dev.ui.component.SectionContainer
import junjange.dev.ui.component.SectionHeader
import junjange.dev.ui.model.Community
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.Education
import junjange.dev.ui.model.Etc
import junjange.dev.ui.model.Experience
import junjange.dev.ui.state.rememberDeviceState
import junjange.dev.ui.theme.TITLE_LETTER_SPACING
import junjange.dev.ui.theme.bodyLineHeight
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.experience
import junjange_dev.composeapp.generated.resources.experience_community
import junjange_dev.composeapp.generated.resources.experience_education
import junjange_dev.composeapp.generated.resources.experience_etc
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExperienceSection(modifier: Modifier = Modifier) {
    val deviceState = rememberDeviceState()
    val isDesktop = deviceState.value == Device.DESKTOP

    SectionContainer(modifier = modifier) {
        SectionHeader(title = stringResource(Res.string.experience))

        Spacer(modifier = Modifier.height(if (isDesktop) 48.dp else 32.dp))

        ExperienceGroup(
            title = stringResource(Res.string.experience_education),
            items = Education.entries,
            isDesktop = isDesktop,
        )

        Spacer(modifier = Modifier.height(GROUP_GAP))

        ExperienceGroup(
            title = stringResource(Res.string.experience_community),
            items = Community.entries,
            isDesktop = isDesktop,
        )

        Spacer(modifier = Modifier.height(GROUP_GAP))

        GroupTitle(text = stringResource(Res.string.experience_etc))
        Spacer(modifier = Modifier.height(12.dp))
        Etc.entries.forEach { etc ->
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            EtcRow(etc = etc)
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    }
}

@Composable
private fun ExperienceGroup(
    title: String,
    items: List<Experience>,
    isDesktop: Boolean,
) {
    GroupTitle(text = title)
    Spacer(modifier = Modifier.height(if (isDesktop) 40.dp else 28.dp))
    items.forEachIndexed { index, experience ->
        if (isDesktop) {
            ExperienceRow(experience = experience)
        } else {
            ExperienceColumn(experience = experience)
        }
        if (index != items.lastIndex) {
            Spacer(modifier = Modifier.height(if (isDesktop) ITEM_GAP else 56.dp))
        }
    }
}

@Composable
private fun GroupTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        letterSpacing = 0.sp,
    )
}

@Composable
private fun ExperienceRow(
    experience: Experience,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
    ) {
        Column(modifier = Modifier.width(HEADLINE_COLUMN_WIDTH)) {
            ExperienceLogo(experience = experience)
            Spacer(modifier = Modifier.height(20.dp))
            ExperienceTitle(experience = experience)
        }

        Spacer(modifier = Modifier.width(HEADLINE_COLUMN_GAP))

        Column(modifier = Modifier.weight(1f)) {
            ExperienceChips(experience = experience)
            Spacer(modifier = Modifier.height(24.dp))
            ExperienceDescription(description = stringResource(experience.descriptionRes))
        }
    }
}

@Composable
private fun ExperienceColumn(
    experience: Experience,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ExperienceLogo(experience = experience)
        Spacer(modifier = Modifier.height(20.dp))
        ExperienceChips(experience = experience)
        Spacer(modifier = Modifier.height(14.dp))
        ExperienceTitle(experience = experience)
        Spacer(modifier = Modifier.height(24.dp))
        ExperienceDescription(description = stringResource(experience.descriptionRes))
    }
}

@Composable
private fun ExperienceLogo(experience: Experience) {
    CardImage(
        logo = experience.logoRes,
        size = LOGO_SIZE,
        cornerRadius = 20.dp,
    )
}

@Composable
private fun ExperienceTitle(
    experience: Experience,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current

    Column(modifier = modifier) {
        Text(
            text = stringResource(experience.titleRes),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = TITLE_LETTER_SPACING,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = stringResource(experience.subtitleRes),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 16.sp,
            lineHeight = 16.sp.bodyLineHeight(),
        )

        if (experience.links.isNotEmpty()) {
            Spacer(modifier = Modifier.height(14.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                experience.links.forEach { (label, url) ->
                    Text(
                        text = stringResource(label),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { uriHandler.openUri(url) },
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ExperienceChips(experience: Experience) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Chip(text = stringResource(experience.periodRes), accent = true)
    }
}

@Composable
private fun ExperienceDescription(
    description: String,
    modifier: Modifier = Modifier,
) {
    val lines =
        description
            .split("\n")
            .map { it.trim().removePrefix("-").trim() }
            .filter { it.isNotEmpty() }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        lines.forEach { line ->
            val parts = line.split(" | ", limit = 2)
            if (parts.size == 2) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = parts[0],
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 15.sp,
                        lineHeight = 15.sp.bodyLineHeight(),
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = parts[1],
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        fontSize = 14.sp,
                        lineHeight = 15.sp.bodyLineHeight(),
                    )
                }
            } else {
                Text(
                    text = line,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 15.sp,
                    lineHeight = 15.sp.bodyLineHeight(),
                )
            }
        }
    }
}

@Composable
private fun EtcRow(
    etc: Etc,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 18.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = stringResource(etc.titleRes),
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 16.sp.bodyLineHeight(),
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = stringResource(etc.periodRes),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp,
            lineHeight = 16.sp.bodyLineHeight(),
        )
    }
}

private val ITEM_GAP = 80.dp
private val GROUP_GAP = 72.dp
private val HEADLINE_COLUMN_WIDTH = 360.dp
private val HEADLINE_COLUMN_GAP = 64.dp
private val LOGO_SIZE = 72.dp
