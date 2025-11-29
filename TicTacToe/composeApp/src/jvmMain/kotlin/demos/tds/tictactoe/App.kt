package demos.tds.tictactoe

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import demos.tds.tictactoe.game.GameScreen
import demos.tds.tictactoe.lobby.LobbyScreen
import demos.tds.tictactoe.lobby.User
import demos.tds.tictactoe.title.TitleScreen


enum class AppScreen {
    Title,
    Lobby,
    Game
}

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf(value = AppScreen.Title) }
    when (currentScreen) {
        AppScreen.Title -> TitleScreen(onStart = { currentScreen = AppScreen.Lobby })
        AppScreen.Lobby -> LobbyScreen(
            usersInLobby = listOf(User(name = "Palecas")),
            onUserSelected = { currentScreen = AppScreen.Game },
            onLeave = { currentScreen = AppScreen.Title }
        )
        AppScreen.Game -> GameScreen()
    }
}

@Preview
@Composable
private fun AppPreview() = App()