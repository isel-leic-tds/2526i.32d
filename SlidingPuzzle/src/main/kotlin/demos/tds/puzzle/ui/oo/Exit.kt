package demos.tds.puzzle.ui.oo

/**
 * The "exit" command.
 * Terminates the application.
 */
class Exit : Command {

    override fun execute(context: CommandContext, params: List<String>) = CommandResult.Exit

    override fun help() = "Usage: exit\nDescription: Exits the application."
}