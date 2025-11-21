package demos.tds.tictactoe

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


enum class AppScreen {
    Title,
    Lobby,
    Game
}

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf(value = AppScreen.Title) }
    MaterialTheme {
        when (currentScreen) {
            AppScreen.Title -> TitleScreen(onStart = { currentScreen = AppScreen.Lobby })
            AppScreen.Lobby -> LobbyScreen(usersInLobby = listOf(User(name = "Palecas")))
            else -> @Composable {}
        }
    }
}

@Preview
@Composable
private fun AppPreview() = App()