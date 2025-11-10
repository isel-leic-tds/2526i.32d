package demos.tds.puzzle.slidingpuzzlecompose.domain

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.fail

class PuzzleTests {

    @Test
    fun `create Puzzle succeeds`() {
        Puzzle()
    }

    @Test
    fun `create Puzzle with side 1 fails`() {
        assertFailsWith<IllegalArgumentException> {
            Puzzle(side = 1)
        }
    }

    @Test
    fun `create Puzzle with negative or 0 side fails`() {
        assertFailsWith<IllegalArgumentException> {
            Puzzle(side = -1)
        }
    }

    @Test
    fun `initially empty cell is at the end of the puzzle`() {
        val sut = Puzzle(side = 3)
        assertNull(actual = sut.pieces.last())
    }

    @Test
    fun `initially the puzzle pieces are at the correct positions`() {
        val sut = Puzzle(side = 3)
        sut.take(n = sut.size - 1).forEachIndexed { idx, piece ->
            assertNotNull(actual = piece)
            assertEquals(expected = (idx + 1).toString(), actual = piece.face)
        }
    }

    @Test
    fun `get piece at coordinate returns correct piece`() {
        val sut = Puzzle(side = 3)
        val piece = sut[sut.createCoordinate(row = 1, column = 1)]
        assertNotNull(actual = piece)
        assertEquals(expected = "5", actual = piece.face)
    }

    @Test
    fun `get piece at coordinate of empty cell returns null`() {
        val sut = Puzzle(side = 3)
        val piece = sut[sut.createCoordinate(row = 2, column = 2)]
        assertNull(actual = piece)
    }

    @Test
    fun `computePuzzleSize with side 3 returns 9`() {
        val size = computePuzzleSize(side = 3)
        assertEquals(expected = 9, actual = size)
    }

    @Test
    fun `computePuzzleSize with side -2 throws`() {
        assertFailsWith<IllegalArgumentException> {
            computePuzzleSize(side = -2)
        }
    }

    @Test
    fun `isValidPuzzleSide with side 1 returns false`() {
        assertFalse(actual = isValidPuzzleSide(side = 1))
    }

    @Test
    fun `isValidPuzzleSide with side 2 returns true`() {
        assertTrue(actual = isValidPuzzleSide(side = 2))
    }

    @Test
    fun `movePieceAt adjacent to space moves that piece to the space position`() {

        val sut = Puzzle(side = 3)
        val pieceOrigin = sut.createCoordinate(row = 2, column = 1)
        val spaceOrigin = sut.createCoordinate(row = 2, column = 2)
        val pieceToMove = sut[pieceOrigin]

        val result = sut.movePieceAt(at = pieceOrigin)

        assertEquals(expected = pieceToMove, actual = result[spaceOrigin])
        assertNull(actual = result[pieceOrigin])
    }

    @Test
    fun `movePieceAt not adjacent to space does not move any piece`() {
        val sut = Puzzle(side = 3)
        val pieceOrigin = sut.createCoordinate(row = 0, column = 0)

        val result = sut.movePieceAt(at = pieceOrigin)
        assertEquals(expected = sut, actual = result)
    }

    @Test
    fun `fromListOrNull with empty list returns null`() {
        val result = Puzzle.fromListOrNull(list = emptyList())
        assertNull(actual = result)
    }

    @Test
    fun `fromListOrNull with more than one null returns null`() {
        val result = Puzzle.fromListOrNull(list = List(size = 9) { null })
        assertNull(actual = result)
    }

    @Test
    fun `fromListOrNull with invalid size list returns null`() {
        val size = 8
        val result = Puzzle.fromListOrNull(
            list = buildList {
                repeat(times = size) {
                    add(if (it + 1 == size) null else Piece(face = it.toString()))
                }
            }
        )
        assertNull(actual = result)
    }

    @Test
    fun isSolved_inInitialPuzzle_returns_true() {
        val sut = Puzzle(side = 2)
        assertTrue(actual = sut.isSolved)
    }

    @Test
    fun isSolved_inPuzzleWithOneMove_returns_false() {
        val sut = Puzzle(side = 3)
        sut.movePieceAt(at = sut.createCoordinate(row = 0, column = 0))
        assertFalse(actual = sut.isSolved)
    }

    @Test
    fun isSolved_inShuffledPuzzle_returns_false() {
        val sut = Puzzle(side = 3).shuffle()
        assertFalse(actual = sut.isSolved)
    }
}