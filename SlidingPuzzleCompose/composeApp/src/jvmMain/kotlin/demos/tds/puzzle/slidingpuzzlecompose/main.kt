package demos.tds.puzzle.slidingpuzzlecompose

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Sliding Puzzle",
    ) {
        App()
    }
}
