package demos.tds.tictactoe

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import demos.tds.tictactoe.game.ui.GameScreen
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class GameScreenTests {

    @Test
    fun contains_the_associated_test_tag() = runComposeUiTest {
        setContent { GameScreen() }
        onNodeWithTag(testTag = AppScreen.Game.name).assertExists()
    }
}