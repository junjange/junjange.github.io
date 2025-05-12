package junjange.dev.ui.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.model.Contact
import junjange.dev.ui.utils.contentPadding
import junjange.dev.ui.utils.isPc
import junjange.dev.ui.utils.rememberDeviceState
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.copyright
import junjange_dev.composeapp.generated.resources.ic_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ContactSection(modifier: Modifier = Modifier) {
    val deviceState = rememberDeviceState()

    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(deviceState.contentPadding()),
        contentAlignment = Alignment.Center,
    ) {
        if (deviceState.isPc) {
            ContactPcContent()
        } else {
            ContactMobileContent()
        }
    }
}

@Composable
private fun ContactPcContent(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_logo),
                modifier = Modifier.height(28.dp),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
            )
            Text(
                text = stringResource(Res.string.copyright),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Contact.entries.forEach {
                ContactButton(it)
            }
        }
    }
}

@Composable
private fun ContactMobileContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_logo),
            modifier = Modifier.height(28.dp),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        Text(
            text = stringResource(Res.string.copyright),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(12.dp))

        Row {
            Contact.entries.forEach {
                ContactButton(it)
            }
        }
    }
}

@Composable
private fun ContactButton(
    contact: Contact,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current

    TextButton(
        onClick = { uriHandler.openUri(contact.url) },
        modifier = modifier,
        colors =
            ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            ),
    ) {
        Icon(
            painterResource(contact.iconRes),
            contentDescription = contact.name,
            modifier = Modifier.size(36.dp),
        )
    }
}
