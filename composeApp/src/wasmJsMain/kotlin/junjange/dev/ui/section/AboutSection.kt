package junjange.dev.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.material3.HorizontalDivider
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.component.SectionContainer
import junjange.dev.ui.component.SectionHeader
import junjange.dev.ui.component.SurfaceCard
import junjange.dev.ui.component.reveal
import junjange.dev.ui.model.AboutMe
import junjange.dev.ui.model.Career
import junjange.dev.ui.model.Community
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.Education
import junjange.dev.ui.model.Project
import junjange.dev.ui.model.formatTenure
import junjange.dev.ui.state.rememberDeviceState
import junjange.dev.ui.theme.TITLE_LETTER_SPACING
import junjange.dev.ui.theme.bodyLineHeight
import junjange.dev.ui.theme.keepWords
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.about_headline
import junjange_dev.composeapp.generated.resources.section_about
import junjange_dev.composeapp.generated.resources.stat_communities
import junjange_dev.composeapp.generated.resources.stat_communities_value
import junjange_dev.composeapp.generated.resources.stat_projects
import junjange_dev.composeapp.generated.resources.stat_projects_value
import junjange_dev.composeapp.generated.resources.stat_tenure
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutSection(modifier: Modifier = Modifier) {
    val device = rememberDeviceState().value
    val isMobile = device == Device.MOBILE
    val isDesktop = device == Device.DESKTOP

    SectionContainer(modifier = modifier) {
        StatRow(isMobile = isMobile)

        Spacer(modifier = Modifier.height(if (isMobile) 96.dp else 160.dp))

        SectionHeader(
            eyebrow = stringResource(Res.string.section_about),
            headline = stringResource(Res.string.about_headline),
        )

        Spacer(modifier = Modifier.height(if (isMobile) 40.dp else 64.dp))

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            AboutMe.entries.forEachIndexed { index, aboutMe ->
                if (isDesktop) {
                    AboutWideCard(index = index, aboutMe = aboutMe, modifier = Modifier.fillMaxWidth().reveal())
                } else {
                    AboutCard(index = index, aboutMe = aboutMe, modifier = Modifier.fillMaxWidth().reveal())
                }
            }
        }
    }
}

@Composable
private fun StatRow(isMobile: Boolean) {
    val tenure = formatTenure(Career.fullTimeMonths())
    val stats =
        listOf(
            stringResource(Res.string.stat_tenure) to tenure,
            stringResource(Res.string.stat_projects) to stringResource(Res.string.stat_projects_value, Project.entries.size),
            stringResource(Res.string.stat_communities) to
                stringResource(Res.string.stat_communities_value, Education.entries.size + Community.entries.size),
        )

    if (isMobile) {
        SurfaceCard(modifier = Modifier.fillMaxWidth().reveal(), padding = 24.dp) {
            stats.forEachIndexed { index, (label, value) ->
                if (index != 0) HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                StatListRow(label = label, value = value)
            }
        }
    } else {
        Row(horizontalArrangement = Arrangement.spacedBy(CARD_GAP)) {
            stats.forEachIndexed { index, (label, value) ->
                StatCard(
                    label = label,
                    value = value,
                    modifier = Modifier.weight(1f).reveal(delayMillis = index * 120),
                )
            }
        }
    }
}

@Composable
private fun StatListRow(
    label: String,
    value: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            letterSpacing = TITLE_LETTER_SPACING,
        )
    }
}

@Composable
private fun StatCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    SurfaceCard(modifier = modifier, padding = 28.dp) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = TITLE_LETTER_SPACING,
        )
    }
}

@Composable
private fun AboutWideCard(
    index: Int,
    aboutMe: AboutMe,
    modifier: Modifier = Modifier,
) {
    SurfaceCard(modifier = modifier, padding = 40.dp) {
        Row {
            Column(modifier = Modifier.width(WIDE_TITLE_WIDTH)) {
                AboutNumber(index = index)
                Spacer(modifier = Modifier.height(16.dp))
                AboutTitle(aboutMe = aboutMe)
            }
            Spacer(modifier = Modifier.width(48.dp))
            AboutDescription(aboutMe = aboutMe, modifier = Modifier.weight(1f).padding(top = 34.dp))
        }
    }
}

@Composable
private fun AboutCard(
    index: Int,
    aboutMe: AboutMe,
    modifier: Modifier = Modifier,
) {
    SurfaceCard(modifier = modifier) {
        AboutNumber(index = index)
        Spacer(modifier = Modifier.height(20.dp))
        AboutTitle(aboutMe = aboutMe)
        Spacer(modifier = Modifier.height(16.dp))
        AboutDescription(aboutMe = aboutMe)
    }
}

@Composable
private fun AboutNumber(index: Int) {
    Text(
        text = "0${index + 1}",
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
    )
}

@Composable
private fun AboutTitle(aboutMe: AboutMe) {
    Text(
        text = stringResource(aboutMe.titleRes).keepWords(),
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 31.sp,
        letterSpacing = TITLE_LETTER_SPACING,
    )
}

@Composable
private fun AboutDescription(
    aboutMe: AboutMe,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(aboutMe.descriptionRes).keepWords(),
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontSize = 16.sp,
        lineHeight = 16.sp.bodyLineHeight(),
    )
}

private val CARD_GAP = 20.dp
private val WIDE_TITLE_WIDTH = 340.dp
