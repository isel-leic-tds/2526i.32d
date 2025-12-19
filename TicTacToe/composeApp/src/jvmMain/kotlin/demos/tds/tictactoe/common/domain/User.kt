package demos.tds.tictactoe.common.domain

/**
 * Represents a candidate player in the Tic-Tac-Toe game. Candidate players are those who have not yet joined the
 * game yet and are still waiting in the lobby.
 */
data class User(val name: String) {
    init {
        require(name.isValidUserName()) { "Username cannot be blank" }
    }
}

/**
 * Checks if the given string is a valid username.
 */
fun String.isValidUserName() = !isBlank()


/**
 * Converts this string to a [User] object, or returns null if the string is not a valid username.
 */
fun String.toUserOrNull() = if (isValidUserName()) User(this) else null