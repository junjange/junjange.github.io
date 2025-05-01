package junjange.dev.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.LocalThemeMode
import junjange.dev.ui.PC_CONTENT_WIDTH
import junjange.dev.ui.ThemeMode
import junjange.dev.ui.model.Section
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopHeader(
    modifier: Modifier = Modifier,
    onThemeChanged: (ThemeMode) -> Unit,
    onSectionClicked: (Section) -> Unit,
) {
    val themeMode = LocalThemeMode.current

    Row(
        modifier =
            modifier.fillMaxWidth().padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier =
                Modifier
                    .width(PC_CONTENT_WIDTH.dp)
                    .height(HEADER_HEIGHT.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.spacedBy(48.dp),
            ) {
                Section.entries.forEach {
                    TextButton(
                        onClick = { onSectionClicked(it) },
                    ) {
                        Text(
                            text = stringResource(it.title),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 15.sp,
                        )
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            IconButton(onClick = { onThemeChanged(themeMode.toggle()) }) {
                Icon(
                    painter = painterResource(themeMode.iconRes),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

private const val HEADER_HEIGHT = 72
