package demos.tds.tictactoe

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class TitleScreenTests {

    @Test
    fun contains_the_associated_test_tag() = runComposeUiTest {
        setContent { TitleScreen() }
        onNodeWithTag(testTag = AppScreen.Title.name).assertExists()
    }

    @Test
    fun clicking_on_start_button_should_call_onStart() = runComposeUiTest {

        var onStartCalled = false
        setContent { TitleScreen(onStart = { onStartCalled = true }) }

        onNodeWithTag(testTag = TitleScreenStartButtonTag).performClick()
        assert(onStartCalled)
    }

}