package demos.tds.puzzle.slidingpuzzlecompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import demos.tds.puzzle.slidingpuzzlecompose.domain.Coordinate
import demos.tds.puzzle.slidingpuzzlecompose.domain.Piece
import demos.tds.puzzle.slidingpuzzlecompose.domain.Puzzle

@Composable
fun PuzzleView(puzzle: Puzzle, onMakeMove: (Coordinate) -> Unit) {

    Column {
        repeat(times = puzzle.side) { row ->
            Row {
                repeat(times = puzzle.side) { column ->
                    val coordinate = puzzle.createCoordinate(row, column)
                    CellView(piece = puzzle.get(coordinate), coordinate = coordinate)
                }
            }
        }
    }
}


@Composable
fun CellView(piece: Piece?, coordinate: Coordinate) {
    Box(modifier = Modifier.size(60.dp)) {
        piece?.let {
            Text(text = piece.face)
        }
    }
}