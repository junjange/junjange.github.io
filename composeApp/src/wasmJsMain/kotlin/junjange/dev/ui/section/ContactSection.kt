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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.component.SectionContainer
import junjange.dev.ui.component.SectionHeader
import junjange.dev.ui.component.SurfaceCard
import junjange.dev.ui.component.reveal
import junjange.dev.ui.model.Contact
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.currentYear
import junjange.dev.ui.state.contentPadding
import junjange.dev.ui.state.rememberDeviceState
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.contact_email
import junjange_dev.composeapp.generated.resources.contact_headline
import junjange_dev.composeapp.generated.resources.copyright
import junjange_dev.composeapp.generated.resources.ic_email
import junjange_dev.composeapp.generated.resources.ic_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ContactSection(modifier: Modifier = Modifier) {
    val deviceState = rememberDeviceState()
    val isMobile = deviceState.value == Device.MOBILE
    val horizontalPadding = deviceState.contentPadding().calculateStartPadding(LayoutDirection.Ltr)

    Column(modifier = modifier.fillMaxWidth()) {
        SectionContainer(
            contentPadding = PaddingValues(start = horizontalPadding, end = horizontalPadding, bottom = if (isMobile) 72.dp else 120.dp),
        ) {
            SurfaceCard(
                modifier = Modifier.fillMaxWidth().reveal(),
                padding = if (isMobile) 32.dp else 72.dp,
            ) {
                SectionHeader(
                    eyebrow = "Contact",
                    headline = stringResource(Res.string.contact_headline),
                )
                Spacer(modifier = Modifier.height(40.dp))
                ContactActions(isMobile = isMobile, modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }

        SiteFooter()
    }
}

@Composable
fun SiteFooter(modifier: Modifier = Modifier) {
    val deviceState = rememberDeviceState()
    val horizontalPadding = deviceState.contentPadding().calculateStartPadding(LayoutDirection.Ltr)

    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        SectionContainer(contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = 32.dp)) {
            Footer(isMobile = deviceState.value == Device.MOBILE)
        }
    }
}

@Composable
private fun ContactActions(
    isMobile: Boolean,
    modifier: Modifier = Modifier,
) {
    if (isMobile) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            EmailButton()
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) { ContactIcons() }
        }
    } else {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            EmailButton()
            ContactIcons()
        }
    }
}

@Composable
private fun EmailButton() {
    val uriHandler = LocalUriHandler.current

    Button(
        onClick = { uriHandler.openUri(Contact.EMAIL.url) },
        modifier = Modifier.height(52.dp),
        shape = RoundedCornerShape(14.dp),
        elevation = null,
        contentPadding = PaddingValues(horizontal = 22.dp),
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_email),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = stringResource(Res.string.contact_email), fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
    }
}

@Composable
private fun ContactIcons() {
    val uriHandler = LocalUriHandler.current

    Contact.entries.filter { it != Contact.EMAIL }.forEach { contact ->
        IconButton(
            onClick = { uriHandler.openUri(contact.url) },
            modifier =
                Modifier
                    .size(52.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
        ) {
            Icon(
                painterResource(contact.iconRes),
                contentDescription = contact.name,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(22.dp),
            )
        }
    }
}

@Composable
private fun Footer(isMobile: Boolean) {
    val copyright = stringResource(Res.string.copyright, currentYear())

    if (isMobile) {
        Column {
            FooterLogo()
            Spacer(modifier = Modifier.height(12.dp))
            FooterText(copyright)
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FooterLogo()
            FooterText(copyright)
        }
    }
}

@Composable
private fun FooterLogo() {
    Icon(
        painter = painterResource(Res.drawable.ic_logo),
        modifier = Modifier.height(22.dp),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}

@Composable
private fun FooterText(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontSize = 13.sp,
    )
}
