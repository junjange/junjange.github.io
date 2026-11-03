package junjange.dev.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.state.rememberDeviceState
import junjange.dev.ui.state.sectionTitleFontSize
import junjange.dev.ui.theme.TITLE_LETTER_SPACING
import junjange.dev.ui.theme.keepWords

@Composable
fun SectionHeader(
    eyebrow: String,
    headline: String,
    modifier: Modifier = Modifier,
    accents: List<Pair<String, Color>> = emptyList(),
) {
    val titleSize = rememberDeviceState().sectionTitleFontSize()

    Column(
        modifier = modifier.fillMaxWidth().reveal(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = eyebrow,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = headline.keepWords(accents),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = titleSize,
            lineHeight = (titleSize.value * 1.35f).sp,
            letterSpacing = TITLE_LETTER_SPACING,
            textAlign = TextAlign.Center,
        )
    }
}
