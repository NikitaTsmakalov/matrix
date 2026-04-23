package im.vector.app.features.spaces.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import im.vector.lib.strings.CommonStrings

@Composable
fun SpaceListEmptyState(
        onCreateSpaceClick: () -> Unit,
) {
    Column(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Text(
                text = stringResource(id = CommonStrings.space_list_empty_title),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
        )
        Text(
                text = stringResource(id = CommonStrings.space_list_empty_message),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.75f)
        )
        Button(
                onClick = onCreateSpaceClick,
                modifier = Modifier.padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = Color.White
                )
        ) {
            Text(text = stringResource(id = CommonStrings.create_space))
        }
    }
}
