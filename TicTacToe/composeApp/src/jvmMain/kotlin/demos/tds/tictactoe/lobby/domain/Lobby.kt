package demos.tds.tictactoe.lobby.domain

import demos.tds.tictactoe.common.domain.User
import kotlinx.coroutines.delay

/**
 * Abstraction that characterizes a lobby.
 */
interface Lobby {
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
 * Fake implementation of the lobby.
 */
@Suppress("unused")
class FakeLobby : Lobby {


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
