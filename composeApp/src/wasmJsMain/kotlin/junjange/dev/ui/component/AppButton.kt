package junjange.dev.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import junjange.dev.ui.model.Project
import junjange.dev.ui.theme.Black
import junjange.dev.ui.theme.DarkGray
import junjange.dev.ui.theme.White
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppButton(
    project: Project,
    modifier: Modifier = Modifier,
    size: Dp = 92.dp,
    cornerRadius: Dp = 24.dp,
) {
    val uriHandler = LocalUriHandler.current
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    Surface(
        shape = RoundedCornerShape(cornerRadius),
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier =
            modifier.then(
                Modifier
                    .size(size)
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(cornerRadius),
                        clip = false,
                        ambientColor = DarkGray.copy(0.01f),
                        spotColor = DarkGray.copy(0.01f),
                    ).hoverable(interactionSource),
            ),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .clip(RoundedCornerShape(cornerRadius))
                    .clickable { uriHandler.openUri(project.url) },
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(project.logoRes),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
            AnimatedVisibility(
                visible = isHovered || isPressed,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(color = Black.copy(alpha = 0.75f))
                            .clip(RoundedCornerShape(cornerRadius)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(project.titleRes),
                        color = White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp),
                    )
                }
            }
        }
    }
}
