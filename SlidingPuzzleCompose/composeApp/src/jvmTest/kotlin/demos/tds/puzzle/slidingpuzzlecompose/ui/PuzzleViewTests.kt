package demos.tds.puzzle.slidingpuzzlecompose.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import demos.tds.puzzle.slidingpuzzlecompose.domain.Coordinate
import demos.tds.puzzle.slidingpuzzlecompose.domain.Piece
import demos.tds.puzzle.slidingpuzzlecompose.domain.Puzzle
import kotlin.test.Test
import kotlin.test.fail

@OptIn(ExperimentalTestApi::class)
class PuzzleViewTests {

    @Test
    fun `PuzzleView displays puzzle`() = runComposeUiTest {
        val thePuzzle = Puzzle(side = 2)
        setContent {
            PuzzleView(
                puzzle = thePuzzle,
                onMakeMove = { _, _ -> fail("onMakeMove should not be called") }
            )
        }

        thePuzzle.forEach {
            onNodeWithTag(testTag = getTestTagForCellView(piece = it)).assertExists()
        }
    }

    @Test
    fun `click on CellView that may move calls onMakeMove`() = runComposeUiTest {
        val thePuzzle = Puzzle(side = 2)
        val theCoordinate = thePuzzle.createCoordinate(row = 0, column = 1)
        val thePiece = thePuzzle[theCoordinate]
        var actualPiece: Piece? = null
        var actualCoordinate: Coordinate? = null
        setContent {
            PuzzleView(
                puzzle = thePuzzle,
                onMakeMove = { piece, coordinate ->
                    actualPiece = piece
                    actualCoordinate = coordinate
                }
            )
        }

        onNodeWithTag(testTag = getTestTagForCellView(piece = thePiece)).performClick()
        assert(actualPiece == thePiece)
        assert(actualCoordinate == theCoordinate)
    }
}