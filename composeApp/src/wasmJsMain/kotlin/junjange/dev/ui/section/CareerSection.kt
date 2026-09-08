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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.component.CardImage
import junjange.dev.ui.component.Chip
import junjange.dev.ui.component.SectionContainer
import junjange.dev.ui.component.SectionHeader
import junjange.dev.ui.model.Career
import junjange.dev.ui.model.CareerProject
import junjange.dev.ui.model.Device
import junjange.dev.ui.state.rememberDeviceState
import junjange.dev.ui.theme.TITLE_LETTER_SPACING
import junjange.dev.ui.theme.bodyLineHeight
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.section_career
import org.jetbrains.compose.resources.stringResource

@Composable
fun CareerSection(modifier: Modifier = Modifier) {
    val deviceState = rememberDeviceState()
    val isDesktop = deviceState.value == Device.DESKTOP

    SectionContainer(modifier = modifier) {
        SectionHeader(title = stringResource(Res.string.section_career))

        Spacer(modifier = Modifier.height(if (isDesktop) 48.dp else 32.dp))

        Career.entries.forEachIndexed { index, career ->
            if (isDesktop) {
                CareerRow(career = career)
            } else {
                CareerColumn(career = career)
            }
            if (index != Career.entries.lastIndex) {
                Spacer(modifier = Modifier.height(if (isDesktop) ITEM_GAP else 64.dp))
            }
        }
    }
}

@Composable
private fun CareerRow(
    career: Career,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
    ) {
        Column(modifier = Modifier.width(HEADLINE_COLUMN_WIDTH)) {
            CareerLogo(career = career)
            Spacer(modifier = Modifier.height(20.dp))
            CareerTitle(career = career)
        }

        Spacer(modifier = Modifier.width(HEADLINE_COLUMN_GAP))

        Column(modifier = Modifier.weight(1f)) {
            CareerChips(career = career)
            Spacer(modifier = Modifier.height(24.dp))
            CareerProjects(career = career)
        }
    }
}

@Composable
private fun CareerColumn(
    career: Career,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        CareerLogo(career = career)
        Spacer(modifier = Modifier.height(20.dp))
        CareerChips(career = career)
        Spacer(modifier = Modifier.height(14.dp))
        CareerTitle(career = career)
        Spacer(modifier = Modifier.height(28.dp))
        CareerProjects(career = career)
    }
}

@Composable
private fun CareerLogo(career: Career) {
    CardImage(
        logo = career.logoRes,
        size = LOGO_SIZE,
        cornerRadius = 20.dp,
        contentPadding = PaddingValues(6.dp),
    )
}

@Composable
private fun CareerTitle(
    career: Career,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(career.nameRes),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = TITLE_LETTER_SPACING,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = stringResource(career.introRes),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 16.sp,
            lineHeight = 16.sp.bodyLineHeight(),
        )
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
        Chip(text = stringResource(career.periodRes))
    }
}

@Composable
private fun CareerProjects(
    career: Career,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        career.project.forEach { careerProject ->
            CareerProjectItem(careerProject = careerProject)
        }
    }
}

@Composable
private fun CareerProjectItem(careerProject: CareerProject) {
    val secondary = MaterialTheme.colorScheme.onSurfaceVariant

    Column {
        Text(
            text = stringResource(careerProject.titleRes),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            lineHeight = 26.sp,
        )
        careerProject.periodRes?.let { periodRes ->
            Text(
                text = stringResource(periodRes),
                color = secondary.copy(alpha = 0.7f),
                fontSize = 13.sp,
                lineHeight = 20.sp,
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            careerProject.contributions.forEach { contribution ->
                Text(
                    text = contribution,
                    color = secondary,
                    fontSize = 15.sp,
                    lineHeight = 15.sp.bodyLineHeight(),
                )
            }
        }
    }
}

private val ITEM_GAP = 96.dp
private val HEADLINE_COLUMN_WIDTH = 360.dp
private val HEADLINE_COLUMN_GAP = 64.dp
private val LOGO_SIZE = 72.dp
