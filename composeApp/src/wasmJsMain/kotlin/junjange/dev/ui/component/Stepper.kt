package junjange.dev.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Stepper(
    dotSize: Dp,
    frontHeight: Dp,
    rearHeight: Dp,
    isHead: Boolean = false,
    thickness: Dp = 2.dp,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier =
                Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(if (isHead) 0f else 0.2f),
                        shape = CircleShape,
                    ).size(
                        width = thickness,
                        height = frontHeight,
                    ),
        )
        Spacer(Modifier.height(8.dp))
        Box(
            modifier =
                Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(0.33f),
                        shape = CircleShape,
                    ).size(dotSize),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier =
                    Modifier
                        .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                        .size(12.dp),
            )
        }
        Spacer(Modifier.height(8.dp))
        Box(
            modifier =
                Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(0.2f),
                        shape = CircleShape,
                    ).size(width = thickness, height = rearHeight),
        )
    }
}
