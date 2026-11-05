package junjange.dev.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.CONTENT_MAX_WIDTH
import junjange.dev.ui.component.FeedbackCard
import junjange.dev.ui.component.HEADER_HEIGHT
import junjange.dev.ui.component.LogoImage
import junjange.dev.ui.component.SectionContainer
import junjange.dev.ui.component.SurfaceCard
import junjange.dev.ui.component.reveal
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.Feedback
import junjange.dev.ui.model.LocalThemeMode
import junjange.dev.ui.model.ThemeMode
import junjange.dev.ui.model.FEEDBACK_FORM_URL
import junjange.dev.ui.model.peerFeedbacks
import junjange.dev.ui.model.splitIntoColumns
import junjange.dev.ui.section.SiteFooter
import junjange.dev.ui.state.contentPadding
import junjange.dev.ui.state.rememberDeviceState
import junjange.dev.ui.state.sectionTitleFontSize
import junjange.dev.ui.theme.TITLE_LETTER_SPACING
import junjange.dev.ui.theme.bodyLineHeight
import junjange.dev.ui.theme.keepWords
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.feedback_cta_description
import junjange_dev.composeapp.generated.resources.feedback_cta_title
import junjange_dev.composeapp.generated.resources.feedback_description
import junjange_dev.composeapp.generated.resources.feedback_eyebrow
import junjange_dev.composeapp.generated.resources.feedback_headline
import junjange_dev.composeapp.generated.resources.feedback_total
import junjange_dev.composeapp.generated.resources.feedback_write
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FeedbackScreen(
    onBack: () -> Unit,
    onThemeChanged: (ThemeMode) -> Unit,
    modifier: Modifier = Modifier,
    feedbacks: List<Feedback> = peerFeedbacks,
) {
    val uriHandler = LocalUriHandler.current
    val onWrite = { uriHandler.openUri(FEEDBACK_FORM_URL) }

    Scaffold(
        modifier = modifier,
        topBar = { FeedbackTopBar(onBack = onBack, onThemeChanged = onThemeChanged) },
    ) { innerPadding ->
        LazyColumn(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item { FeedbackHero(onWrite = onWrite) }
            item { FeedbackList(feedbacks = feedbacks) }
            item { FeedbackCallToAction(onWrite = onWrite) }
            item { SiteFooter() }
        }
    }
}

@Composable
private fun FeedbackTopBar(
    onBack: () -> Unit,
    onThemeChanged: (ThemeMode) -> Unit,
) {
    val themeMode = LocalThemeMode.current
    val horizontalPadding = rememberDeviceState().contentPadding().calculateStartPadding(LayoutDirection.Ltr)

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(HEADER_HEIGHT.dp)
                .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center,
    ) {
        HorizontalDivider(
            modifier = Modifier.align(Alignment.BottomCenter),
            color = MaterialTheme.colorScheme.outlineVariant,
        )
        Row(
            modifier =
                Modifier
                    .padding(horizontal = (horizontalPadding - 12.dp).coerceAtLeast(0.dp))
                    .widthIn(max = CONTENT_MAX_WIDTH.dp + 24.dp)
                    .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                }
                LogoImage(onClick = onBack)
            }
            IconButton(onClick = { onThemeChanged(themeMode.toggle()) }) {
                Icon(
                    painter = painterResource(themeMode.iconRes),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

@Composable
private fun FeedbackHero(onWrite: () -> Unit) {
    val deviceState = rememberDeviceState()
    val isMobile = deviceState.value == Device.MOBILE
    val titleSize = deviceState.sectionTitleFontSize()
    val horizontalPadding = deviceState.contentPadding().calculateStartPadding(LayoutDirection.Ltr)

    SectionContainer(
        contentPadding =
            PaddingValues(
                start = horizontalPadding,
                end = horizontalPadding,
                top = if (isMobile) 56.dp else 96.dp,
                bottom = if (isMobile) 40.dp else 64.dp,
            ),
    ) {
        Column(modifier = Modifier.reveal()) {
            Text(
                text = stringResource(Res.string.feedback_eyebrow),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(Res.string.feedback_headline),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = titleSize,
                lineHeight = (titleSize.value * 1.35f).sp,
                letterSpacing = TITLE_LETTER_SPACING,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(Res.string.feedback_description).keepWords(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = if (isMobile) 16.sp else 18.sp,
                lineHeight = (if (isMobile) 16.sp else 18.sp).bodyLineHeight(),
            )
            Spacer(modifier = Modifier.height(32.dp))
            WriteButton(onClick = onWrite)
        }
    }
}

@Composable
private fun FeedbackList(feedbacks: List<Feedback>) {
    val deviceState = rememberDeviceState()
    if (deviceState.value == Device.UNKNOWN) return
    val isMobile = deviceState.value == Device.MOBILE
    val horizontalPadding = deviceState.contentPadding().calculateStartPadding(LayoutDirection.Ltr)

    SectionContainer(
        contentPadding = PaddingValues(start = horizontalPadding, end = horizontalPadding, bottom = if (isMobile) 40.dp else 64.dp),
    ) {
        Text(
            text = stringResource(Res.string.feedback_total, feedbacks.size),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = if (isMobile) 18.sp else 20.sp,
            letterSpacing = TITLE_LETTER_SPACING,
            modifier = Modifier.reveal(),
        )
        Spacer(modifier = Modifier.height(20.dp))

        if (isMobile) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                feedbacks.forEach { FeedbackCard(feedback = it, isMobile = true, modifier = Modifier.fillMaxWidth().reveal()) }
            }
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                feedbacks.splitIntoColumns(count = 2).forEach { column ->
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        column.forEach { FeedbackCard(feedback = it, isMobile = false, modifier = Modifier.fillMaxWidth().reveal()) }
                    }
                }
            }
        }
    }
}

@Composable
private fun FeedbackCallToAction(onWrite: () -> Unit) {
    val deviceState = rememberDeviceState()
    val isMobile = deviceState.value == Device.MOBILE
    val horizontalPadding = deviceState.contentPadding().calculateStartPadding(LayoutDirection.Ltr)

    SectionContainer(
        contentPadding = PaddingValues(start = horizontalPadding, end = horizontalPadding, bottom = if (isMobile) 72.dp else 120.dp),
    ) {
        SurfaceCard(
            modifier = Modifier.fillMaxWidth().reveal(),
            padding = if (isMobile) 32.dp else 64.dp,
        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(Res.string.feedback_cta_title),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (isMobile) 24.sp else 32.sp,
                    letterSpacing = TITLE_LETTER_SPACING,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(Res.string.feedback_cta_description).keepWords(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = if (isMobile) 16.sp else 17.sp,
                    lineHeight = (if (isMobile) 16.sp else 17.sp).bodyLineHeight(),
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(32.dp))
                WriteButton(onClick = onWrite)
            }
        }
    }
}

@Composable
private fun WriteButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.height(52.dp),
        shape = RoundedCornerShape(14.dp),
        elevation = null,
        contentPadding = PaddingValues(horizontal = 22.dp),
    ) {
        Text(text = stringResource(Res.string.feedback_write), fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
    }
}
