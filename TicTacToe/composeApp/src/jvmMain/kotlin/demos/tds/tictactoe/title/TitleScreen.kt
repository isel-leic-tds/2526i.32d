package demos.tds.tictactoe.title

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import demos.tds.tictactoe.AppScreen
import demos.tds.tictactoe.common.ScreenScaffold

const val TitleScreenStartButtonTag = "StartButtonTag"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleScreen(onStart: () -> Unit = {}) =
    ScreenScaffold(
        title = "Tic-Tac-Toe",
        modifier = Modifier.testTag(tag = AppScreen.Title.name)
    ) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = onStart,
            modifier = Modifier
                .testTag(tag = TitleScreenStartButtonTag)
        ) {
            Text(text = "Start")
        }
    }
}

@Preview
@Composable
private fun TitleScreenPreview() {
    MaterialTheme {
        TitleScreen()
    }
}