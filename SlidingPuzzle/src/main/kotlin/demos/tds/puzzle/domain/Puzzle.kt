package demos.tds.puzzle.domain

private const val DEFAULT_PUZZLE_SIDE = 4

/**
 * Represents sliding puzzles.
 * Sliding puzzles are square shaped and always contain a space that is used to move pieces around.
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

    /**
     * Gets the piece at the given coordinate, or null if the puzzle's space is at that position.
     * @param at The coordinate of the piece.
     * @return The piece or null, if [at] is empty.
     */
    operator fun get(at: Coordinate): Piece? = pieces[at.row * side + at.column]

    /**
     * Creates a coordinate for this puzzle.
     * @param row    The row in the interval 0 until side.
     * @param column The column in the interval 0 until side.
     * @return The coordinate.
     * @throws IllegalArgumentException if [row] or [column] are not valid.
     */
    fun createCoordinate(row: Int, column: Int): Coordinate {
        require(row in 0 until side) { "Invalid row $row" }
        require(column in 0 until side) { "Invalid column $column" }
        return Coordinate(row, column)
    }

    /**
     * Converts an index in the pieces list to a coordinate.
     * @receiver The index in the pieces list.
     * @return The corresponding coordinate.
     */
    private fun Int.toCoordinate(): Coordinate {
        val row = this / side
        val column = this % side
        return Coordinate(row, column)
    }

    /**
     * Converts a coordinate to an index in the pieces list.
     * @receiver The coordinate.
     * @return The corresponding index in the pieces list.
     */
    private fun Coordinate.toIndex(): Int = row * side + column

    /**
     * Moves the piece at the given coordinate to the empty space, if possible.
     * If the piece cannot be moved, returns the same puzzle instance.
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
}

/**
 * Checks whether [side] is an acceptable sliding puzzle size.
 * @param side the side of the square. Must be greater than 1.
 * @return true if [side] is an acceptable sliding puzzle side, false otherwise.
 */
fun isValidPuzzleSide(side: Int): Boolean = side > 1

/**
 * Computes the actual puzzle size. Sliding puzzles are square shaped.
 * @param side  the side of the square. It must be an acceptable puzzle side, as checked by [isValidPuzzleSide].
 * @throws [IllegalArgumentException] if [side] is not an acceptable side for a sliding puzzle.
 */
fun computePuzzleSize(side: Int): Int  {
    require(isValidPuzzleSide(side)) {
        "Side must be greater than 1"
    }
    return side * side
}

