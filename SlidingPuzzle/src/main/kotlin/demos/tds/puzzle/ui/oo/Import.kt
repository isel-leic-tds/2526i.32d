package demos.tds.puzzle.ui.oo

import demos.tds.puzzle.domain.Puzzle
import demos.tds.puzzle.storage.Storage

class Import(private val storage: Storage<String, Puzzle>) : Command<CommandContext.Empty> {

    override fun execute(context: CommandContext.Empty, params: List<String>): CommandResult {

        val file = params.firstOrNull()
        when (file) {
            null -> throw CommandException.InvalidParameters(command = this)
            else -> {
                TODO("Not yet implemented")
            }
        }
    }

    override fun help(): String {
        return "Usage: import [filename]\nDescription: Imports a puzzle from a file."
    }
}