package junjange.dev.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.component.CardImage
import junjange.dev.ui.component.Chip
import junjange.dev.ui.component.SectionContainer
import junjange.dev.ui.component.SectionHeader
import junjange.dev.ui.component.SurfaceCard
import junjange.dev.ui.component.reveal
import junjange.dev.ui.model.Career
import junjange.dev.ui.model.CareerProject
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.LocalThemeMode
import junjange.dev.ui.model.ThemeMode
import junjange.dev.ui.model.formatTenure
import junjange.dev.ui.state.rememberDeviceState
import junjange.dev.ui.theme.PassOrderNavy
import junjange.dev.ui.theme.PassOrderNavyOnDark
import junjange.dev.ui.theme.PassOrderOrange
import junjange.dev.ui.theme.TITLE_LETTER_SPACING
import junjange.dev.ui.theme.bodyLineHeight
import junjange.dev.ui.theme.keepWords
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.career_headline
import junjange_dev.composeapp.generated.resources.career_previous
import junjange_dev.composeapp.generated.resources.section_career
import org.jetbrains.compose.resources.stringResource

@Composable
fun CareerSection(modifier: Modifier = Modifier) {
    val device = rememberDeviceState().value
    val isMobile = device == Device.MOBILE
    val isDesktop = device == Device.DESKTOP
    val previous = Career.entries.drop(1)

    SectionContainer(modifier = modifier) {
        SectionHeader(
            eyebrow = stringResource(Res.string.section_career),
            headline = stringResource(Res.string.career_headline),
            accents = passOrderAccents(),
        )

        Spacer(modifier = Modifier.height(if (isMobile) 40.dp else 64.dp))

        CurrentCareerCard(
            career = Career.Paytalab,
            isDesktop = isDesktop,
            isMobile = isMobile,
            modifier = Modifier.fillMaxWidth().reveal(),
        )

        Spacer(modifier = Modifier.height(if (isMobile) 56.dp else 80.dp))

        Text(
            text = stringResource(Res.string.career_previous),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = if (isMobile) 20.sp else 24.sp,
            letterSpacing = TITLE_LETTER_SPACING,
            modifier = Modifier.reveal(),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            previous.forEach { career ->
                PreviousCareerCard(
                    career = career,
                    isDesktop = isDesktop,
                    isMobile = isMobile,
                    modifier = Modifier.fillMaxWidth().reveal(),
                )
            }
        }
    }
}

@Composable
private fun CurrentCareerCard(
    career: Career,
    isDesktop: Boolean,
    isMobile: Boolean,
    modifier: Modifier = Modifier,
) {
    SurfaceCard(modifier = modifier, padding = if (isMobile) 24.dp else 40.dp) {
        CareerHeadline(career = career, logoSize = if (isMobile) 56.dp else 64.dp, nameSize = if (isMobile) 22 else 26)
        Spacer(modifier = Modifier.height(20.dp))
        CareerChips(career = career)
        Spacer(modifier = Modifier.height(if (isMobile) 32.dp else 44.dp))

        if (isDesktop) {
            Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                career.project.forEach { project ->
                    CareerProjectItem(careerProject = project, modifier = Modifier.weight(1f))
                }
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(28.dp)) {
                career.project.forEach { project ->
                    CareerProjectItem(careerProject = project)
                }
            }
        }
    }
}

@Composable
private fun PreviousCareerCard(
    career: Career,
    isDesktop: Boolean,
    isMobile: Boolean,
    modifier: Modifier = Modifier,
) {
    SurfaceCard(modifier = modifier, padding = if (isMobile) 24.dp else 40.dp) {
        if (isDesktop) {
            Row {
                Column(modifier = Modifier.width(PREVIOUS_HEADLINE_WIDTH)) {
                    CareerHeadline(career = career, logoSize = 52.dp, nameSize = 20)
                    Spacer(modifier = Modifier.height(18.dp))
                    CareerChips(career = career)
                }
                Spacer(modifier = Modifier.width(40.dp))
                PreviousProjects(career = career, modifier = Modifier.weight(1f))
            }
        } else {
            CareerHeadline(career = career, logoSize = 52.dp, nameSize = 20)
            Spacer(modifier = Modifier.height(18.dp))
            CareerChips(career = career)
            Spacer(modifier = Modifier.height(28.dp))
            PreviousProjects(career = career)
        }
    }
}

@Composable
private fun PreviousProjects(
    career: Career,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(24.dp)) {
        career.project.forEach { project ->
            CareerProjectItem(careerProject = project)
        }
    }
}

@Composable
private fun CareerHeadline(
    career: Career,
    logoSize: Dp,
    nameSize: Int,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        CardImage(
            logo = career.logoRes,
            size = logoSize,
            cornerRadius = 16.dp,
            contentPadding = PaddingValues(0.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = stringResource(career.nameRes),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = nameSize.sp,
                lineHeight = (nameSize * 1.3f).sp,
                letterSpacing = TITLE_LETTER_SPACING,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = stringResource(career.introRes).keepWords(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 15.sp,
                lineHeight = 15.sp.bodyLineHeight(),
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CareerChips(career: Career) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Chip(text = stringResource(career.teamRes), accent = true)
        val period = stringResource(career.periodRes)
        val tenure = career.joinDate?.let { formatTenure(it.elapsedMonths()) }
        Chip(text = if (tenure != null) "$period ($tenure)" else period, onCard = true)
    }
}

@Composable
private fun CareerProjectItem(
    careerProject: CareerProject,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(careerProject.titleRes).keepWords(),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            lineHeight = 25.sp,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            careerProject.contributions.forEach { contribution ->
                Text(
                    text = contribution.keepWords(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 15.sp,
                    lineHeight = 15.sp.bodyLineHeight(),
                )
            }
        }
    }
}

private val PREVIOUS_HEADLINE_WIDTH = 400.dp

@Composable
private fun passOrderAccents(): List<Pair<String, Color>> {
    val pass = if (LocalThemeMode.current == ThemeMode.Dark) PassOrderNavyOnDark else PassOrderNavy
    return listOf("패스" to pass, "오더" to PassOrderOrange)
}
