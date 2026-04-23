package im.vector.app.features.home.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import im.vector.app.R
import im.vector.app.features.home.room.list.RoomSummaryFormatter
import im.vector.app.features.home.room.list.UnreadCounterBadgeView
import im.vector.lib.strings.CommonStrings
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi

@Composable
fun HomeFabCluster(
        spacesBadgeState: UnreadCounterBadgeView.State?,
        onCreateChatClick: () -> Unit,
        onOpenSpacesClick: () -> Unit,
) {
    Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                    .navigationBarsPadding()
                    .padding(bottom = 12.dp)
    ) {
        BadgedBox(
                badge = {
                    SpaceBadge(state = spacesBadgeState)
                },
                modifier = Modifier.offset(y = 6.dp)
        ) {
            FabLikeButton(
                    size = 40.dp,
                    corner = 12.dp,
                    onClick = onOpenSpacesClick,
                    contentDescription = stringResource(id = CommonStrings.a11y_open_spaces),
                    iconRes = R.drawable.ic_spaces,
                    backgroundColor = Color.White,
                    iconTint = Color(0xFF0DBD8B),
                    elevation = 8.dp
            )
        }
        FabLikeButton(
                size = 56.dp,
                corner = 16.dp,
                onClick = onCreateChatClick,
                modifier = Modifier
                        .offset(y = 12.dp),
                contentDescription = stringResource(id = CommonStrings.a11y_create_message),
                iconRes = R.drawable.ic_new_chat,
                backgroundColor = Color(0xFF0DBD8B),
                iconTint = Color.White,
                elevation = 6.dp
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun FabLikeButton(
        size: Dp,
        corner: Dp,
        onClick: () -> Unit,
        contentDescription: String,
        iconRes: Int,
        backgroundColor: Color,
        iconTint: Color,
        elevation: Dp,
        modifier: Modifier = Modifier,
) {
    Surface(
            modifier = modifier
                    .size(size)
                    .clip(RoundedCornerShape(corner)),
            color = backgroundColor,
            shape = RoundedCornerShape(corner),
            elevation = elevation,
            onClick = onClick
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = contentDescription,
                    tint = iconTint
            )
        }
    }
}

@Composable
private fun SpaceBadge(state: UnreadCounterBadgeView.State?) {
    val text = badgeText(state)
    if (text != null) {
        val highlighted = when (state) {
            is UnreadCounterBadgeView.State.Count -> state.highlighted
            is UnreadCounterBadgeView.State.Text -> state.highlighted
            null -> false
        }
        Badge(backgroundColor = if (highlighted) MaterialTheme.colors.error else Color(0xFF5A6175)) {
            Box(modifier = Modifier.offset(y = (-1).dp), contentAlignment = Alignment.Center) {
                Text(text = text, color = Color.White, style = MaterialTheme.typography.caption)
            }
        }
    }
}

internal fun badgeText(state: UnreadCounterBadgeView.State?): String? = when (state) {
    is UnreadCounterBadgeView.State.Count -> state.count.takeIf { it > 0 }?.let { RoomSummaryFormatter.formatUnreadMessagesCounter(it) }
    is UnreadCounterBadgeView.State.Text -> state.text
    null -> null
}
