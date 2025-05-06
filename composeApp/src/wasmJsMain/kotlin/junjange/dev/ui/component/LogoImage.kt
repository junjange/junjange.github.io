package junjange.dev.ui.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.ic_logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun LogoImage(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.height(48.dp),
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier.fillMaxHeight(),
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    }
}
