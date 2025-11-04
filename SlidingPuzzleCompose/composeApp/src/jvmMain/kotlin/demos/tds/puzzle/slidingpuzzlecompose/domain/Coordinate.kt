package demos.tds.puzzle.slidingpuzzlecompose.domain

import kotlin.math.abs

/**
 * Represents coordinates of the sliding puzzle.
 *
 * IMPORTANT DESIGN NOTE:
 * Coordinates MUST always be valid within the context of a puzzle. This means that row and column are always in
 * interval 0 until side, where side is the side of the puzzle. This is ensured by validating the coordinates
 * upon creation by the puzzle instance.
 *
 * To control the creation of coordinates to ensure they are always valid within the context of a puzzle, the
 * constructor is made internal so that coordinates can only be created within the module. Unfortunately, in Kotlin
 * we cannot ensure that coordinates are not instantiated from elsewhere in the module, but we can document it here.
 *
 * @property row The row index of the coordinate.
 * @property column The column index of the coordinate.
 * @constructor Creates a new instance of Coordinate.
 */
@ConsistentCopyVisibility
data class Coordinate internal constructor(val row: Int, val column: Int)

/**
 * Checks if this coordinate is adjacent to the given [other] coordinate. Two coordinates are considered adjacent if
 * they are next to each other either horizontally or vertically (not diagonally).
 *
 * @param other The other coordinate to check adjacency with.
 * @return True if this coordinate is adjacent to [other], false otherwise.
 */
fun Coordinate.isAdjacentTo(other: Coordinate): Boolean {
    val rowDiff = abs(n = this.row - other.row)
    val columnDiff = abs(n = this.column - other.column)
    return (rowDiff == 1 && columnDiff == 0) || (rowDiff == 0 && columnDiff == 1)
}
