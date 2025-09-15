package demos.tds.puzzle

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

private const val PUZZLE_TEST_SIDE = 5

class PieceTests {

    @Test
    fun `create Piece with valid number succeeds`() {
        Piece(number = 1, side = PUZZLE_TEST_SIDE)
    }

    @Test
    fun `create Piece with negative number fails`() {
        assertFailsWith<IllegalArgumentException> {
            Piece(number = -2, side = PUZZLE_TEST_SIDE)
        }
    }

    @Test
    fun `create Piece with zero fails`() {
        assertFailsWith<IllegalArgumentException> {
            Piece(number = 0, side = PUZZLE_TEST_SIDE)
        }
    }

    @Test
    fun `create Piece with PUZZLE_SIZE fails`() {
        assertFailsWith<IllegalArgumentException> {
            val number = PUZZLE_TEST_SIDE * PUZZLE_TEST_SIDE
            Piece(number = number, side = PUZZLE_TEST_SIDE)
        }
    }

    @Test
    fun `create Piece with size - 2 succeeds`() {
        val size = PUZZLE_TEST_SIDE * PUZZLE_TEST_SIDE
        Piece(number = size - 2, side = PUZZLE_TEST_SIDE)
    }

    @Test
    fun `initially the puzzle pieces are at the correct positions`() {
        val side = 3
        val sut = Puzzle(side = side)
        for (i in 0 until sut.size - 1) {
            val piece = sut[i]
            assertNotNull(actual = piece)
            assertEquals(expected = i + 1, actual = piece.number)
        }
    }
}