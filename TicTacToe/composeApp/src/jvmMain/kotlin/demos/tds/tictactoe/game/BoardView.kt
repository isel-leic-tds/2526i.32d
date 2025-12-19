package demos.tds.tictactoe.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import demos.tds.tictactoe.common.ui.theme.AppTheme
import demos.tds.tictactoe.game.domain.BOARD_SIDE
import demos.tds.tictactoe.game.domain.Board
import demos.tds.tictactoe.game.domain.Coordinate
import demos.tds.tictactoe.game.domain.Marker
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BoardView(
    board: Board,
    onTileSelected: (at: Coordinate) -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        repeat(BOARD_SIDE) { row ->
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(weight = 1.0f, fill = true)
            ) {
                repeat(BOARD_SIDE) { column ->
                    val at = Coordinate(row, column)
                    TileView(
                        move = board[at],
                        enabled = enabled,
                        modifier = Modifier.weight(weight = 1.0f, fill = true),
                        onSelected = { onTileSelected(at) },
                    )
                    if (column != BOARD_SIDE - 1) { VerticalSeparator() }
                }
            }
            if (row != BOARD_SIDE - 1) { HorizontalSeparator() }
        }
    }
}

@Composable
private fun HorizontalSeparator() {
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(8.dp)
        .background(MaterialTheme.colorScheme.primary)
    )
}

@Composable
private fun VerticalSeparator() {
    Spacer(modifier = Modifier
        .fillMaxHeight()
        .width(8.dp)
        .background(MaterialTheme.colorScheme.primary)
    )
}


@Preview(showBackground = true)
@Composable
private fun EmptyBoardViewPreview() {
    AppTheme {
        BoardView(board = Board(), enabled = true, onTileSelected = { })
    }
}

@Preview(showBackground = true)
@Composable
private fun NonEmptyBoardViewPreview() {
    AppTheme {
        BoardView(board = aBoard, enabled = true, onTileSelected = { })
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
