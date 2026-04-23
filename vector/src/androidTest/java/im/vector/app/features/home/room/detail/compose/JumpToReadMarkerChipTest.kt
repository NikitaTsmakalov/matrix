package im.vector.app.features.home.room.detail.compose

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import im.vector.lib.strings.CommonStrings
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JumpToReadMarkerChipTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun hiddenChipIsNotDisplayed() {
        composeRule.setContent {
            JumpToReadMarkerChip(
                    visible = false,
                    onClick = {},
                    onCloseClick = {}
            )
        }

        composeRule.onNodeWithText(composeRule.activity.getString(CommonStrings.room_jump_to_first_unread))
                .assertDoesNotExist()
    }

    @Test
    fun visibleChipIsDisplayed() {
        composeRule.setContent {
            JumpToReadMarkerChip(
                    visible = true,
                    onClick = {},
                    onCloseClick = {}
            )
        }

        composeRule.onNodeWithText(composeRule.activity.getString(CommonStrings.room_jump_to_first_unread))
                .assertIsDisplayed()
    }
}
