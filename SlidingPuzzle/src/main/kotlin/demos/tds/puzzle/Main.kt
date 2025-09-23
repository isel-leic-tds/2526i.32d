package demos.tds.puzzle

import demos.tds.puzzle.domain.Puzzle


fun main() {
    val puzzle = Puzzle()
    display(puzzle = puzzle)

    println()

    val movedPuzzle = puzzle.movePieceAt(at = puzzle.createCoordinate(row = 3, column = 2))
    display(puzzle = movedPuzzle)
}