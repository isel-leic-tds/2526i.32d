package demos.tds.puzzle.slidingpuzzlecompose

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import demos.tds.puzzle.slidingpuzzlecompose.ui.MessageBoard

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "slidingpuzzlecompose",
    ) {
        MessageBoard("SLB GLORIOSO SLB!", "Eu AMO o BENFICA")
    }
}
