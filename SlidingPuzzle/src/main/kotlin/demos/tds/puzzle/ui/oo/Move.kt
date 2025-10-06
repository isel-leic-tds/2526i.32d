package demos.tds.puzzle.ui.oo

/**
 * The "move" command.
 * Moves the piece at the given coordinates if possible.
 */
class Move : Command {

    override fun execute(context: CommandContext, params: List<String>): CommandResult {

        val puzzle = if (context !is CommandContext.WithPuzzle) {
            throw CommandException.IllegalContext(message = "Move command requires a puzzle")
        }
        else {
            context.puzzle
        }

        val row = params.getOrNull(index = 0)?.toIntOrNull()
        val column = params.getOrNull(index = 1)?.toIntOrNull()
        val at = if (row != null && column != null) {
            puzzle.createCoordinateOrNull(row = row, column = column)
        } else null

        return when {
            row == null || column == null -> throw CommandException.InvalidParameters(command = this)
            at == null -> throw CommandException.InvalidParameters(command = this)
            else -> CommandResult.Success(puzzle = puzzle.movePieceAt(at))
        }
    }

    override fun help(): String {
        return "Usage: move [row] [column]\nDescription: Moves the piece at position with row and column. " +
                "Coordinates are zero indexed and must be positive integers withing the puzzle bounds"
    }
}