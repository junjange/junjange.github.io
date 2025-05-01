package junjange.dev.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import junjange.dev.ui.LocalScreenSize
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
