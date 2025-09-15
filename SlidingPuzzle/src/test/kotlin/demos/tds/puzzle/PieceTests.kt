package demos.tds.puzzle

import kotlin.test.Test
import kotlin.test.assertFailsWith

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
}