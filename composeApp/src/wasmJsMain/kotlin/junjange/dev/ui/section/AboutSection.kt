package junjange.dev.ui.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.PC_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.model.AboutMe
import junjange.dev.ui.model.Device
import junjange.dev.ui.theme.DarkGray
import junjange.dev.ui.utils.contentPadding
import junjange.dev.ui.utils.rememberDeviceState
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.section_about
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutSection(modifier: Modifier = Modifier) {
    val deviceState = rememberDeviceState()

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(deviceState.contentPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            stringResource(Res.string.section_about),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier.height(36.dp))

        val horizontalPadding =
            when (deviceState.value) {
                Device.MOBILE -> 0.dp
                Device.PC -> (PC_CONTENT_HORIZONTAL_PADDING / 2).dp
            }

        AboutMe.entries.forEach { interview ->
            ContentCard(
                title = interview.titleRes,
                description = interview.descriptionRes,
                modifier = Modifier.padding(horizontal = horizontalPadding),
            )
            Spacer(modifier.height(24.dp))
        }
    }
}

@Composable
fun ContentCard(
    title: StringResource,
    description: StringResource,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(12.dp),
                    ambientColor = DarkGray.copy(0.01f),
                    spotColor = DarkGray.copy(0.01f),
                    clip = false,
                ).background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(12.dp),
                ).padding(20.dp),
    ) {
        Text(
            text = stringResource(title),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(description),
            fontSize = 16.sp,
            lineHeight = 22.sp,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}
