package demos.tds.puzzle.domain

/**
 * Represents pieces of the puzzle. Instances of this class can only be created within the module, specifically,
 * by the [Puzzle] class.
 *
 * @property face The piece face, used to identify the piece
 * @constructor Creates a new instance of Piece.
 */
@ConsistentCopyVisibility
data class Piece internal constructor(val face: String)