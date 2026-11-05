package junjange.dev.ui.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import junjange.dev.ui.component.DefaultOutlinedButton
import junjange.dev.ui.component.FeedbackCard
import junjange.dev.ui.component.SectionContainer
import junjange.dev.ui.component.SectionHeader
import junjange.dev.ui.component.reveal
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.Feedback
import junjange.dev.ui.model.peerFeedbacks
import junjange.dev.ui.model.splitIntoColumns
import junjange.dev.ui.state.rememberDeviceState
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.feedback_eyebrow
import junjange_dev.composeapp.generated.resources.feedback_preview_headline
import junjange_dev.composeapp.generated.resources.feedback_see_all
import org.jetbrains.compose.resources.stringResource

@Composable
fun FeedbackPreviewSection(
    onSeeAll: () -> Unit,
    modifier: Modifier = Modifier,
    feedbacks: List<Feedback> = peerFeedbacks,
) {
    val device = rememberDeviceState().value
    if (device == Device.UNKNOWN) return
    val isMobile = device == Device.MOBILE
    val background = MaterialTheme.colorScheme.primaryContainer
    val gap = if (isMobile) 16.dp else 20.dp
    val firstCardHeights = remember(isMobile) { mutableStateMapOf<Int, Int>() }
    val firstRowHeight = with(LocalDensity.current) { (firstCardHeights.values.maxOrNull() ?: 0).toDp() }
    val previewHeight = if (firstRowHeight > 0.dp) firstRowHeight + gap + PEEK_HEIGHT else FALLBACK_HEIGHT
    val columns =
        if (isMobile) {
            listOf(feedbacks.take(PREVIEW_COUNT_MOBILE))
        } else {
            feedbacks.take(PREVIEW_COUNT_DESKTOP).splitIntoColumns(count = 2)
        }

    SectionContainer(modifier = modifier) {
        SectionHeader(
            eyebrow = stringResource(Res.string.feedback_eyebrow),
            headline = stringResource(Res.string.feedback_preview_headline),
        )

        Spacer(modifier = Modifier.height(if (isMobile) 40.dp else 64.dp))

        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(previewHeight)
                    .clipToBounds()
                    .reveal(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(align = Alignment.Top, unbounded = true),
                horizontalArrangement = Arrangement.spacedBy(gap),
            ) {
                columns.forEachIndexed { columnIndex, column ->
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(gap)) {
                        column.forEachIndexed { index, feedback ->
                            val measure = if (index == 0) Modifier.onSizeChanged { firstCardHeights[columnIndex] = it.height } else Modifier
                            FeedbackCard(feedback = feedback, isMobile = isMobile, modifier = Modifier.fillMaxWidth().then(measure))
                        }
                    }
                }
            }
            Box(
                modifier =
                    Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(FADE_HEIGHT)
                        .background(
                            Brush.verticalGradient(
                                0f to background.copy(alpha = 0f),
                                0.6f to background.copy(alpha = 0.9f),
                                1f to background,
                            ),
                        ).clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onSeeAll,
                        ),
            )
            DefaultOutlinedButton(
                text = stringResource(Res.string.feedback_see_all),
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = onSeeAll,
            )
        }
    }
}

private const val PREVIEW_COUNT_MOBILE = 3
private const val PREVIEW_COUNT_DESKTOP = 4
private val PEEK_HEIGHT = 180.dp
private val FADE_HEIGHT = 200.dp
private val FALLBACK_HEIGHT = 720.dp
