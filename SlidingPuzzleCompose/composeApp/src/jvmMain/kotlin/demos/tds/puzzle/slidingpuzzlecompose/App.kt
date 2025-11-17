package demos.tds.puzzle.slidingpuzzlecompose

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import demos.tds.puzzle.slidingpuzzlecompose.domain.DEFAULT_PUZZLE_SIDE
import demos.tds.puzzle.slidingpuzzlecompose.domain.Puzzle
import demos.tds.puzzle.slidingpuzzlecompose.ui.PuzzleView

@Composable
fun App() {
    var puzzle by remember { mutableStateOf(Puzzle(DEFAULT_PUZZLE_SIDE).shuffle()) }
    MaterialTheme {
        PuzzleView(
            puzzle = puzzle,
            onMakeMove = { _, coordinate ->
                puzzle = puzzle.movePieceAt(at = coordinate)
            }
        )
    }
}