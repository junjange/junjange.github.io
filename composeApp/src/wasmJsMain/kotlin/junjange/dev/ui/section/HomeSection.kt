package junjange.dev.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.LocalScreenSize
import junjange.dev.ui.MOBILE_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.MOBILE_CONTENT_MIN_HEIGHT
import junjange.dev.ui.MOBILE_CONTENT_VERTICAL_PADDING
import junjange.dev.ui.PC_CONTENT_MIN_HEIGHT
import junjange.dev.ui.component.AnimatedArrow
import junjange.dev.ui.component.HEADER_HEIGHT
import junjange.dev.ui.model.Section
import junjange.dev.ui.state.DeviceState
import junjange.dev.ui.state.fontSize
import junjange.dev.ui.state.isMobile
import junjange.dev.ui.state.rememberDeviceState
import junjange.dev.ui.toDpSize
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
fun HomeSection(
    modifier: Modifier = Modifier,
    onSectionClicked: (Section) -> Unit,
) {
    val deviceState = rememberDeviceState()
    val nicknameString = buildNicknameString(deviceState = deviceState)

    HomePcSection(
        nicknameString = nicknameString,
        modifier =
            modifier.then(
                if (deviceState.isMobile) {
                    Modifier.height(0.dp)
                } else {
                    Modifier
                },
            ),
        onSectionClicked = onSectionClicked,
    )

    if (deviceState.isMobile) {
        HomeMobileSection(
            nicknameString = nicknameString,
            modifier = modifier,
            onSectionClicked = onSectionClicked,
        )
    }
}

@Composable
private fun buildNicknameString(deviceState: DeviceState): AnnotatedString =
    buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontSize = deviceState.fontSize(),
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
private fun HomeMobileSection(
    nicknameString: AnnotatedString,
    modifier: Modifier = Modifier,
    onSectionClicked: (Section) -> Unit,
) {
    val screenSize = LocalScreenSize.current.toDpSize()

    Box(
        modifier =
            modifier
                .width(screenSize.width.dp)
                .height((maxOf(MOBILE_CONTENT_MIN_HEIGHT, screenSize.height) - HEADER_HEIGHT).dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier =
                    Modifier
                        .padding(top = MOBILE_CONTENT_VERTICAL_PADDING.dp)
                        .padding(horizontal = MOBILE_CONTENT_HORIZONTAL_PADDING.dp),
            ) {
                Text(
                    text = nicknameString,
                    lineHeight = 76.sp,
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(Res.string.intro),
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(36.dp))

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

        AnimatedArrow(
            modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp),
            onClick = { onSectionClicked(Section.AboutMe) },
        )
    }
}

@Composable
private fun HomePcSection(
    nicknameString: AnnotatedString,
    modifier: Modifier = Modifier,
    onSectionClicked: (Section) -> Unit,
) {
    val screenSize = LocalScreenSize.current.toDpSize()

    Box(
        modifier =
            modifier.then(
                Modifier
                    .width(screenSize.width.dp)
                    .height((maxOf(PC_CONTENT_MIN_HEIGHT, screenSize.height) - HEADER_HEIGHT).dp),
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = nicknameString, lineHeight = 92.sp)

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(Res.string.intro),
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(36.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                GithubButton()
                BlogButton()
            }
        }

        AnimatedArrow(
            modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp),
            onClick = { onSectionClicked(Section.AboutMe) },
        )
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
