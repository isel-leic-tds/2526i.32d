package demos.tds.tictactoe.lobby.infrastructure

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.MongoClient
import com.mongodb.kotlin.client.MongoDatabase
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.lobby.domain.Lobby
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private const val LOBBY_COLLECTION_NAME = "lobby"

/**
 * Implementation of [Lobby] that uses MongoDB as a backing store.
 */
class LobbyMongoDB(private val dbClient: MongoClient, private val dbName: String) : Lobby {

    private val logger: Logger = LoggerFactory.getLogger("LobbyServiceMongoDB")

    private val database: MongoDatabase by lazy {
        dbClient.getDatabase(databaseName = dbName)
    }

    /**
     * Retrieves all users from the database. It's a blocking I/O operation.
     */
    private fun getAllUsers(): List<User> = database
        .getCollection<User>(LOBBY_COLLECTION_NAME)
        .find()
        .toList()

    override suspend fun getUsers(): List<User> =
        withContext(Dispatchers.IO) {
            logger.info("Retrieving users in the lobby from database")
            getAllUsers()
        }

    override suspend fun enterLobby(user: User): List<User> =
        withContext(Dispatchers.IO) {
            logger.info("Adding user ${user.name} to lobby")
            database
                .getCollection<User>(LOBBY_COLLECTION_NAME)
                .insertOne(user)
            getAllUsers()
        }

    override suspend fun leaveLobby(user: User) {
        withContext(Dispatchers.IO) {
            logger.info("Removing user ${user.name} from lobby")
            database
                .getCollection<User>(LOBBY_COLLECTION_NAME)
                .deleteOne(
                    Filters.eq("name", user.name)
                )
        }
    }
}
