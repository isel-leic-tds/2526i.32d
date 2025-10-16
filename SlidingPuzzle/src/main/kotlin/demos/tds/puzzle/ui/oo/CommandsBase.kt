package demos.tds.puzzle.ui.oo

import demos.tds.puzzle.domain.Puzzle

/**
 * Represents a command that can be executed by the application.
 */
sealed interface Command<in T : CommandContext> {

    /**
     * Executes the command with the given parameters.
     * @param params the parameters to pass to the command.
     * @throws CommandException.InvalidParameters if the command cannot be executed with the given parameters.
     */
    fun execute(context: T, params: List<String>): CommandResult

    /**
     * Returns a help message for the command.
     */
    fun help(): String
}

/**
 * Converts a string to a [Command].
 * @return the corresponding [Command] instance.
 * @throws CommandException.Unknown if the string does not correspond to a known command.
 */
@Suppress("UNCHECKED_CAST")
fun String.toCommand(): Command<CommandContext> = when(trim().lowercase()) {
    "new" -> New()
    "move" -> Move()
    "quit" -> Exit()
    else -> throw CommandException.Unknown(message = "Illegal command: $this")
} as Command<CommandContext>

/**
 * Represents the result of executing a command.
 */
sealed interface CommandResult {
    object Exit: CommandResult
    data class Success(val puzzle: Puzzle): CommandResult
}

/**
 * Represents the context in which a command is executed.
 */
sealed interface CommandContext {
    object Empty: CommandContext
    data class WithPuzzle(val puzzle: Puzzle): CommandContext
}
