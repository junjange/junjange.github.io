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
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.component.CardImage
import junjange.dev.ui.component.TimelineIndicator
import junjange.dev.ui.model.Career
import junjange.dev.ui.model.CareerProject
import junjange.dev.ui.model.Device
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

        val isDesktop = deviceState.value == Device.DESKTOP

        Career.entries.forEachIndexed { index, career ->
            if (isDesktop) {
                CareerContentTwoColumn(
                    career = career,
                    isFirst = index == 0,
                )
            } else {
                CareerContent(
                    career = career,
                    isFirst = index == 0,
                )
            }
        }
    }
}

// 데스크탑: 타임라인 + 좌(회사 정보) / 우(프로젝트 상세) 2단 레이아웃
@Composable
private fun CareerContentTwoColumn(
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

        CareerInfo(
            career = career,
            modifier = Modifier.width(CAREER_INFO_COLUMN_WIDTH),
        )

        Spacer(Modifier.width(32.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            career.project.forEach { careerProject ->
                CareerProjectItem(careerProject = careerProject)
                Spacer(Modifier.height(24.dp))
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

// 회사 로고 + 이름/소개/직책/기간
@Composable
private fun CareerInfo(
    career: Career,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
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
}

// 모바일/태블릿: 기존 타임라인 + 세로 나열
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
            CareerInfo(career = career)

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
    val highlightColor = MaterialTheme.colorScheme.onPrimaryContainer
    val baseColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.8f)

    Text(
        text = stringResource(careerProject.titleRes),
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    )
    careerProject.periodRes?.let {
         Text(
            text = stringResource(careerProject.periodRes),
            color = baseColor,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
        )
    }

    careerProject.contributions.forEach { contribution ->
        val annotatedString = buildAnnotatedString {
            withStyle(
                style = ParagraphStyle(textIndent = TextIndent(restLine = 14.sp)),
            ) {
                append("• ")
                contribution.segments.forEach { segment ->
                    withStyle(
                        style = SpanStyle(
                            color = if (segment.isHighlighted) highlightColor else baseColor,
                            fontWeight = if (segment.isHighlighted) FontWeight.Bold else FontWeight.Normal,
                        )
                    ) {
                        append(segment.text)
                    }
                }
            }
        }

        Text(
            text = annotatedString,
            fontSize = 14.sp,
            lineHeight = 20.sp,
        )
    }
}

private val CAREER_DOT_SIZE = 24.dp
private val CAREER_LOGO_SIZE = 128.dp
private val CAREER_INFO_COLUMN_WIDTH = 420.dp
