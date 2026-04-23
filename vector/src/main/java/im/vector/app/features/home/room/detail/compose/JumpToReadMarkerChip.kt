package im.vector.app.features.home.room.detail.compose

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import im.vector.app.R
import im.vector.lib.strings.CommonStrings

@Composable
fun JumpToReadMarkerChip(
        visible: Boolean,
        onClick: () -> Unit,
        onCloseClick: () -> Unit,
) {
    if (!visible) return

    Surface(
            shape = MaterialTheme.shapes.large,
            elevation = 4.dp,
            modifier = Modifier.clickable(onClick = onClick)
    ) {
        Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(start = 12.dp, end = 4.dp, top = 6.dp, bottom = 6.dp)
        ) {
            Icon(
                    painter = painterResource(id = R.drawable.ic_jump_to_unread),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
            )
            Text(text = stringResource(id = CommonStrings.room_jump_to_first_unread))
            IconButton(onClick = onCloseClick) {
                Icon(
                        painter = painterResource(id = R.drawable.ic_close_24dp),
                        contentDescription = null
                )
            }
        }
    }
}
