package demos.tds.puzzle.ui.oo

import demos.tds.puzzle.domain.Puzzle
import demos.tds.puzzle.storage.Storage

class Export(private val storage: Storage<String, Puzzle>) : Command<CommandContext.WithPuzzle> {

    override fun execute(context: CommandContext.WithPuzzle, params: List<String>): CommandResult  {
        val file = params.firstOrNull() ?: throw CommandException.InvalidParameters(command = this)
        storage.update(key = file, data = context.puzzle)
        return CommandResult.Success(puzzle = context.puzzle)
    }

    override fun help(): String {
        return "Usage: import [filename]\nDescription: Imports a puzzle from a file."
    }
}
