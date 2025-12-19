package demos.tds.tictactoe.lobby

import demos.tds.tictactoe.common.domain.User
import kotlinx.coroutines.delay

/**
 * Abstraction that characterizes the lobby service. This service encapsulates the logic of a lobby.
 */
interface LobbyService {
    /**
     * Gets the list of users currently in the lobby.
     */
    suspend fun getUsers(): List<User>

    /**
     * The user enters the lobby.
     */
    suspend fun enterLobby(user: User): List<User>

    /**
     * The user leaves the lobby.
     */
    suspend fun leaveLobby(user: User)
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

    override suspend fun getUsers(): List<User> {
        delay(3000)
        return users.toList()
    }

    override suspend fun enterLobby(user: User): List<User> {
        delay(5000)
        users.add(user)
        return users.toList()
    }

    override suspend fun leaveLobby(user: User) {
        delay(1000)
        users.remove(user)
    }
}
