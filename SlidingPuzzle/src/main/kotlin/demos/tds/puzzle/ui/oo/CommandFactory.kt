package demos.tds.puzzle.ui.oo

import demos.tds.puzzle.storage.specific.FileSystemStorage

/**
 * Converts a string to a [Command].
 * @return the corresponding [Command] instance.
 * @throws CommandException.Unknown if the string does not correspond to a known command.
 *
 * TODO: Change so that command dependencies are injected.
 * This is to be made using a higher order function to demonstrate the expressive power of Kotlin and the power of
 * functions as first-class citizens. Should I do it when transitioning to the FP style?
 */
@Suppress("UNCHECKED_CAST")
fun String.toCommand(): Command<CommandContext> = when(trim().lowercase()) {
    "new" -> New()
    "move" -> Move()
    "import" -> Import(storage = FileSystemStorage())
    "quit" -> Exit()
    else -> throw CommandException.Unknown(message = "Illegal command: $this")
} as Command<CommandContext>
