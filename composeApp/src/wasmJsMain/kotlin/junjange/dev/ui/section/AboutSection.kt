package junjange.dev.ui.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.component.SectionContainer
import junjange.dev.ui.component.SectionHeader
import junjange.dev.ui.model.AboutMe
import junjange.dev.ui.model.Device
import junjange.dev.ui.state.rememberDeviceState
import junjange.dev.ui.theme.TITLE_LETTER_SPACING
import junjange.dev.ui.theme.bodyLineHeight
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.section_about
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutSection(modifier: Modifier = Modifier) {
    val deviceState = rememberDeviceState()
    val isDesktop = deviceState.value == Device.DESKTOP

    SectionContainer(modifier = modifier) {
        SectionHeader(title = stringResource(Res.string.section_about))

        Spacer(modifier = Modifier.height(if (isDesktop) 56.dp else 32.dp))

        AboutMe.entries.forEachIndexed { index, aboutMe ->
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            AboutRow(
                index = index,
                aboutMe = aboutMe,
                isDesktop = isDesktop,
            )
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    }
}

@Composable
private fun AboutRow(
    index: Int,
    aboutMe: AboutMe,
    isDesktop: Boolean,
    modifier: Modifier = Modifier,
) {
    val rowPadding = if (isDesktop) 40.dp else 28.dp

    if (isDesktop) {
        Row(
            modifier = modifier.fillMaxWidth().padding(vertical = rowPadding),
            verticalAlignment = Alignment.Top,
        ) {
            AboutLabel(index = index, modifier = Modifier.width(LABEL_COLUMN_WIDTH))
            Spacer(modifier = Modifier.width(LABEL_COLUMN_GAP))
            AboutBody(aboutMe = aboutMe, modifier = Modifier.weight(1f, fill = false).widthIn(max = BODY_MAX_WIDTH))
        }
    } else {
        Column(modifier = modifier.fillMaxWidth().padding(vertical = rowPadding)) {
            AboutLabel(index = index)
            Spacer(modifier = Modifier.height(16.dp))
            AboutBody(aboutMe = aboutMe)
        }
    }
}

@Composable
private fun AboutLabel(
    index: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(
            modifier =
                Modifier
                    .size(8.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "0${index + 1}",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        )
    }
}

@Composable
private fun AboutBody(
    aboutMe: AboutMe,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(aboutMe.titleRes),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 30.sp,
            letterSpacing = TITLE_LETTER_SPACING,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(aboutMe.descriptionRes),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 17.sp,
            lineHeight = 17.sp.bodyLineHeight(),
        )
    }
}

private val BODY_MAX_WIDTH = 720.dp
private val LABEL_COLUMN_WIDTH = 240.dp
private val LABEL_COLUMN_GAP = 40.dp
