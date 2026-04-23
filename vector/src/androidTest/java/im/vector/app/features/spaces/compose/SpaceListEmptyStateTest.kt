package im.vector.app.features.spaces.compose

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
class SpaceListEmptyStateTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun showsLocalizedContent() {
        composeRule.setContent {
            SpaceListEmptyState(onCreateSpaceClick = {})
        }

        composeRule.onNodeWithText(
                composeRule.activity.getString(CommonStrings.space_list_empty_title)
        ).assertIsDisplayed()
        composeRule.onNodeWithText(
                composeRule.activity.getString(CommonStrings.create_space)
        ).assertIsDisplayed()
    }
}
