package demos.tds.puzzle.slidingpuzzlecompose.domain

import kotlin.math.sqrt

const val DEFAULT_PUZZLE_SIDE = 4

/**
 * Represents sliding puzzles.
 * Sliding puzzles are square-shaped and always contain a space used to move pieces around.
 *
 * @property side The side of the square puzzle. Must be greater than 1.
 * @property size The total number of pieces in the puzzle, including the empty space.
 * @property pieces The list of pieces in the puzzle.
 */
@ConsistentCopyVisibility
data class Puzzle private constructor(
    val side: Int,
    val size: Int,
    val pieces: List<Piece?>
) : Iterable<Piece?> by pieces {

    init {
        require(value = isValidPuzzleSide(side)) {
            "Side must be greater than 1"
        }
    }

    /**
     * Creates a new instance of Puzzle with the given side.
     * The pieces are initialized in order, with the empty space at the end.
     * @param side The side of the square puzzle. Must be greater than 1.
     */
    constructor(side: Int = DEFAULT_PUZZLE_SIDE) : this(
        side = side,
        size = computePuzzleSize(side),
        pieces = buildList {
            repeat(times = computePuzzleSize(side) - 1) {
                add(Piece(face = (it + 1).toString()))
            }
            add(null)
        }
    )

    companion object {

        fun fromListOrNull(list: List<Piece?>): Puzzle? = when {
            !isValidPuzzleSize(list.size) -> null
            list.count { it == null } != 1 -> null
            else -> Puzzle(
                side = sqrt(list.size.toDouble()).toInt(),
                size = list.size,
                pieces = list
            )
        }
    }

    private fun isValidRowIndex(row: Int): Boolean = row in 0 until side
    private fun isValidColumnIndex(column: Int): Boolean = column in 0 until side

    /**
     * Gets the piece at the given coordinate, or null if the puzzle's space is at that position.
     * @param at The coordinate of the piece.
     * @return The piece or null, if [at] is empty.
     */
    operator fun get(at: Coordinate): Piece? = pieces[at.row * side + at.column]

    /**
     * Creates a coordinate for this puzzle.
     * @param row    The row in interval 0 until side.
     * @param column The column in interval 0 until side.
     * @return The coordinate.
     * @throws IllegalArgumentException if [row] or [column] are not valid.
     */
    fun createCoordinate(row: Int, column: Int): Coordinate {
        require(value = isValidRowIndex(row)) { "Invalid row $row" }
        require(value = isValidColumnIndex(column)) { "Invalid column $column" }
        return Coordinate(row, column)
    }

    /**
     * Creates a coordinate for this puzzle, or null if the coordinates are invalid.
     */
    fun createCoordinateOrNull(row: Int, column: Int): Coordinate? =
        if (isValidRowIndex(row) && isValidColumnIndex(column)) {
            Coordinate(row, column)
        } else {
            null
        }

    /**
     * Converts an index in the piece list to a coordinate.
     * @receiver The index in the pieces list.
     * @return The corresponding coordinate.
     */
    private fun Int.toCoordinate(): Coordinate {
        val row = this / side
        val column = this % side
        return Coordinate(row, column)
    }

    /**
     * Converts a coordinate to an index in the piece list.
     * @receiver The coordinate.
     * @return The corresponding index in the piece list.
     */
    private fun Coordinate.toIndex(): Int = row * side + column

    /**
     * Moves the piece at the given coordinate to the empty space, if possible.
     * If the piece cannot be moved, it returns the same puzzle instance.
     * @param at The coordinate of the piece to move.
     * @return A new puzzle instance with the piece moved, or the same instance if the piece cannot be moved.
     */
    fun movePieceAt(at: Coordinate): Puzzle {
        val indexOfSpace = pieces.indexOf(null)
        return if (at.isAdjacentTo(other = indexOfSpace.toCoordinate())) {
            val indexOfPiece = at.toIndex()
            val pieceToMove = this[at]
            val newPieces: List<Piece?> = pieces.mapIndexed { index, piece ->
                when (index) {
                    indexOfPiece -> null
                    indexOfSpace -> pieceToMove
                    else -> piece
                }
            }
            Puzzle(side, size, newPieces)
        } else {
            this
        }
    }

    /**
     * Creates a new puzzle from shuffling the pieces in this one.
     * @return A new puzzle instance with its pieces shuffled.
     */
    fun shuffle(): Puzzle = Puzzle(
        side = side,
        size = size,
        pieces = pieces.toMutableList().apply { shuffle() }
    )
}

/**
 * Checks whether [side] is an acceptable sliding puzzle size.
 * @param side the side of the square. Must be greater than 1.
 * @return true if [side] is an acceptable sliding puzzle side, false otherwise.
 */
fun isValidPuzzleSide(side: Int): Boolean = side > 1

/**
 * Computes the actual puzzle size. Sliding puzzles are square-shaped.
 * @param side  the side of the square. It must be an acceptable puzzle side, as checked by [isValidPuzzleSide].
 * @throws [IllegalArgumentException] if [side] is not an acceptable side for a sliding puzzle.
 */
fun computePuzzleSize(side: Int): Int {
    require(isValidPuzzleSide(side)) {
        "Side must be greater than 1"
    }
    return side * side
}

/**
 * Converts an integer to a valid puzzle side. If the integer is not a valid puzzle side, the return value is the nearest
 * valid puzzle side smaller than the integer.
 */
private fun Int.toPuzzleSide(): Int = sqrt(this.toDouble()).toInt()

/**
 * Checks whether [size] is a valid puzzle size.
 */
fun isValidPuzzleSize(size: Int): Boolean =
    size.toPuzzleSide().let { it > 1 && size == computePuzzleSize(side = it) }

/**
 * Checks whether this puzzle is solved.
 * @return true if the puzzle is solved, false otherwise.
 */
val Puzzle.isSolved: Boolean
    get() = true