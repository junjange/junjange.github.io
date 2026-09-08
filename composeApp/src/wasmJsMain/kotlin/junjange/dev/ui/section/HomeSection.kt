package junjange.dev.ui.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.CONTENT_MAX_WIDTH
import junjange.dev.ui.DESKTOP_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.DESKTOP_CONTENT_WIDTH
import junjange.dev.ui.MOBILE_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.MOBILE_CONTENT_MIN_HEIGHT
import junjange.dev.ui.MOBILE_CONTENT_VERTICAL_PADDING
import junjange.dev.ui.TABLET_CONTENT_MIN_HEIGHT
import junjange.dev.ui.component.AnimatedArrow
import junjange.dev.ui.component.HEADER_HEIGHT
import junjange.dev.ui.component.LoadingScreen
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.LocalScreenSize
import junjange.dev.ui.model.LocalThemeMode
import junjange.dev.ui.model.ThemeMode
import junjange.dev.ui.model.Section
import junjange.dev.ui.model.asDp
import junjange.dev.ui.state.DeviceState
import junjange.dev.ui.state.rememberDeviceState
import junjange.dev.ui.state.titleFontSize
import junjange.dev.ui.theme.TITLE_LETTER_SPACING
import junjange.dev.ui.theme.bodyLineHeight
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.blue_chip
import junjange_dev.composeapp.generated.resources.follow_github
import junjange_dev.composeapp.generated.resources.ic_github
import junjange_dev.composeapp.generated.resources.ic_home_graphic
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

    when (deviceState.value) {
        Device.UNKNOWN -> {
            Box(modifier = modifier.fillMaxSize()) {
                LoadingScreen()
            }
        }

        Device.DESKTOP ->
            HomeDesktopSection(
                nicknameString = nicknameString,
                modifier = modifier,
                onSectionClicked = onSectionClicked,
            )

        Device.TABLET, Device.MOBILE ->
            HomeCompactSection(
                nicknameString = nicknameString,
                modifier = modifier,
                stackButtons = deviceState.value == Device.MOBILE,
            )
    }
}

@Composable
private fun buildNicknameString(deviceState: DeviceState): AnnotatedString =
    buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontSize = deviceState.titleFontSize(),
                fontWeight = FontWeight.Bold,
                letterSpacing = TITLE_LETTER_SPACING,
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
private fun heroGlowAlpha(): Float = if (LocalThemeMode.current == ThemeMode.Dark) 0.22f else 0.10f

private fun Modifier.heroGlow(color: Color): Modifier =
    drawBehind {
        drawRect(
            brush =
                Brush.radialGradient(
                    colors = listOf(color, Color.Transparent),
                    center = Offset(size.width * 0.72f, size.height * 0.42f),
                    radius = size.height * 0.55f,
                ),
        )
    }

@Composable
private fun HomeCompactSection(
    nicknameString: AnnotatedString,
    stackButtons: Boolean,
    modifier: Modifier = Modifier,
) {
    val screenSize = LocalScreenSize.current.asDp()
    val glow = MaterialTheme.colorScheme.primary.copy(alpha = heroGlowAlpha())

    Box(
        modifier =
            modifier
                .fillMaxSize()
                .heroGlow(glow)
                .padding(
                    vertical = MOBILE_CONTENT_VERTICAL_PADDING.dp,
                    horizontal = MOBILE_CONTENT_HORIZONTAL_PADDING.dp,
                ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_home_graphic),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier =
                    Modifier.height(
                        maxOf(
                            MOBILE_CONTENT_MIN_HEIGHT,
                            screenSize.height,
                        ).dp / 3f,
                    ),
            )

            Spacer(modifier = Modifier.height(HOME_IMAGE_TEXT_GAP))

            Text(
                text = nicknameString,
                lineHeight = 76.sp,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(Res.string.intro),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 16.sp,
                lineHeight = 16.sp.bodyLineHeight(),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(36.dp))

            if (stackButtons) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    GithubButton(modifier = Modifier.fillMaxWidth())
                    BlogButton(modifier = Modifier.fillMaxWidth())
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    GithubButton()
                    BlogButton()
                }
            }
        }
    }
}

@Composable
private fun HomeDesktopSection(
    nicknameString: AnnotatedString,
    modifier: Modifier = Modifier,
    onSectionClicked: (Section) -> Unit,
) {
    val screenSize = LocalScreenSize.current.asDp()
    val horizontalPadding = DESKTOP_CONTENT_HORIZONTAL_PADDING.dp
    val contentWidth = minOf(CONTENT_MAX_WIDTH.dp, screenSize.width.dp - horizontalPadding * 2)
    val textWidth = contentWidth * HOME_TEXT_WIDTH_RATIO
    val imageWidth = minOf(DESKTOP_CONTENT_WIDTH.dp / 2, contentWidth - textWidth - HOME_COLUMN_GAP)
    val glow = MaterialTheme.colorScheme.primary.copy(alpha = heroGlowAlpha())

    Box(
        modifier =
            modifier.then(
                Modifier
                    .width(screenSize.width.dp)
                    .height((maxOf(TABLET_CONTENT_MIN_HEIGHT, screenSize.height) - HEADER_HEIGHT).dp)
                    .heroGlow(glow)
                    .padding(horizontal = horizontalPadding),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier =
                Modifier
                    .width(contentWidth)
                    .fillMaxHeight(),
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_home_graphic),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier =
                    Modifier
                        .width(imageWidth)
                        .align(Alignment.CenterEnd),
            )

            Column(
                modifier =
                    Modifier
                        .width(textWidth)
                        .align(Alignment.CenterStart),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = nicknameString, lineHeight = 76.sp)

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(Res.string.intro),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 17.sp,
                    lineHeight = 17.sp.bodyLineHeight(),
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
        }

        AnimatedArrow(
            modifier =
                Modifier
                    .size(128.dp)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp),
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
        modifier = modifier.then(Modifier.height(52.dp)),
        shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
        elevation = null,
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 22.dp),
    ) {
        Icon(
            painterResource(Res.drawable.ic_github),
            contentDescription = null,
            modifier = Modifier.size(22.dp),
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = stringResource(Res.string.follow_github),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
    }
}

@Composable
private fun BlogButton(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    val blogUri = "https://fre2-dom.tistory.com"

    Button(
        onClick = { uriHandler.openUri(blogUri) },
        modifier = modifier.then(Modifier.height(52.dp)),
        shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
        elevation = null,
        colors =
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            ),
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 22.dp),
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_open_in_new),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = stringResource(Res.string.visit_blog),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
    }
}

private val BUTTON_CORNER_RADIUS = 12.dp
private val HOME_COLUMN_GAP = 24.dp
private val HOME_IMAGE_TEXT_GAP = 24.dp
private const val HOME_TEXT_WIDTH_RATIO = 0.53f
