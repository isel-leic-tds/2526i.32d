package demos.tds.tictactoe.common.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class ScreenScaffoldTests {

    @Test
    fun title_is_displayed() = runComposeUiTest {
        val expectedTitle = "Test"
        setContent { ScreenScaffold(title = expectedTitle) }

        onNodeWithText(text = expectedTitle).assertExists()
    }

    @Test
    fun when_onLeave_is_not_null_leave_button_is_displayed() = runComposeUiTest {
        setContent {
            ScreenScaffold(onLeave = { })
        }

        onNodeWithTag(testTag = TopBarBackButtonTag)
            .assertExists("Leave button should be displayed")
    }

    @Test
    fun when_onLeave_is_null_leave_button_is_not_displayed() = runComposeUiTest {
        setContent {
            ScreenScaffold(onLeave = null)
        }

        onNodeWithTag(testTag = TopBarBackButtonTag)
            .assertDoesNotExist()
    }

    @Test
    fun when_leave_button_is_clicked_onLeave_is_called() = runComposeUiTest {
        var onLeaveCalled = false
        setContent {
            ScreenScaffold(onLeave = { onLeaveCalled = true })
        }

        onNodeWithTag(testTag = TopBarBackButtonTag).performClick()
        assert(onLeaveCalled)
    }
}