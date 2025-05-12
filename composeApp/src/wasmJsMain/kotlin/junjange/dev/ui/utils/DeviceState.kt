package junjange.dev.ui.utils

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

typealias DeviceState = State<Device>

@Composable
fun rememberDeviceState(): DeviceState {
    val screenWidthInDp = with(LocalDensity.current) { LocalScreenSize.current.width.toDp() }
    return remember(screenWidthInDp) { derivedStateOf { Device.fromWidth(screenWidthInDp) } }
}

val DeviceState.isMobile
    get() = value == Device.MOBILE
val DeviceState.isPc
    get() = value == Device.PC

@Composable
@ReadOnlyComposable
fun DeviceState.fontSize(): TextUnit =
    when (this.value) {
        Device.PC -> 72.sp
        Device.MOBILE -> 48.sp
    }

@Composable
@ReadOnlyComposable
fun DeviceState.contentPadding(): PaddingValues =
    when (this.value) {
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
