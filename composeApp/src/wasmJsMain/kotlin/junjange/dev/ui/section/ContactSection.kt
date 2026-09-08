package junjange.dev.ui.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.component.SectionContainer
import junjange.dev.ui.model.Contact
import junjange.dev.ui.model.Device
import junjange.dev.ui.state.contentPadding
import junjange.dev.ui.state.rememberDeviceState
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.copyright
import junjange_dev.composeapp.generated.resources.ic_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ContactSection(modifier: Modifier = Modifier) {
    val deviceState = rememberDeviceState()
    val horizontalPadding = deviceState.contentPadding().calculateStartPadding(LayoutDirection.Ltr)

    SectionContainer(
        modifier = modifier,
        background = MaterialTheme.colorScheme.secondaryContainer,
        contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = FOOTER_VERTICAL_PADDING),
    ) {
        when (deviceState.value) {
            Device.DESKTOP, Device.TABLET -> ContactWideContent()
            Device.MOBILE -> ContactMobileContent()
            Device.UNKNOWN -> {}
        }
    }
}

@Composable
private fun ContactWideContent(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Brand()
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Contact.entries.forEach { ContactButton(it) }
        }
    }
}

@Composable
private fun ContactMobileContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Brand()
        Spacer(Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Contact.entries.forEach { ContactButton(it) }
        }
    }
}

@Composable
private fun Brand() {
    Column {
        Icon(
            painter = painterResource(Res.drawable.ic_logo),
            modifier = Modifier.height(24.dp),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = stringResource(Res.string.copyright),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 13.sp,
        )
    }
}

@Composable
private fun ContactButton(
    contact: Contact,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current

    IconButton(
        onClick = { uriHandler.openUri(contact.url) },
        modifier =
            modifier
                .size(44.dp)
                .background(MaterialTheme.colorScheme.outlineVariant, CircleShape),
    ) {
        Icon(
            painterResource(contact.iconRes),
            contentDescription = contact.name,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp),
        )
    }
}

private val FOOTER_VERTICAL_PADDING = 48.dp
