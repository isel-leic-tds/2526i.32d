package demos.tds.puzzle

import demos.tds.puzzle.ui.oo.CommandContext
import demos.tds.puzzle.ui.oo.CommandException
import demos.tds.puzzle.ui.oo.CommandResult
import demos.tds.puzzle.ui.oo.toCommand

/**
 * Supported commands for the sliding puzzle application.
 *  - new <side>: creates a new puzzle with the given side (default is 4)
 *  - move <row> <column>: moves the piece at the given coordinate (0-based)
 *  - shuffle: shuffles the puzzle
 *  - quit: exits the application
 */
fun main() {
    println("Welcome to the sliding puzzle application!")

    var context: CommandContext = CommandContext.Empty

    while (true) {
        print(">> ")
        val input = parsedReadLine() ?: continue

        try {
            val command = input.first.toCommand()
            val params = input.second

            when(val result = command.execute(context, params)) {
                is CommandResult.Exit -> break
                is CommandResult.Success -> context = CommandContext.WithPuzzle(puzzle = result.puzzle)
            }

            display(puzzle = context.puzzle)
        }
        catch (e: CommandException.InvalidParameters) {
            println(e.command.help())
        }
        catch (_: CommandException.Unknown) {
            println("Unknown command: ${input.first}")
        }
    }
}

/**
 * Helper function to parse a line of input from the user.
 * @return a pair containing the command and its parameters, or null if the input is blank.
 */
private fun parsedReadLine(): Pair<String, List<String>>? {
    val input = readlnOrNull()
    if (input == null || input.isBlank()) return null
    val parts = input.split(" ")
    return Pair(first = parts[0], second = parts.drop(n = 1))
}
