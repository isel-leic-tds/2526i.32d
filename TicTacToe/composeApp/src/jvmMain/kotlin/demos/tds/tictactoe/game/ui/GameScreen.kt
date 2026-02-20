package demos.tds.tictactoe.game.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import demos.tds.tictactoe.AppScreen
import demos.tds.tictactoe.common.ui.ScreenScaffold

@Composable
fun GameScreen() {
    ScreenScaffold(modifier = Modifier.testTag(tag = AppScreen.Game.name)) {
    }
}

@Preview
@Composable
private fun GameScreenPreview() = GameScreen()