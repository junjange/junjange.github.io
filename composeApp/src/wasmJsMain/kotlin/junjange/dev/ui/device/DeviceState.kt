package junjange.dev.ui.device

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
import junjange.dev.ui.MOBILE_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.MOBILE_CONTENT_VERTICAL_PADDING
import junjange.dev.ui.PC_CONTENT_HORIZONTAL_PADDING
import junjange.dev.ui.PC_CONTENT_VERTICAL_PADDING
import junjange.dev.ui.model.Device
import junjange.dev.ui.screen.LocalScreenSize

typealias DeviceState = State<Device>

@Composable
fun rememberDeviceState(): DeviceState {
    val screenWidthDp = with(LocalDensity.current) { LocalScreenSize.current.width.toDp() }
    return remember(screenWidthDp) {
        derivedStateOf { Device.fromWidth(screenWidthDp) }
    }
}

val DeviceState.isMobile: Boolean
    get() = value == Device.MOBILE

val DeviceState.isPc: Boolean
    get() = value == Device.PC

@Composable
@ReadOnlyComposable
fun DeviceState.fontSize(): TextUnit =
    when (value) {
        Device.PC -> 72.sp
        Device.MOBILE -> 48.sp
    }

@Composable
@ReadOnlyComposable
fun DeviceState.contentPadding(): PaddingValues =
    when (value) {
        Device.PC -> PcContentPadding
        Device.MOBILE -> MobileContentPadding
    }

private val PcContentPadding =
    PaddingValues(
        horizontal = PC_CONTENT_HORIZONTAL_PADDING.dp,
        vertical = PC_CONTENT_VERTICAL_PADDING.dp,
    )

private val MobileContentPadding =
    PaddingValues(
        horizontal = MOBILE_CONTENT_HORIZONTAL_PADDING.dp,
        vertical = MOBILE_CONTENT_VERTICAL_PADDING.dp,
    )
