package demos.tds.puzzle.ui.oo

/**
 * The "exit" command.
 * Terminates the application.
 */
class Exit : Command<CommandContext.Empty> {

    override fun execute(context: CommandContext.Empty, params: List<String>) = CommandResult.Exit

    override fun help() = "Usage: exit\nDescription: Exits the application."
}