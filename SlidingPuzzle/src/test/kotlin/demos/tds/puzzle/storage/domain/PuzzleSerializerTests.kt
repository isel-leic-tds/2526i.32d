package demos.tds.puzzle.storage.domain

import demos.tds.puzzle.domain.Puzzle
import kotlin.test.Test
import kotlin.test.assertEquals

class PuzzleSerializerTests {

    @Test
    fun `serialize on a puzzle produces the correct string`() {
        val sut = PuzzleSerializer()
        val expected = "1 2 3 *"
        val actual = sut.serialize(data = Puzzle(side = 2))
        assertEquals(expected, actual)
    }

    @Test
    fun `deserialize on a string produces the correct puzzle`() {
        val sut = PuzzleSerializer()
        val expected = Puzzle(side = 2)
        val actual = sut.deserialize("1 2 3 *")
        assertEquals(expected, actual)
    }
}