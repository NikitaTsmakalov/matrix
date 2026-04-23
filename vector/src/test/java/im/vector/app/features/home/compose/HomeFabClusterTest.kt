package im.vector.app.features.home.compose

import im.vector.app.features.home.room.list.UnreadCounterBadgeView
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class HomeFabClusterTest {

    @Test
    fun `badgeText formats count state`() {
        badgeText(UnreadCounterBadgeView.State.Count(count = 1200, highlighted = false)) shouldBeEqualTo "1.2k"
    }

    @Test
    fun `badgeText returns null for zero count`() {
        badgeText(UnreadCounterBadgeView.State.Count(count = 0, highlighted = true)) shouldBeEqualTo null
    }
}
