package demos.tds.tictactoe

/**
 * Represents a candidate player in the Tic-Tac-Toe game. Candidate players are those who have not yet joined the
 * game yet and are still waiting in the lobby.
 */
data class User(val name: String) {
    init {
        require(name.isNotBlank()) { "User name cannot be blank" }
    }
}
