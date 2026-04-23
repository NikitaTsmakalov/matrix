package im.vector.app.features.home.compose

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import im.vector.app.features.home.room.list.UnreadCounterBadgeView
import im.vector.lib.strings.CommonStrings
import org.amshove.kluent.shouldBeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFabClusterUiTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun fabCallbacksAreTriggered() {
        var createClicked = false
        var spacesClicked = false
        composeRule.setContent {
            HomeFabCluster(
                    spacesBadgeState = UnreadCounterBadgeView.State.Count(4, highlighted = true),
                    onCreateChatClick = { createClicked = true },
                    onOpenSpacesClick = { spacesClicked = true }
            )
        }

        composeRule.onNodeWithContentDescription(
                composeRule.activity.getString(CommonStrings.a11y_create_message)
        ).performClick()
        composeRule.onNodeWithContentDescription(
                composeRule.activity.getString(CommonStrings.a11y_open_spaces)
        ).performClick()

        createClicked.shouldBeTrue()
        spacesClicked.shouldBeTrue()
    }
}
