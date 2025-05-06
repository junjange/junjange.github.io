package junjange.dev.ui.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junjange.dev.ui.component.LogoButton
import junjange.dev.ui.model.Club
import junjange.dev.ui.model.Device
import junjange.dev.ui.model.Etc
import junjange.dev.ui.state.contentPadding
import junjange.dev.ui.state.rememberDeviceState
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.experience
import junjange_dev.composeapp.generated.resources.experience_development
import junjange_dev.composeapp.generated.resources.experience_etc
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExperienceSection(modifier: Modifier = Modifier) {
    val deviceState = rememberDeviceState()

    Column(
        modifier =
            modifier
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(deviceState.contentPadding()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                stringResource(Res.string.experience),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(36.dp))

        Text(
            stringResource(Res.string.experience_development),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(16.dp))

        val (gridColumns, gridHeight) =
            when (deviceState.value) {
                Device.MOBILE -> 1 to 344.dp
                Device.PC -> 2 to 172.dp
            }

        LazyVerticalGrid(
            columns = GridCells.Fixed(gridColumns),
            modifier = Modifier.height(gridHeight),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        ) {
            items(Club.entries) {
                ClubListItem(club = it, modifier = Modifier.weight(1f))
                Spacer(Modifier.height(12.dp))
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(Res.string.experience_etc),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(12.dp))

        Column {
            Etc.entries.forEach { mentor ->
                EtcText(mentor)
                Spacer(Modifier.height(4.dp))
            }
        }
    }
}

@Composable
private fun ClubListItem(
    club: Club,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LogoButton(
            logoRes = club.logoRes,
            url = club.url,
            size = 72.dp,
            contentPadding = PaddingValues(0.dp),
            cornerRadius = 12.dp,
            forceDarkTheme = true,
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(club.titleRes),
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.9f),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                )
            }
            Text(
                text = stringResource(club.positionRes),
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.5f),
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
private fun EtcText(
    etc: Etc,
    modifier: Modifier = Modifier,
) {
    val text =
        buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.8f),
                ),
            ) {
                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.25f),
                    ),
                ) {
                    append(" • ")
                }

                append(stringResource(etc.titleRes))
                append(" ")

                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.5f),
                        fontSize = 12.sp,
                    ),
                ) {
                    append(stringResource(etc.dateRes))
                }
            }
        }
    Text(text = text, modifier = modifier)
}
