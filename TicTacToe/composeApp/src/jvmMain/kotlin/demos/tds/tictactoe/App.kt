package demos.tds.tictactoe

import androidx.compose.runtime.*
import com.mongodb.kotlin.client.MongoClient
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.common.ui.theme.AppTheme
import demos.tds.tictactoe.game.ui.GameScreen
import demos.tds.tictactoe.lobby.infrastructure.LobbyMongoDB
import demos.tds.tictactoe.lobby.ui.LobbyScreen
import demos.tds.tictactoe.lobby.ui.LobbyScreenViewModel
import demos.tds.tictactoe.settings.SettingsScreen
import demos.tds.tictactoe.title.TitleScreen


/**
 * The application screens.
 * In our context, a screen is the set of elements used to display a specific part of the app.
 */
sealed class AppScreen {

    data class Title(val user: User?) : AppScreen() {
        companion object { val name: String = "Title" }
    }

    data class Settings(val user: User?) : AppScreen() {
        companion object { val name: String = "Settings" }
    }

    data class Lobby(val user: User) : AppScreen() {
        companion object { val name: String = "Lobby" }
    }

    object Game : AppScreen() {
        val name: String = "Game"
    }
}

/**
 * The root composable of the app.
 * It is responsible for displaying the correct screen based on the current state.
 */
@Composable
fun App(
    dbClient: MongoClient,
    dbName: String,
    startScreen: AppScreen = AppScreen.Title(user = null),
) {

    AppTheme {

        var currentScreen by remember { mutableStateOf(value = startScreen) }
        val scope = rememberCoroutineScope()

        when (val observedCurrentScreen = currentScreen) {

            is AppScreen.Title ->
                TitleScreen(
                    localUser = observedCurrentScreen.user,
                    onStart = {
                        if (observedCurrentScreen.user != null)
                            currentScreen = AppScreen.Lobby(observedCurrentScreen.user)
                    },
                    onSettingsSelected = { currentScreen = AppScreen.Settings(observedCurrentScreen.user) }
                )

            is AppScreen.Lobby -> {
                LobbyScreen(
                    viewModel = LobbyScreenViewModel(
                        localUser = observedCurrentScreen.user,
                        service = LobbyMongoDB(dbClient = dbClient, dbName = dbName),
                        scope = scope
                    ),
                    onUserChallenged = { currentScreen = AppScreen.Game },
                    onLeave = { currentScreen = AppScreen.Title(observedCurrentScreen.user) }
                )
            }

            is AppScreen.Game -> GameScreen()

            is AppScreen.Settings -> SettingsScreen(
                user = observedCurrentScreen.user,
                onLeave = { currentScreen = AppScreen.Title(user = it) }
            )
        }
    }
}
