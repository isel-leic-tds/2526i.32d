package demos.tds.tictactoe

import androidx.compose.ui.test.*
import com.mongodb.kotlin.client.MongoClient
import demos.tds.tictactoe.common.ui.LeaveButtonTag
import demos.tds.tictactoe.title.TitleScreenStartButtonTag
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class AppTests {

    private val noOpMongoClient = MongoClient.create()

    @Test
    fun initially_the_title_screen_is_shown() = runComposeUiTest {

        setContent { App(dbClient = noOpMongoClient) }

        onNodeWithTag(testTag = AppScreen.Title.name).assertExists(
            "Title screen should be shown initially"
        )
    }

    @Test
    fun in_title_screen_clicking_on_start_button_should_navigate_to_lobby_screen() = runComposeUiTest {

        setContent { App(dbClient = noOpMongoClient) }

        onNodeWithTag(testTag = TitleScreenStartButtonTag).performClick()
        onNodeWithTag(testTag = AppScreen.Lobby.name).assertExists(
            "Lobby screen should be shown after clicking on start button"
        )
    }

    @Test
    fun in_lobby_screen_selecting_a_user_should_navigate_to_game_screen() = runComposeUiTest {

        setContent { App(dbClient = noOpMongoClient) }

        onNodeWithTag(testTag = TitleScreenStartButtonTag).performClick()
        onNodeWithText(text = "Palecas").performClick()
        onNodeWithTag(testTag = AppScreen.Game.name).assertExists(
            "Game screen should be shown after selecting a user"
        )
    }

    @Test
    fun in_lobby_screen_clicking_on_leave_button_should_navigate_to_title_screen() = runComposeUiTest {

        setContent { App(dbClient = noOpMongoClient) }

        onNodeWithTag(testTag = TitleScreenStartButtonTag).performClick()
        onNodeWithTag(testTag = AppScreen.Lobby.name).assertExists()
        onNodeWithTag(testTag = LeaveButtonTag).performClick()
        onNodeWithTag(testTag = AppScreen.Title.name).assertExists(
            "Title screen should be shown after clicking on leave button"
        )
    }
}