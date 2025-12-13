package demos.tds.tictactoe

import androidx.compose.runtime.*
import com.mongodb.kotlin.client.MongoClient
import demos.tds.tictactoe.game.GameScreen
import demos.tds.tictactoe.lobby.ui.LobbyScreen
import demos.tds.tictactoe.lobby.ui.LobbyScreenViewModel
import demos.tds.tictactoe.lobby.LobbyServiceMongoDB
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.title.TitleScreen


enum class AppScreen {
    Title,
    Lobby,
    Game
}

@Composable
fun App(dbClient: MongoClient) {
    val localUser = User(name = $"Local User ${System.currentTimeMillis()}")
    var currentScreen by remember { mutableStateOf(value = AppScreen.Title) }
    val scope = rememberCoroutineScope()
    when (currentScreen) {
        AppScreen.Title -> TitleScreen(onStart = { currentScreen = AppScreen.Lobby })
        AppScreen.Lobby -> {
            LobbyScreen(
                viewModel = LobbyScreenViewModel(
                    localUser = localUser,
                    service = LobbyServiceMongoDB(dbClient = dbClient),
                    scope = scope
                ),
                onUserSelected = { currentScreen = AppScreen.Game },
                onLeave = { currentScreen = AppScreen.Title
                }
            )
        }
        AppScreen.Game -> GameScreen()
    }
}
