package junjange.dev.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import junjange.dev.ui.PC_CONTENT_WIDTH
import junjange.dev.ui.component.LogoButton
import junjange.dev.ui.component.Stepper
import junjange.dev.ui.component.TagText
import junjange.dev.ui.model.Career
import junjange.dev.ui.state.contentPadding
import junjange.dev.ui.state.isPc
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                stringResource(Res.string.section_career),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp,
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Column(modifier = Modifier.width((PC_CONTENT_WIDTH / 2).dp)) {
            if (deviceState.isPc) {
                Career.entries.forEachIndexed { index, career ->
                    CareerPcStep(
                        career = career,
                        isFirst = index == 0,
                        isLast = index == Career.entries.lastIndex,
                    )
                }
            } else {
                Career.entries.forEachIndexed { index, career ->
                    CareerMobileStep(
                        career = career,
                        isFirst = index == 0,
                        isLast = index == Career.entries.lastIndex,
                    )
                }
            }
        }
    }
}

@Composable
private fun CareerPcStep(
    career: Career,
    isFirst: Boolean,
    isLast: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
    ) {
        TagText(
            text = career.date,
            textColor = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            height = TAG_TEXT_HEIGHT,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = (CAREER_LOGO_SIZE - CAREER_DOT_SIZE) / 2),
        )
        Spacer(Modifier.width(24.dp))
        Stepper(
            dotSize = CAREER_DOT_SIZE,
            frontHeight = (TAG_TEXT_HEIGHT - CAREER_DOT_SIZE) / 2 + (CAREER_LOGO_SIZE - CAREER_DOT_SIZE) / 2 - 8.dp,
            rearHeight = if (isLast) 192.dp else 224.dp,
            isHead = isFirst,
        )
        Spacer(Modifier.width(24.dp))
        LogoButton(
            logoRes = career.logoRes,
            url = career.url,
            size = CAREER_LOGO_SIZE,
        )
        Spacer(Modifier.width(24.dp))
        Column {
            Spacer(Modifier.height(12.dp))
            Text(
                text = stringResource(career.positionRes),
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.75f),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(career.teamRes),
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.8f),
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
            )
            Text(
                text = stringResource(career.descriptionRes),
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.8f),
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
            )
        }
    }
}

@Composable
private fun CareerMobileStep(
    career: Career,
    isFirst: Boolean,
    isLast: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
    ) {
        Stepper(
            dotSize = CAREER_DOT_SIZE,
            frontHeight = (TAG_TEXT_HEIGHT - CAREER_DOT_SIZE) / 2 + (CAREER_LOGO_SIZE - CAREER_DOT_SIZE) / 2 - 8.dp,
            rearHeight = if (isLast) 192.dp else 224.dp,
            isHead = isFirst,
        )
        Spacer(Modifier.width(24.dp))
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                LogoButton(
                    logoRes = career.logoRes,
                    url = career.url,
                    size = CAREER_LOGO_SIZE,
                )
                Spacer(Modifier.width(24.dp))
                TagText(
                    text = career.date,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    height = TAG_TEXT_HEIGHT,
                    color = MaterialTheme.colorScheme.primary,
                    inverse = true,
                )
            }
            Spacer(Modifier.height(12.dp))
            Text(
                text = stringResource(career.positionRes),
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.75f),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(career.teamRes),
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.8f),
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
            )
            Text(
                text = stringResource(career.descriptionRes),
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.8f),
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
            )
        }
    }
}

private val TAG_TEXT_HEIGHT = 28.dp
private val CAREER_DOT_SIZE = 24.dp
private val CAREER_LOGO_SIZE = 128.dp
