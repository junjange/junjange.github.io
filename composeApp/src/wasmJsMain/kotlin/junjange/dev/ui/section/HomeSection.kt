package junjange.dev.ui.section

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.MOBILE_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.MOBILE_CONTENT_VERTICAL_PADDING
import junjange.dev.ui.PC_CONTENT_WIDTH
import junjange.dev.ui.component.SectionColumn
import junjange.dev.ui.component.defaultEnterAnim
import junjange.dev.ui.model.Device
import junjange.dev.ui.state.DeviceState
import junjange.dev.ui.state.isMobile
import junjange.dev.ui.state.rememberDeviceState
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.blue_chip
import junjange_dev.composeapp.generated.resources.follow_github
import junjange_dev.composeapp.generated.resources.ic_github
import junjange_dev.composeapp.generated.resources.ic_open_in_new
import junjange_dev.composeapp.generated.resources.intro
import junjange_dev.composeapp.generated.resources.jojunjang
import junjange_dev.composeapp.generated.resources.visit_blog
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeSection(modifier: Modifier = Modifier) {
    val visibleState =
        rememberSaveable {
            MutableTransitionState(false).apply {
                targetState = true
            }
        }
    val deviceState = rememberDeviceState()
    val nicknameString = buildNicknameString(deviceState = deviceState)

    Box {
        HomePcSection(
            nicknameString = nicknameString,
            visibleState = visibleState,
            modifier =
                modifier.then(
                    if (deviceState.isMobile) {
                        Modifier.height(1.dp)
                    } else {
                        Modifier
                    },
                ),
        )
        if (deviceState.isMobile) {
            HomeMobileSection(
                nicknameString = nicknameString,
                deviceState = deviceState,
                visibleState = visibleState,
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun buildNicknameString(deviceState: DeviceState): AnnotatedString =
    buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontSize = getFontSize(deviceState),
                fontWeight = FontWeight.Black,
            ),
        ) {
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.onPrimaryContainer)) {
                append(stringResource(Res.string.blue_chip))
            }

            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append(stringResource(Res.string.jojunjang))
            }
        }
    }

@Composable
private fun getFontSize(deviceState: DeviceState): TextUnit =
    when (deviceState.value) {
        Device.PC -> 72.sp
        Device.MOBILE -> 48.sp
    }

@Composable
private fun HomeMobileSection(
    nicknameString: AnnotatedString,
    visibleState: MutableTransitionState<Boolean>,
    deviceState: DeviceState,
    modifier: Modifier = Modifier,
) {
    SectionColumn(
        modifier = modifier,
        deviceState = deviceState,
        contentPadding = PaddingValues(bottom = 24.dp),
        content = {
            AnimatedVisibility(
                visibleState = visibleState,
                enter =
                    defaultEnterAnim(
                        delayMillis = 500,
                        orientation = Orientation.Horizontal,
                        inverseSlide = true,
                    ),
                modifier = Modifier.padding(bottom = 12.dp),
            ) {
                Column(
                    modifier =
                        Modifier
                            .padding(top = MOBILE_CONTENT_VERTICAL_PADDING.dp)
                            .padding(horizontal = MOBILE_CONTENT_HORIZONTAL_PADDING.dp),
                ) {
                    Text(
                        text = nicknameString,
                        lineHeight =
                            when (deviceState.value) {
                                Device.PC -> 92.sp
                                Device.MOBILE -> 76.sp
                            },
                    )
                }
            }
            AnimatedVisibility(
                visibleState = visibleState,
                enter = defaultEnterAnim(delayMillis = 500),
                modifier = Modifier.padding(bottom = 36.dp),
            ) {
                Text(
                    text = stringResource(Res.string.intro),
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Center,
                )
            }

            AnimatedVisibility(
                visibleState = visibleState,
                enter =
                    defaultEnterAnim(
                        delayMillis = 500,
                        inverseSlide = true,
                    ),
            ) {
                Column(
                    modifier =
                        Modifier.padding(
                            horizontal = MOBILE_CONTENT_HORIZONTAL_PADDING.dp,
                        ),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    GithubButton(modifier = Modifier.fillMaxWidth())
                    BlogButton(modifier = Modifier.fillMaxWidth())
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        },
    )
}

@Composable
private fun HomePcSection(
    nicknameString: AnnotatedString,
    visibleState: MutableTransitionState<Boolean>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier.then(
                Modifier
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .width(PC_CONTENT_WIDTH.dp),
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            AnimatedVisibility(
                visibleState = visibleState,
                enter = defaultEnterAnim(delayMillis = 500, inverseSlide = true),
                modifier = Modifier.padding(bottom = 12.dp),
            ) {
                Text(text = nicknameString, lineHeight = 92.sp)
            }
            AnimatedVisibility(
                visibleState = visibleState,
                enter = defaultEnterAnim(delayMillis = 500),
                modifier = Modifier.padding(bottom = 36.dp),
            ) {
                Text(
                    text = stringResource(Res.string.intro),
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Center,
                )
            }
            AnimatedVisibility(
                visibleState = visibleState,
                enter =
                    defaultEnterAnim(
                        orientation = Orientation.Horizontal,
                        inverseSlide = true,
                        delayMillis = 500,
                    ),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    GithubButton()
                    BlogButton()
                }
            }
            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}

@Composable
private fun GithubButton(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    val githubUri = "https://github.com/junjange"

    Button(
        onClick = { uriHandler.openUri(githubUri) },
        modifier = modifier.then(Modifier.height(56.dp)),
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 24.dp),
    ) {
        Text(
            text = stringResource(Res.string.follow_github),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            painterResource(Res.drawable.ic_github),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
    }
}

@Composable
private fun BlogButton(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    val blogUri = "https://fre2-dom.tistory.com"

    OutlinedButton(
        onClick = { uriHandler.openUri(blogUri) },
        modifier = modifier.then(Modifier.height(56.dp)),
        colors =
            ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 24.dp),
    ) {
        Text(
            text = stringResource(Res.string.visit_blog),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            painter = painterResource(Res.drawable.ic_open_in_new),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
    }
}
