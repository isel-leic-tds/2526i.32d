package demos.tds.puzzle.slidingpuzzlecompose.domain

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class PuzzleDslTests {

    @Test
    fun `puzzle with side two is correctly built`() {
        val sut = puzzle {
            row(1, 2)
            side = 2
            row(3, 0)
        }

        assertEquals(expected = sut.size, actual = 4)
        assertNull(actual = sut[3])
        assert(sut[0]?.face == "1")
    }
}