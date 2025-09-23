package demos.tds.puzzle

import demos.tds.puzzle.domain.Puzzle

fun display(puzzle: Puzzle) {
    val side = puzzle.side
    for (row in 0 until side) {
        for (column in 0 until side) {
            val piece = puzzle[puzzle.createCoordinate(row, column)]
            if (piece == null) {
                print(" - ")
            } else {
                print(String.format("%2s ", piece.face))
            }
        }
        println()
    }
}