package junjange.dev.ui.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.component.CardImage
import junjange.dev.ui.model.Community
import junjange.dev.ui.model.Education
import junjange.dev.ui.model.Etc
import junjange.dev.ui.model.Experience
import junjange.dev.ui.state.contentPadding
import junjange.dev.ui.state.rememberDeviceState
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.experience
import junjange_dev.composeapp.generated.resources.experience_community
import junjange_dev.composeapp.generated.resources.experience_education
import junjange_dev.composeapp.generated.resources.experience_etc
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExperienceSection(modifier: Modifier = Modifier) {
    val deviceState = rememberDeviceState()

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(deviceState.contentPadding()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.experience),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = stringResource(Res.string.experience_education),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Education.entries.forEach { experience ->
            ExperienceItem(experience = experience)
            Spacer(modifier = Modifier.height(24.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(Res.string.experience_community),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Community.entries.forEach { experience ->
            ExperienceItem(experience = experience)
            Spacer(modifier = Modifier.height(24.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(Res.string.experience_etc),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column {
            Etc.entries.forEach { mentor ->
                EtcText(mentor)
                Spacer(Modifier.height(4.dp))
            }
        }
    }
}

@Composable
private fun ExperienceItem(
    experience: Experience,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current

    Column(modifier = modifier) {
        Row {
            CardImage(
                logo = experience.logoRes,
                size = 92.dp,
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(experience.titleRes),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
                Text(
                    text = stringResource(experience.subtitleRes),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                )
                Text(
                    text = stringResource(experience.periodRes),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    experience.links.forEachIndexed { index, (label, url) ->
                        Text(
                            text = stringResource(label),
                            fontSize = 14.sp,
                            modifier = Modifier.clickable { uriHandler.openUri(url) },
                            style =
                                MaterialTheme.typography.bodySmall.copy(
                                    textDecoration = TextDecoration.Underline,
                                    color =
                                        MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                            alpha = 0.8f,
                                        ),
                                ),
                        )
                        if (index != experience.links.lastIndex) {
                            Text(
                                text = " | ",
                                fontSize = 14.sp,
                                color =
                                    MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                        alpha = 0.8f,
                                    ),
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(experience.descriptionRes),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
        )
    }
}

@Composable
private fun EtcText(
    etc: Etc,
    modifier: Modifier = Modifier,
) {
    val text =
        buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.8f),
                ),
            ) {
                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.25f),
                    ),
                ) {
                    append(" • ")
                }

                append(stringResource(etc.titleRes))
                append(" ")

                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.5f),
                        fontSize = 12.sp,
                    ),
                ) {
                    append(stringResource(etc.periodRes))
                }
            }
        }
    Text(text = text, modifier = modifier)
}
