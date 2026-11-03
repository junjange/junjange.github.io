package junjange.dev.ui.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import junjange.dev.ui.component.SurfaceCard
import junjange.dev.ui.component.reveal
import junjange.dev.ui.model.Community
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.Education
import junjange.dev.ui.model.Etc
import junjange.dev.ui.model.Experience
import junjange.dev.ui.state.rememberDeviceState
import junjange.dev.ui.theme.TITLE_LETTER_SPACING
import junjange.dev.ui.theme.bodyLineHeight
import junjange.dev.ui.theme.keepWords
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.experience_community
import junjange_dev.composeapp.generated.resources.experience_education
import junjange_dev.composeapp.generated.resources.experience_etc
import junjange_dev.composeapp.generated.resources.experience_headline
import junjange_dev.composeapp.generated.resources.section_experience
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExperienceSection(modifier: Modifier = Modifier) {
    val isMobile = rememberDeviceState().value == Device.MOBILE

    SectionContainer(modifier = modifier) {
        SectionHeader(
            eyebrow = stringResource(Res.string.section_experience),
            headline = stringResource(Res.string.experience_headline),
        )

        Spacer(modifier = Modifier.height(if (isMobile) 40.dp else 64.dp))

        ExperienceGroup(
            title = stringResource(Res.string.experience_education),
            items = Education.entries,
            isMobile = isMobile,
        )

        Spacer(modifier = Modifier.height(GROUP_GAP))

        ExperienceGroup(
            title = stringResource(Res.string.experience_community),
            items = Community.entries,
            isMobile = isMobile,
        )

        Spacer(modifier = Modifier.height(GROUP_GAP))

        GroupTitle(text = stringResource(Res.string.experience_etc))
        Spacer(modifier = Modifier.height(20.dp))
        SurfaceCard(
            modifier = Modifier.fillMaxWidth().reveal(),
            padding = if (isMobile) 20.dp else 32.dp,
        ) {
            Etc.entries.forEachIndexed { index, etc ->
                if (index != 0) HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                EtcRow(etc = etc, isMobile = isMobile)
            }
        }
    }
}

@Composable
private fun ExperienceGroup(
    title: String,
    items: List<Experience>,
    isMobile: Boolean,
) {
    GroupTitle(text = title)
    Spacer(modifier = Modifier.height(20.dp))
    if (isMobile) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items.forEach { experience ->
                ExperienceCard(experience = experience, isMobile = true, modifier = Modifier.fillMaxWidth().reveal())
            }
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items.forEachIndexed { index, experience ->
                ExperienceCard(
                    experience = experience,
                    isMobile = false,
                    modifier = Modifier.weight(1f).fillMaxHeight().reveal(delayMillis = index * 120),
                )
            }
        }
    }
}

@Composable
private fun GroupTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.reveal(),
        text = text,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        letterSpacing = TITLE_LETTER_SPACING,
    )
}

@Composable
private fun ExperienceCard(
    experience: Experience,
    isMobile: Boolean,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current

    SurfaceCard(modifier = modifier, padding = if (isMobile) 24.dp else 32.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CardImage(logo = experience.logoRes, size = 52.dp, cornerRadius = 16.dp)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(experience.titleRes),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = 27.sp,
                    letterSpacing = TITLE_LETTER_SPACING,
                )
                Text(
                    text = stringResource(experience.subtitleRes),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 15.sp,
                    lineHeight = 15.sp.bodyLineHeight(),
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))
        Chip(text = stringResource(experience.periodRes), onCard = true)
        Spacer(modifier = Modifier.height(20.dp))
        ExperienceDescription(description = stringResource(experience.descriptionRes))

        if (experience.links.isNotEmpty()) {
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(24.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                experience.links.forEach { (label, url) ->
                    Text(
                        text = "${stringResource(label)} ↗",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        modifier = Modifier.clickable { uriHandler.openUri(url) },
                    )
                }
            }
        }
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
                        text = parts[0].keepWords(),
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        lineHeight = 15.sp.bodyLineHeight(),
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = parts[1],
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 14.sp,
                        lineHeight = 15.sp.bodyLineHeight(),
                    )
                }
            } else {
                Text(
                    text = line.keepWords(),
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
    isMobile: Boolean,
    modifier: Modifier = Modifier,
) {
    val title = stringResource(etc.titleRes).keepWords()
    val period = stringResource(etc.periodRes)

    if (isMobile) {
        Column(modifier = modifier.fillMaxWidth().padding(vertical = 16.dp)) {
            Text(
                text = period,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 13.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                lineHeight = 15.sp.bodyLineHeight(),
            )
        }
    } else {
        Row(
            modifier = modifier.fillMaxWidth().padding(vertical = 20.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 16.sp.bodyLineHeight(),
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = period,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 15.sp,
                lineHeight = 16.sp.bodyLineHeight(),
            )
        }
    }
}

private val GROUP_GAP = 72.dp
