package junjange.dev.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.component.FeedbackCard
import junjange.dev.ui.component.SectionContainer
import junjange.dev.ui.component.SectionHeader
import junjange.dev.ui.component.reveal
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.Feedback
import junjange.dev.ui.model.peerFeedbacks
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
    val previews = feedbacks.take(PREVIEW_COUNT)

    SectionContainer(modifier = modifier) {
        SectionHeader(
            eyebrow = stringResource(Res.string.feedback_eyebrow),
            headline = stringResource(Res.string.feedback_preview_headline),
        )

        Spacer(modifier = Modifier.height(if (isMobile) 40.dp else 64.dp))

        if (isMobile) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                previews.forEach { FeedbackCard(feedback = it, isMobile = true, modifier = Modifier.fillMaxWidth().reveal()) }
            }
        } else {
            Row(
                modifier = Modifier.height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                previews.forEachIndexed { index, feedback ->
                    FeedbackCard(
                        feedback = feedback,
                        isMobile = false,
                        modifier = Modifier.weight(1f).fillMaxHeight().reveal(delayMillis = index * 100),
                        authorAtBottom = true,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(if (isMobile) 24.dp else 32.dp))

        TextButton(
            onClick = onSeeAll,
            modifier = Modifier.align(Alignment.CenterHorizontally).reveal(),
        ) {
            Text(
                text = stringResource(Res.string.feedback_see_all),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(22.dp),
            )
        }
    }
}

private const val PREVIEW_COUNT = 2
