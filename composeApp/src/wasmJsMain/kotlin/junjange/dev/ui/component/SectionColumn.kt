package junjange.dev.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import junjange.dev.ui.MOBILE_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.MOBILE_CONTENT_VERTICAL_PADDING
import junjange.dev.ui.PC_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.PC_CONTENT_VERTICAL_PADDING
import junjange.dev.ui.PC_CONTENT_WIDTH
import junjange.dev.ui.model.Device
import junjange.dev.ui.state.DeviceState
import junjange.dev.ui.state.isMobile

@Composable
fun SectionColumn(
    modifier: Modifier = Modifier,
    deviceState: DeviceState,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    contentPadding: PaddingValues = getContentPadding(deviceState.value),
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        modifier =
            modifier.then(
                Modifier
                    .background(color = backgroundColor)
                    .fillMaxWidth(),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier =
                Modifier
                    .padding(contentPadding)
                    .let {
                        if (deviceState.isMobile) {
                            it
                        } else {
                            it.width(PC_CONTENT_WIDTH.dp)
                        }
                    },
            horizontalAlignment = horizontalAlignment,
            content = content,
        )
    }
}

@Composable
fun getContentPadding(device: Device): PaddingValues =
    when (device) {
        Device.MOBILE -> mobileSectionPaddingValues
        Device.PC -> pcSectionPaddingValues
    }

private val pcSectionPaddingValues =
    PaddingValues(
        horizontal = PC_CONTENT_HORIZONTAL_PADDING.dp,
        vertical = PC_CONTENT_VERTICAL_PADDING.dp,
    )

private val mobileSectionPaddingValues =
    PaddingValues(
        horizontal = MOBILE_CONTENT_HORIZONTAL_PADDING.dp,
        vertical = MOBILE_CONTENT_VERTICAL_PADDING.dp,
    )
