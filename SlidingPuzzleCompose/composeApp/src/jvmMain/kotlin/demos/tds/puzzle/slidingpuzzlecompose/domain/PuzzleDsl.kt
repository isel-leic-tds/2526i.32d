package demos.tds.puzzle.slidingpuzzlecompose.domain


/**
 * Builds a [Puzzle] instance using the DSL.
 */
fun puzzle(addContent: PuzzleBuilder.() -> Unit): Puzzle {
    val puzzleBuilder = PuzzleBuilder()
    puzzleBuilder.addContent()
    return puzzleBuilder.build()
}

class PuzzleBuilder {

    var side: Int? = null
    private val rows = mutableListOf<Int>()

    fun row(vararg values: Int) {
        requireNotNull(value = side) { "The side must be set before adding a row" }
        require(values.size == side) {
            "The size of the row must be equal to the side of the puzzle which is $side"
        }
        rows.addAll(values.toList())
    }

    fun build(): Puzzle = Puzzle.fromListOrThrows(
        list = rows.map { if (it == 0) null else Piece(face = it.toString()) }
    )
}