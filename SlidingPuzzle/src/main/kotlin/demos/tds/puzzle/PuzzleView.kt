package demos.tds.puzzle

fun display(puzzle: Puzzle) {
    val side = puzzle.side
    for (row in 0 until side) {
        for (col in 0 until side) {
            val piece = puzzle[row, col]
            if (piece == null) {
                print(" - ")
            } else {
                print(String.format("%2d ", piece.number))
            }
        }
        println()
    }
}