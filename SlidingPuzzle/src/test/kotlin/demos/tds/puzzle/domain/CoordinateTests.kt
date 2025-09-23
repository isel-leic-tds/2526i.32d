package demos.tds.puzzle.domain

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CoordinateTests {

    @Test
    fun `createCoordinate with negative row throws`() {
        assertFailsWith<IllegalArgumentException> {
            val sut = Puzzle(side = 3)
            sut.createCoordinate(row = -1, column = 2)
        }
    }

    @Test
    fun `createCoordinate with row equal to side throws`() {
        assertFailsWith<IllegalArgumentException> {
            val sut = Puzzle(side = 3)
            sut.createCoordinate(row = 3, column = 2)
        }
    }

    @Test
    fun `createCoordinate with negative column throws`() {
        assertFailsWith<IllegalArgumentException> {
            val sut = Puzzle(side = 3)
            sut.createCoordinate(row = 2, column = -1)
        }
    }

    @Test
    fun `createCoordinate with column equal to side throws`() {
        assertFailsWith<IllegalArgumentException> {
            val sut = Puzzle(side = 3)
            sut.createCoordinate(row = 2, column = 3)
        }
    }

    @Test
    fun `isAdjacentTo between top left corner and bottom right returns false`() {
        with(receiver = Puzzle(side = 3)) {
            val topLeft = createCoordinate(row = 0, column = 0)
            val result = topLeft.isAdjacentTo(other = createCoordinate(row = 2, column = 2))
            assertFalse(actual = result)
        }
    }

    @Test
    fun `isAdjacentTo between top left corner and position to its right returns true`() {
        with(receiver = Puzzle(side = 3)) {
            val topLeft = createCoordinate(row = 0, column = 0)
            val result = topLeft.isAdjacentTo(other = createCoordinate(row = 0, column = 1))
            assertTrue(actual = result)
        }
    }

    @Test
    fun `isAdjacentTo between top left corner and position below returns true`() {
        with(receiver = Puzzle(side = 3)) {
            val topLeft = createCoordinate(row = 0, column = 0)
            val result = topLeft.isAdjacentTo(other = createCoordinate(row = 1, column = 0))
            assertTrue(actual = result)
        }
    }

    @Test
    fun `isAdjacentTo between top left corner and position diagonally returns false`() {
        with(receiver = Puzzle(side = 3)) {
            val topLeft = createCoordinate(row = 0, column = 0)
            val result = topLeft.isAdjacentTo(other = createCoordinate(row = 1, column = 1))
            assertFalse(actual = result)
        }
    }

    @Test
    fun `isAdjacentTo between two identical positions returns false`() {
        with(receiver = Puzzle(side = 3)) {
            val topLeft = createCoordinate(row = 0, column = 0)
            val result = topLeft.isAdjacentTo(other = createCoordinate(row = 0, column = 0))
            assertFalse(actual = result)
        }
    }
}