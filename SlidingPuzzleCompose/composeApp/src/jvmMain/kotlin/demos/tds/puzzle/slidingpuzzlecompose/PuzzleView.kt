package demos.tds.puzzle.slidingpuzzlecompose

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import demos.tds.puzzle.slidingpuzzlecompose.domain.Coordinate
import demos.tds.puzzle.slidingpuzzlecompose.domain.Piece
import demos.tds.puzzle.slidingpuzzlecompose.domain.Puzzle

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


@Composable
fun CellView(piece: Piece?, coordinate: Coordinate, onClick: (Piece?, Coordinate) -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size = 140.dp)
            .border(width = 1.dp, color = Color.Black)
            .clickable(enabled = piece != null) { onClick(piece, coordinate) }
    ) {
        piece?.let {
            Text(
                fontSize = 24.sp,
                text = piece.face
            )
        }
    }
}

@Preview
@Composable
fun CellViewPreview() {
    CellView(
        piece = Piece(face = "1"),
        coordinate = Coordinate(0, 0),
        onClick = { _, _ -> }
    )
}

@Preview
@Composable
fun PuzzleViewPreview() {
    PuzzleView(puzzle = Puzzle(side = 3), onMakeMove = { _, _ -> })
}