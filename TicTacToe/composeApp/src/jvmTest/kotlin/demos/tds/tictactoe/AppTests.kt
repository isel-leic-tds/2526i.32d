package demos.tds.tictactoe

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class AppTests {

    @Test
    fun initially_the_title_screen_is_shown() = runComposeUiTest {

        setContent { App() }

        onNodeWithTag(testTag = AppScreen.Title.name).assertExists(
            "Title screen should be shown initially"
        )
    }

    @Test
    fun clicking_on_start_button_should_navigate_to_lobby_screen() = runComposeUiTest {

        setContent { App() }

        onNodeWithTag(testTag = TitleScreenStartButtonTag).performClick()
        onNodeWithTag(testTag = AppScreen.Lobby.name).assertExists(
            "Lobby screen should be shown after clicking on start button"
        )
    }
}