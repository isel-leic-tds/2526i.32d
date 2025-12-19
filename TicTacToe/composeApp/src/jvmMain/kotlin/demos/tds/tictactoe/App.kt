package demos.tds.tictactoe

import androidx.compose.runtime.*
import com.mongodb.kotlin.client.MongoClient
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.common.ui.theme.AppTheme
import demos.tds.tictactoe.game.GameScreen
import demos.tds.tictactoe.lobby.LobbyServiceMongoDB
import demos.tds.tictactoe.lobby.ui.LobbyScreen
import demos.tds.tictactoe.lobby.ui.LobbyScreenViewModel
import demos.tds.tictactoe.settings.SettingsScreen
import demos.tds.tictactoe.title.TitleScreen


/**
 * The application screens.
 * In our context, a screen is the set of elements used to display a specific part of the app.
 */
enum class AppScreen {
    Title,
    Settings,
    Lobby,
    Game
}

/**
 * The root composable of the app.
 * It is responsible for displaying the correct screen based on the current state.
 */
@Composable
fun App(
    dbClient: MongoClient,
    startScreen: AppScreen = AppScreen.Title,
    initialUser: User? = null
) {

    AppTheme {

        var localUser by remember { mutableStateOf(initialUser) }

        var currentScreen by remember { mutableStateOf(value = startScreen) }
        val scope = rememberCoroutineScope()

        when (currentScreen) {

            AppScreen.Title ->
                TitleScreen(
                    localUser = localUser,
                    onStart = { currentScreen = AppScreen.Lobby },
                    onSettingsSelected = { currentScreen = AppScreen.Settings }
                )

            AppScreen.Lobby -> {
                LobbyScreen(
                    viewModel = LobbyScreenViewModel(
                        // TODO: This is a good example of the usefulness of using a sealed hierarchy instead of enums.
                        localUser = localUser ?: throw IllegalStateException("Local user is null"),
                        service = LobbyServiceMongoDB(dbClient = dbClient),
                        scope = scope
                    ),
                    onUserChallenged = { currentScreen = AppScreen.Game },
                    onLeave = { currentScreen = AppScreen.Title }
                )
            }

            AppScreen.Game -> GameScreen()

            AppScreen.Settings -> SettingsScreen(
                user = localUser,
                onLeave = { localUser = it; currentScreen = AppScreen.Title }
            )
        }
    }
}
