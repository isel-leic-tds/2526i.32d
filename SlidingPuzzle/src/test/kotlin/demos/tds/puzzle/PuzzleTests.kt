package demos.tds.puzzle

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class PuzzleTests {

    @Test
    fun `create Puzzle succeeds`() {
        Puzzle()
    }

    @Test
    fun `create Puzzle with side 1 fails`() {
        assertThrows<IllegalArgumentException> {
            Puzzle(side = 1)
        }
    }

    @Test
    fun `create Puzzle with negative or 0 side fails`() {
        assertThrows<IllegalArgumentException> {
            Puzzle(side = -1)
        }
    }

    @Test
    fun `initially empty cell is at the end of the puzzle`() {
        val sut = Puzzle(side = 3)
        assertNull(actual = sut[sut.size - 1])
    }

    @Test
    fun `initially the puzzle pieces are at the correct positions`() {
        val side = 3
        val sut = Puzzle(side = side)
        for (i in 0 until sut.size - 1) {
            val piece = sut[i]
            assertNotNull(actual = piece)
            assertEquals(expected = i + 1, actual = piece.face.toInt())
        }
    }

    @Test
    fun `get piece by row and column returns correct piece`() {
        val side = 3
        val sut = Puzzle(side = side)
        for (line in 0 until side) {
            val first = sut[line, 0]
            assertNotNull(actual = first)
            assertEquals(expected = line * side + 1, actual = first.face.toInt())
        }
    }

    @Test
    fun `get piece with invalid index fails`() {
        assertThrows<IllegalArgumentException> {
            val sut = Puzzle(side = 3)
            sut[sut.size]
        }
    }

    @Test
    fun `get piece with invalid row fails`() {
        assertThrows<IllegalArgumentException> {
            val sut = Puzzle(side = 3)
            sut[sut.side, 0]
        }
    }

    @Test
    fun `get piece with invalid column fails`() {
        assertThrows<IllegalArgumentException> {
            val sut = Puzzle(side = 3)
            sut[0, sut.side]
        }
    }

    @Test
    fun `get piece with negative index fails`() {
        assertThrows<IllegalArgumentException> {
            val sut = Puzzle(side = 3)
            sut[-1]
        }
    }

    @Test
    fun `get piece with negative row fails`() {
        assertThrows<IllegalArgumentException> {
            val sut = Puzzle(side = 3)
            sut[-1, 0]
        }
    }

    @Test
    fun `get piece with negative column fails`() {
        assertThrows<IllegalArgumentException> {
            val sut = Puzzle(side = 3)
            sut[0, -1]
        }
    }

    @Test
    fun `move piece adjacent to space moves it and results in correct puzzle state`() {
        val side = 3
        val initialPuzzle = Puzzle(side = side)
        val rowToMove = side - 1
        val columnToMove = side - 2
        val sut = initialPuzzle.movePieceAt(row = rowToMove, column = columnToMove)
        assertNull(actual = sut[rowToMove, columnToMove])
        assertEquals(expected = sut.size - 1, actual = sut[side - 1, side - 1]?.face?.toInt())
    }
}