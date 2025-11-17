package demos.tds.puzzle.slidingpuzzlecompose.ui

import androidx.compose.ui.test.*
import demos.tds.puzzle.slidingpuzzlecompose.domain.Coordinate
import demos.tds.puzzle.slidingpuzzlecompose.domain.Piece
import demos.tds.puzzle.slidingpuzzlecompose.domain.Puzzle
import kotlin.test.Test
import kotlin.test.fail

/**
 * Tests for [CellView]
 * For more information on Compose Multiplatform UI testing, see here:
 * https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html
 */
@OptIn(ExperimentalTestApi::class)
class CellViewTests {

    private val thePuzzle = Puzzle(side = 3)

    @Test
    fun `CellView with puzzle piece displays its face value`() = runComposeUiTest {
        val expectedFace = "1"
        val expectedCoordinate = thePuzzle.createCoordinate(row = 0, column = 0)
        val thePiece = Piece(face = expectedFace)

        setContent {
            CellView(
                piece = thePiece,
                coordinate = expectedCoordinate,
                onClick = { _, _ -> fail("onClick should not be called") },
            )
        }

        val testTag = getTestTagForCellView(thePiece)
        onNodeWithTag(testTag).assertTextEquals(expectedFace)
    }

    @Test
    fun `CellView with null puzzle piece does not display any text`() = runComposeUiTest {
        val emptyPieceFace = ""
        setContent {
            CellView(
                piece = null,
                coordinate = thePuzzle.createCoordinate(row = 0, column = 0),
                onClick = { _, _ -> fail("onClick should not be called") },
            )
        }

        // TODO: Review how to check if cell view has no text
        fail("Implement test")
    }

    @Test
    fun `CellView with null puzzle piece is disabled`() = runComposeUiTest {
        val thePiece = null

        setContent {
            CellView(
                piece = thePiece,
                coordinate = thePuzzle.createCoordinate(row = 0, column = 0),
                onClick = { _, _ -> fail("onClick should not be called") },
            )
        }

        val tag = getTestTagForCellView(thePiece)
        onNodeWithTag(tag).assertIsNotEnabled()
    }

    @Test
    fun `CellView with puzzle piece is enabled`() = runComposeUiTest {
        val expectedFace = "1"
        val expectedCoordinate = thePuzzle.createCoordinate(row = 0, column = 0)
        val thePiece = Piece(face = expectedFace)

        setContent {
            CellView(
                piece = thePiece,
                coordinate = expectedCoordinate,
                onClick = { _, _ -> fail("onClick should not be called") },
            )
        }

        val tag = getTestTagForCellView(thePiece)
        onNodeWithTag(tag).assertIsEnabled()
    }

    @Test
    fun `click on CellView with puzzle piece calls click action`() = runComposeUiTest {
        val expectedCoordinate = thePuzzle.createCoordinate(row = 0, column = 0)
        val expectedPiece = Piece("1")
        var actualPiece: Piece? = null
        var actualCoordinate: Coordinate? = null
        setContent {
            CellView(
                piece = expectedPiece,
                coordinate = expectedCoordinate,
                onClick = { originPiece, originCoordinate ->
                    actualPiece = originPiece
                    actualCoordinate = originCoordinate
                },
            )
        }

        val tag = getTestTagForCellView(expectedPiece)
        onNodeWithTag(tag).performClick()
        assert(actualPiece == expectedPiece)
        assert(actualCoordinate == expectedCoordinate)
    }
}