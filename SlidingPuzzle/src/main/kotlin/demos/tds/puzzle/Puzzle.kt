package demos.tds.puzzle

private const val DEFAULT_PUZZLE_SIDE = 4

/**
 * Represents sliding puzzles.
 * Sliding puzzles are square shaped and always contain a space that is used to move pieces around.
 *
 * @property side The puzzle's side.
 * @constructor Creates a new instance of Puzzle.
 */
data class Puzzle(val side: Int = DEFAULT_PUZZLE_SIDE) {

    /**
     * Represents pieces of the puzzle.
     * @property face The piece face, used to identify the piece
     * @constructor Creates a new instance of Piece.
     */
    data class Piece(val face: String)

    /**
     * The list of pieces in the puzzle. The empty space is represented by null.
     */
    private val pieces: List<Piece?>

    init {
        require(value = isValidPuzzleSide(side)) {
            "Side must be greater than 1"
        }
        pieces = buildList {
            repeat(times = computePuzzleSize(side) - 1) {
                add(Piece(face = (it + 1).toString()))
            }
            add(null)
        }
    }

    /**
     * @property size The puzzle size
     */
    val size: Int = pieces.size

    /**
     * Gets the piece at the given index, or null if the puzzle's space is at that position.
     * @param idx The puzzle index in the interval 0 until size
     * @return The piece or null, if at [idx] is the puzzle's empty space
     * @throws IllegalArgumentException if [idx] is not a valid index
     */
    operator fun get(idx: Int): Piece? {
        require(idx in 0 until size) { "Invalid index $idx" }
        return pieces[idx]
    }

    /**
     * Gets the piece at the given row and column, or null if the puzzle's space is at that position.
     * @param row    The row in the interval 0 until side
     * @param column The column in the interval 0 until side
     * @return The piece or null, if at [row], [column] is the puzzle's empty space
     * @throws IllegalArgumentException if [row] or [column] are not valid
     */
    operator fun get(row: Int, column: Int): Piece? {
        require(row in 0 until side) { "Invalid row $row" }
        require(column in 0 until side) { "Invalid column $column" }
        return pieces[row * side + column]
    }
}

/**
 * Checks whether [side] is an acceptable sliding puzzle size.
 */
fun isValidPuzzleSide(side: Int): Boolean = side > 1

/**
 * Computes the actual puzzle size. Sliding puzzles are square shaped.
 * @param side  the side of the square. It must be an acceptable puzzle side, as checked by [isValidPuzzleSide].
 * @throws [IllegalArgumentException] if [side] is not an acceptable side for a sliding puzzle.
 */
fun computePuzzleSize(side: Int): Int = side * side

/**
 * Moves the puzzle piece located at [row], [column], returning the new puzzle.
 */
fun Puzzle.movePieceAt(row: Int, column: Int): Puzzle {
    return this
}
