package demos.tds.tictactoe.lobby

/**
 * Abstraction that characterizes the lobby service. This service encapsulates the logic of a lobby.
 */
interface LobbyService {
    /**
     * Gets the list of users currently in the lobby.
     */
    fun getUsers(): List<User>

    /**
     * The user enters the lobby.
     */
    fun enterLobby(user: User): List<User>

    /**
     * The user leaves the lobby.
     */
    fun leaveLobby(user: User)
}

/**
 * Fake implementation of the lobby service.
 */
@Suppress("unused")
class FakeLobbyService : LobbyService {
    private val users = mutableListOf(
        User(name = "Palecas"),
        User(name = "Darth Vader"),
        User(name = "Goofy"),
        User(name = "Roberto Martinez")
    )

    override fun getUsers(): List<User> {
        Thread.sleep(5000)
        return users.toList()
    }

    override fun enterLobby(user: User): List<User> {
        users.add(user)
        return users.toList()
    }

    override fun leaveLobby(user: User) {
        users.remove(user)
    }
}
