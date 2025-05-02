package junjange.dev.ui.component

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.gestures.Orientation

fun defaultEnterAnim(
    durationMillis: Int = 1000,
    delayMillis: Int = 0,
    easing: Easing = LinearEasing,
    orientation: Orientation = Orientation.Vertical,
    inverseSlide: Boolean = false,
): EnterTransition =
    fadeIn(
        tween(
            durationMillis = durationMillis,
            delayMillis = delayMillis,
            easing = easing,
        ),
    ) +
        when (orientation) {
            Orientation.Horizontal ->
                slideInHorizontally(
                    animationSpec =
                        tween(
                            durationMillis = durationMillis,
                            delayMillis = delayMillis,
                            easing = easing,
                        ),
                    initialOffsetX = { if (inverseSlide) -it / 2 else it / 2 },
                )

            Orientation.Vertical ->
                slideInVertically(
                    animationSpec =
                        tween(
                            durationMillis = durationMillis,
                            delayMillis = delayMillis,
                            easing = easing,
                        ),
                    initialOffsetY = { if (inverseSlide) -it / 2 else it / 2 },
                )
        }
