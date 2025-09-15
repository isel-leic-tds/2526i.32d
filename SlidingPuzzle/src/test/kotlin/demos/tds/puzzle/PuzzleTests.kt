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
    fun `initially first cell is at the start of the puzzle`() {
        val sut = Puzzle(side = 3)
        assertNotNull(actual = sut[0])
        assertEquals(expected = 1, actual = sut[0]?.number)
    }
}