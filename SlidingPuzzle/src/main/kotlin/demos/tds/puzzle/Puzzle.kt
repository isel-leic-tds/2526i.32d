package demos.tds.puzzle

private const val PUZZLE_SIDE = 4
private const val PUZZLE_SIZE = PUZZLE_SIDE * PUZZLE_SIDE

data class Piece(
    val number: Int,
    val side: Int = PUZZLE_SIDE
) {
    val size = side * side

    init {
        require(value = number in 1..size - 1) {
            "Invalid number $number"
        }
    }
}

data class Puzzle(val side: Int = PUZZLE_SIDE) {
    private val pieces: List<Piece?>

    init {
        require(value = side > 1) {
            "Side must be greater than 1"
        }
        pieces = buildList {
            repeat(times = (side * side) - 1) {
                add(Piece(number = it + 1, side = side))
            }
            add(null)
        }
    }

    val size: Int = pieces.size

    operator fun get(idx: Int): Piece? = pieces[idx]

    operator fun get(row: Int, column: Int): Piece? =
        get(idx = row * side + column)
}

fun Puzzle.movePieceAt(row: Int, column: Int): Puzzle {
    return this
}