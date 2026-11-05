package junjange.dev.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.model.Feedback
import junjange.dev.ui.model.FeedbackAuthor
import junjange.dev.ui.model.FeedbackPlace
import junjange.dev.ui.theme.bodyLineHeight
import junjange.dev.ui.theme.keepWords
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.feedback_growth_label
import junjange_dev.composeapp.generated.resources.feedback_specter_author
import org.jetbrains.compose.resources.stringResource

@Composable
fun FeedbackCard(
    feedback: Feedback,
    isMobile: Boolean,
    modifier: Modifier = Modifier,
    authorAtBottom: Boolean = false,
) {
    val bodySize = if (isMobile) 15.sp else 16.sp

    SurfaceCard(modifier = modifier, padding = if (isMobile) 20.dp else 28.dp) {
        Text(
            text = feedback.strength.keepWords(),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = bodySize,
            lineHeight = bodySize.bodyLineHeight(),
        )
        feedback.growth?.let { growth ->
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier =
                    Modifier
                        .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(16.dp))
                        .padding(if (isMobile) 14.dp else 16.dp),
            ) {
                Text(
                    text = stringResource(Res.string.feedback_growth_label),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = growth.keepWords(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp,
                    lineHeight = 14.sp.bodyLineHeight(),
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (authorAtBottom) Spacer(modifier = Modifier.weight(1f))
        FeedbackAuthorRow(feedback = feedback)
    }
}

@Composable
private fun FeedbackAuthorRow(feedback: Feedback) {
    val uriHandler = LocalUriHandler.current
    val author = feedback.author
    val relation = stringResource(feedback.relation.labelRes)
    val place = stringResource(feedback.place.labelRes)
    val specter = stringResource(Res.string.feedback_specter_author)
    val placePeriod = feedback.place.periodRes?.let { stringResource(it) }
    val years = (feedback.period ?: placePeriod)?.toYearRange()
    val title = if (feedback.place == FeedbackPlace.Other) relation else "$place $relation"
    val source =
        when (author) {
            is FeedbackAuthor.GitHub -> "@${author.login}"
            FeedbackAuthor.Specter -> specter
        }
    val subtitle = listOfNotNull(years, source).joinToString(" · ")

    val rowModifier =
        when (author) {
            is FeedbackAuthor.GitHub ->
                Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { uriHandler.openUri(author.profileUrl) }
                    .pointerHoverIcon(PointerIcon.Hand)
            FeedbackAuthor.Specter -> Modifier
        }

    Row(modifier = rowModifier, verticalAlignment = Alignment.CenterVertically) {
        when (author) {
            is FeedbackAuthor.GitHub -> GitHubAvatar(author = author)
            FeedbackAuthor.Specter -> PlaceAvatar(place = feedback.place)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.padding(end = 8.dp)) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                lineHeight = 20.sp,
            )
            Text(
                text = subtitle,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 13.sp,
                lineHeight = 18.sp,
            )
        }
    }
}

@Composable
private fun GitHubAvatar(author: FeedbackAuthor.GitHub) {
    val image = rememberRemoteImage(author.avatarUrl)
    Box(
        modifier =
            Modifier
                .size(AVATAR_SIZE.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)),
        contentAlignment = Alignment.Center,
    ) {
        if (image != null) {
            Image(bitmap = image, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.size(AVATAR_SIZE.dp))
        } else {
            Text(
                text = author.login.take(1).uppercase(),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
            )
        }
    }
}

@Composable
private fun PlaceAvatar(place: FeedbackPlace) {
    val logo =
        when (place) {
            is FeedbackPlace.Company -> place.career.logoRes
            is FeedbackPlace.Activity -> place.experience.logoRes
            else -> null
        }
    if (logo != null) {
        CardImage(logo = logo, size = AVATAR_SIZE.dp, cornerRadius = 12.dp, contentPadding = PaddingValues(0.dp))
    } else {
        Box(
            modifier =
                Modifier
                    .size(AVATAR_SIZE.dp)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f), CircleShape),
        )
    }
}

private fun String.toYearRange(): String? {
    val years = Regex("""\d{4}""").findAll(this).map { it.value }.distinct().toList()
    val ongoing = contains("재직 중") || contains("현재")
    return when {
        years.isEmpty() -> null
        ongoing -> "${years.first()} – 현재"
        years.size == 1 -> years.first()
        else -> "${years.first()} – ${years.last()}"
    }
}

private const val AVATAR_SIZE = 40
