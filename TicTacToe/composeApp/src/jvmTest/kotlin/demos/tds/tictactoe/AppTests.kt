package demos.tds.tictactoe

import androidx.compose.ui.test.*
import com.mongodb.kotlin.client.MongoClient
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.common.ui.TopBarBackButtonTag
import demos.tds.tictactoe.title.TitleScreenStartButtonTag
import kotlin.test.Test
import kotlin.test.fail

@OptIn(ExperimentalTestApi::class)
class AppTests {

    private val noOpMongoClient = MongoClient.create()

    @Test
    fun `initially the title screen is shown`() = runComposeUiTest {

        // Arrange & Act
        setContent { App(dbClient = noOpMongoClient) }

        // Assert
        onNodeWithTag(testTag = AppScreen.Title.name).assertExists(
            "Title screen should be shown initially"
        )
    }

    @Test
    fun `in title screen clicking on start button should navigate to lobby screen`() = runComposeUiTest {

        // Arrange
        setContent {
            App(
                dbClient = noOpMongoClient,
                initialUser = User(name = "Test User")
            )
        }

        // Act
        onNodeWithTag(testTag = TitleScreenStartButtonTag).performClick()

        // Assert
        onNodeWithTag(testTag = AppScreen.Lobby.name).assertExists(
            "Lobby screen should be shown after clicking on start button"
        )
    }

    @Test
    fun `in lobby screen selecting a user should navigate to game screen`() = runComposeUiTest {

        setContent {
            App(
                dbClient = noOpMongoClient,
                startScreen = AppScreen.Lobby,
            )
        }

        fail("To implement")
    }

    @Test
    fun `in lobby screen clicking back button should navigate to title screen`() = runComposeUiTest {

        // Arrange
        setContent {
            App(
                dbClient = noOpMongoClient,
                startScreen = AppScreen.Lobby,
                initialUser = User(name = "Test User")
            )
        }

        // Act
        onNodeWithTag(testTag = TopBarBackButtonTag).performClick()

        // Assert
        onNodeWithTag(testTag = AppScreen.Title.name).assertExists(
            "Title screen should be shown after clicking on leave button"
        )
    }

    @Test
    fun `in settings screen clicking back button should navigate to title screen`() = runComposeUiTest {

        // Arrange
        setContent {
            App(
                dbClient = noOpMongoClient,
                startScreen = AppScreen.Settings,
                initialUser = User(name = "Test User")
            )
        }

        // Act
        onNodeWithTag(testTag = TopBarBackButtonTag).performClick()

        // Assert
        onNodeWithTag(testTag = AppScreen.Title.name).assertExists(
            "Title screen should be shown after clicking on back button"
        )
    }
}