package demos.tds.demoshost

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import demos.tds.demoshost.board.MessageBoardApp
import demos.tds.demoshost.counter.TallyCounterApp

enum class Demos {
    MessageBoardApp,
    TallyCounterApp
}

fun main() = application {
    var currentDemo by remember { mutableStateOf(Demos.MessageBoardApp) }
    Window(
        onCloseRequest = ::exitApplication,
        title = "TDS Demos",
    ) {
        MenuBar {
            Menu(text = "Demos") {
                Item(text = "Message Board", onClick = { currentDemo = Demos.MessageBoardApp })
                Item(text = "Tally Counter", onClick = { currentDemo = Demos.TallyCounterApp })
            }
        }
        when (currentDemo) {
            Demos.MessageBoardApp -> MessageBoardApp()
            Demos.TallyCounterApp -> TallyCounterApp()
        }
    }
}