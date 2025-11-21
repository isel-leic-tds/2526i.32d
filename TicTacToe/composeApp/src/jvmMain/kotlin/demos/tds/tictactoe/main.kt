package demos.tds.tictactoe

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Tic-Tac-Toe",
    ) {
        App()
    }
}