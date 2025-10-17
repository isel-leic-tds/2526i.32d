package demos.tds.puzzle.storage.domain

import demos.tds.puzzle.domain.Piece
import demos.tds.puzzle.domain.Puzzle
import demos.tds.puzzle.storage.Serializer


/**
 * Simple serializer for [Puzzle] instances.
 */
class PuzzleSerializer : Serializer<Puzzle> {

    override fun serialize(data: Puzzle): String = data.pieces.joinToString(separator = " ") { it?.face ?: "*" }

    override fun deserialize(data: String): Puzzle? {
        val pieces = data
            .split(" ")
            .map { if (it == "*") null else Piece(face = it) }

        return Puzzle.fromListOrNull(list = pieces)
    }
}
