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

data class Puzzle(
    val side: Int = PUZZLE_SIDE,
    val size: Int = side * side
) {
    operator fun get(idx: Int): Piece? {
        return null
    }
}