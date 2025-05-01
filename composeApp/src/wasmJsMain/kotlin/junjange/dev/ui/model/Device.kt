package junjange.dev.ui.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class Device {
    MOBILE,
    PC,
    ;

    companion object {
        fun fromWidth(width: Dp): Device = if (width < MOBILE_SCREEN_WIDTH_THRESHOLD.dp) MOBILE else PC

        private const val MOBILE_SCREEN_WIDTH_THRESHOLD = 840
    }
}
