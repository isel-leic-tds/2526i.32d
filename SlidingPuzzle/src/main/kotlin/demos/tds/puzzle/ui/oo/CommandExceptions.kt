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
    class InvalidParameters(val command: Command, message: String = ""): CommandException(message)

    /**
     * Represents an error that occurs when a command is not recognized.
     * @param message the error message.
     */
    class Unknown(message: String = ""): CommandException(message)

    /**
     * TODO:
     * I want to remove this class and substitute it with a compile time check for the context parameter.
     */
    class IllegalContext(message: String): CommandException(message)
}
