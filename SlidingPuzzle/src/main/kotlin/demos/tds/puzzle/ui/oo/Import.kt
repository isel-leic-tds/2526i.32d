package demos.tds.puzzle.ui.oo

import demos.tds.puzzle.domain.Puzzle
import demos.tds.puzzle.storage.Storage

class Import(private val storage: Storage<String, Puzzle>) : Command<CommandContext.Empty> {

    override fun execute(context: CommandContext.Empty, params: List<String>): CommandResult =
        params.firstOrNull()?.let { file ->
            storage.read(file)?.let {
                CommandResult.Success(puzzle = it)
            } ?: throw CommandException.InvalidParameters(
                command = this,
                message = "Could not import puzzle using key: $file"
            )
        } ?: throw CommandException.InvalidParameters(command = this)


    override fun help(): String {
        return "Usage: import [filename]\nDescription: Imports a puzzle from a file."
    }
}
