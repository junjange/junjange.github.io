package junjange.dev.ui.state

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.DESKTOP_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.DESKTOP_CONTENT_VERTICAL_PADDING
import junjange.dev.ui.MOBILE_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.MOBILE_CONTENT_VERTICAL_PADDING
import junjange.dev.ui.TABLET_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.TABLET_CONTENT_VERTICAL_PADDING
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.LocalScreenSize

typealias DeviceState = State<Device>

@Composable
fun rememberDeviceState(): DeviceState {
    val screenWidthDp = with(LocalDensity.current) { LocalScreenSize.current.width.toDp() }
    return remember(screenWidthDp) {
        derivedStateOf { Device.fromWidth(screenWidthDp) }
    }
}

@Composable
@ReadOnlyComposable
fun DeviceState.titleFontSize(): TextUnit =
    when (value) {
        Device.DESKTOP -> 72.sp
        Device.TABLET -> 48.sp
        else -> 36.sp
    }

@Composable
@ReadOnlyComposable
fun DeviceState.contentPadding(): PaddingValues =
    when (value) {
        Device.DESKTOP -> DesktopContentPadding
        Device.TABLET -> TabletContentPadding
        else -> MobileContentPadding
    }

private val DesktopContentPadding =
    PaddingValues(
        horizontal = DESKTOP_CONTENT_HORIZONTAL_PADDING.dp,
        vertical = DESKTOP_CONTENT_VERTICAL_PADDING.dp,
    )

private val TabletContentPadding =
    PaddingValues(
        horizontal = TABLET_CONTENT_HORIZONTAL_PADDING.dp,
        vertical = TABLET_CONTENT_VERTICAL_PADDING.dp,
    )

private val MobileContentPadding =
    PaddingValues(
        horizontal = MOBILE_CONTENT_HORIZONTAL_PADDING.dp,
        vertical = MOBILE_CONTENT_VERTICAL_PADDING.dp,
    )
