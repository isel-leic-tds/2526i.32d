package demos.tds.puzzle.domain

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
    fun `move piece adjacent to space moves it and results in correct puzzle state`() {
        fail("Not yet implemented")
    }
}