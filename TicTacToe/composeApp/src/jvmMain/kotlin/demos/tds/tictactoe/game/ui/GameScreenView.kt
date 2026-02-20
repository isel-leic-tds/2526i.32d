package demos.tds.tictactoe.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import demos.tds.tictactoe.AppScreen
import demos.tds.tictactoe.common.ui.ScreenScaffold
import demos.tds.tictactoe.common.ui.theme.AppTheme
import demos.tds.tictactoe.game.domain.Board
import demos.tds.tictactoe.game.domain.Coordinate
import demos.tds.tictactoe.game.domain.Game
import demos.tds.tictactoe.game.domain.Marker
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import tictactoe.composeapp.generated.resources.*

internal const val GameScreenTitleTag = "GameScreenTitle"
internal const val ForfeitButtonTag = "ForfeitButton"

data class GameScreenState(
    val title: StringResource? = null,
    val game: Game
)

@Composable
fun GameScreenView(
    state: GameScreenState,
    onMoveRequested: (Coordinate) -> Unit = { },
    onForfeitRequested: () -> Unit = { },
) {
    ScreenScaffold(
        title = stringResource(Res.string.game_screen_title),
        modifier = Modifier
            .testTag(tag = AppScreen.Game.name)
            .background(color = Color.White.copy(alpha = 0.9f))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            val titleTextId = when {
                state.title != null -> state.title
                state.game.localPlayerMarker == state.game.board.turn ->
                    Res.string.game_screen_your_turn
                else -> Res.string.game_screen_opponent_turn
            }
            Text(
                text = stringResource(titleTextId),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.testTag(GameScreenTitleTag)
            )
            BoardView(
                board = state.game.board,
                onTileSelected = onMoveRequested,
                enabled = state.game.localPlayerMarker == state.game.board.turn,
                modifier = Modifier
                    .padding(32.dp)
                    //.fillMaxSize(0.75f)
                    .weight(1f, false)
            )
            Button(
                onClick = onForfeitRequested,
                modifier = Modifier.testTag(ForfeitButtonTag)
            ) {
                Text(text = stringResource(Res.string.game_screen_forfeit))
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GameScreenPreview() {
    AppTheme {
        GameScreenView(state = GameScreenState(
            title = null,
            game = Game(localPlayerMarker = Marker.CROSS, board = aBoard)
        ))
    }
}

@Preview(showBackground = true)
@Composable
private fun GameScreenWaiting() {
    AppTheme {
        GameScreenView(state = GameScreenState(
            title = Res.string.game_screen_waiting,
            game = Game(localPlayerMarker = Marker.CROSS, board = Board(Marker.CROSS))
        ))
    }
}

private val aBoard = Board(
    turn = Marker.CIRCLE,
    tiles = listOf(
        listOf(Marker.CROSS, null, Marker.CIRCLE),
        listOf(null, Marker.CROSS, Marker.CIRCLE),
        listOf(Marker.CIRCLE, null, Marker.CROSS)
    )
)
