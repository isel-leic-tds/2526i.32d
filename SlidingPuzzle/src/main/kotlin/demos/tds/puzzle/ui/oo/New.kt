package demos.tds.puzzle.ui.oo

import demos.tds.puzzle.domain.DEFAULT_PUZZLE_SIDE
import demos.tds.puzzle.domain.Puzzle
import demos.tds.puzzle.ui.oo.CommandException.InvalidParameters

/**
 * The "new" command.
 * Creates a new puzzle with the given side (default is [DEFAULT_PUZZLE_SIDE])."
 */
class New() : Command<CommandContext.Empty> {

    override fun execute(context: CommandContext.Empty, params: List<String>): CommandResult {
        val puzzleSide = when {
            params.size > 1 -> throw InvalidParameters(command = this)
            params.isNotEmpty() -> params[0].toIntOrNull() ?: throw InvalidParameters(command = this)
            else -> DEFAULT_PUZZLE_SIDE
        }
        return CommandResult.Success(puzzle = Puzzle(side = puzzleSide))
    }

    override fun help(): String {
        return "Usage: new [side]\nDescription: Creates a new puzzle, where side is an optional integer greater than 1. " +
                "Default value is $DEFAULT_PUZZLE_SIDE."
    }
}
