package demos.tds.puzzle.ui.oo

/**
 * Base class for exceptions that occur during command execution.
 * @param message the error message.
 */
sealed class CommandException(message: String): Exception(message) {
    /**
     * Represents an error that occurs the command cannot be executed with the given parameters.
     * For example, if a command requires a parameter, but none is provided.
     * @param message the error message.
     */
    class InvalidParameters(val command: Command<*>, message: String = ""): CommandException(message)

    /**
     * Represents an error that occurs when a command is not recognized.
     * @param message the error message.
     */
    class Unknown(message: String = ""): CommandException(message)
}
