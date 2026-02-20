package demos.tds.tictactoe

import androidx.compose.ui.test.*
import com.mongodb.kotlin.client.MongoClient
import demos.tds.tictactoe.common.domain.Challenge
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.common.ui.TopBarBackButtonTag
import demos.tds.tictactoe.game.domain.Game
import demos.tds.tictactoe.game.infrasctructure.MatchDocument
import demos.tds.tictactoe.game.infrasctructure.toMatchDocument
import demos.tds.tictactoe.title.TitleScreenStartButtonTag
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class AppTests {

    @AfterTest
    fun tearDown() {
        noOpMongoClient.getDatabase(databaseName = DB_NAME).drop()
        noOpMongoClient.close()
    }

    private val noOpMongoClient = MongoClient.create()

    @Test
    fun `initially the title screen is shown`() = runComposeUiTest {

        // Arrange & Act
        setContent { App(dbClient = noOpMongoClient, dbName = DB_NAME) }

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
                dbName = DB_NAME,
                startScreen = AppScreen.Title(User(name = "Test User")),
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

        // Arrange
        val testUser = User(name = "Test User")
        setContent {
            App(
                dbClient = noOpMongoClient,
                dbName = DB_NAME,
                startScreen = AppScreen.Lobby(testUser),
            )
        }

        // Act
        onNodeWithText(text = testUser.name, substring = true).performClick()

        // Assert
        onNodeWithTag(testTag = AppScreen.Game.name).assertExists()
    }

    @Test
    fun `in lobby screen clicking back button should navigate to title screen`() = runComposeUiTest {

        // Arrange
        setContent {
            App(
                dbClient = noOpMongoClient,
                dbName = DB_NAME,
                startScreen = AppScreen.Lobby(User(name = "Test User")),
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
                dbName = DB_NAME,
                startScreen = AppScreen.Settings(user = User(name = "Test User")),
            )
        }

        // Act
        onNodeWithTag(testTag = TopBarBackButtonTag).performClick()

        // Assert
        onNodeWithTag(testTag = AppScreen.Title.name).assertExists(
            "Title screen should be shown after clicking on back button"
        )
    }

    @Test
    fun test() = runTest {
        noOpMongoClient
            .getDatabase(DB_NAME)
            .getCollection<MatchDocument>("ongoing")
            .insertOne(Game().toMatchDocument(Challenge(challenger = User("Tonhó"), challenged = User("Palecas"))))
    }
}