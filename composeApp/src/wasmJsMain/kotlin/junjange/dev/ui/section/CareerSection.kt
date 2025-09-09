package junjange.dev.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import junjange.dev.ui.component.TimelineIndicator
import junjange.dev.ui.model.Career
import junjange.dev.ui.model.CareerProject
import junjange.dev.ui.state.contentPadding
import junjange.dev.ui.state.rememberDeviceState
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.section_career
import org.jetbrains.compose.resources.stringResource

@Composable
fun CareerSection(modifier: Modifier = Modifier) {
    val deviceState = rememberDeviceState()

    Column(
        modifier =
            modifier
                .padding(deviceState.contentPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            stringResource(Res.string.section_career),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 36.sp,
        )

        Spacer(modifier = Modifier.height(48.dp))

        Career.entries.forEachIndexed { index, career ->
            CareerContent(
                career = career,
                isFirst = index == 0,
            )
        }
    }
}

@Composable
private fun CareerContent(
    career: Career,
    isFirst: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.Top,
    ) {
        TimelineIndicator(
            isFirst = isFirst,
            frontHeight = ((CAREER_LOGO_SIZE - CAREER_DOT_SIZE) / 2) - 8.dp,
            dotSize = CAREER_DOT_SIZE,
        )

        Spacer(Modifier.width(24.dp))

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CardImage(
                    logo = career.logoRes,
                    size = CAREER_LOGO_SIZE,
                    cornerRadius = 32.dp,
                    elevation = 24.dp,
                    contentPadding = PaddingValues(12.dp),
                )

                Spacer(Modifier.width(24.dp))

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = stringResource(career.nameRes),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                    Text(
                        text = stringResource(career.introRes),
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.8f),
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                    )
                    Text(
                        text = stringResource(career.teamRes),
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.8f),
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                    )
                    Text(
                        text = stringResource(career.periodRes),
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.8f),
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            career.project.forEach { careerProject ->
                CareerProjectItem(careerProject = careerProject)
                Spacer(Modifier.height(24.dp))
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CareerProjectItem(careerProject: CareerProject) {
    Text(
        text = stringResource(careerProject.titleRes),
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    )
    Text(
        text = stringResource(careerProject.periodRes),
        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.8f),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    )
    Text(
        text = stringResource(careerProject.techStackRes),
        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.8f),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    )
    Text(
        text = stringResource(careerProject.contributionsRes),
        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.8f),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    )
}

private val CAREER_DOT_SIZE = 24.dp
private val CAREER_LOGO_SIZE = 128.dp
