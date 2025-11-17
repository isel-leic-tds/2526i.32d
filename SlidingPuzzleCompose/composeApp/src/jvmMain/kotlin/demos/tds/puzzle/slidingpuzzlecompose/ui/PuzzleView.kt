package demos.tds.puzzle.slidingpuzzlecompose.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import demos.tds.puzzle.slidingpuzzlecompose.domain.Coordinate
import demos.tds.puzzle.slidingpuzzlecompose.domain.Piece
import demos.tds.puzzle.slidingpuzzlecompose.domain.Puzzle

/**
 *
 */
@Composable
fun PuzzleView(puzzle: Puzzle, onMakeMove: (Piece, Coordinate) -> Unit) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        repeat(times = puzzle.side) { row ->
            Row {
                repeat(times = puzzle.side) { column ->
                    val coordinate = puzzle.createCoordinate(row, column)
                    CellView(
                        piece = puzzle[coordinate],
                        coordinate = coordinate,
                        onClick = { maybePiece, coordinate ->
                            maybePiece?.let { onMakeMove(it, coordinate) }
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PuzzleViewPreview() {
    PuzzleView(puzzle = Puzzle(side = 3), onMakeMove = { _, _ -> })
}