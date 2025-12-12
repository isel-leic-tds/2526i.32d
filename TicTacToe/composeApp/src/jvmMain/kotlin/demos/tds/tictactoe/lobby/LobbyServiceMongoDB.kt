package demos.tds.tictactoe.lobby

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.MongoClient
import com.mongodb.kotlin.client.MongoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val DB_NAME = "tictactoe"
private const val LOBBY_COLLECTION_NAME = "lobby"

/**
 * Implementation of [LobbyService] that uses MongoDB as a backing store.
 *
 * DESIGN NOTE: [MongoClient] is [java.io.Closeable]. If we were to instantiate it here, we would have to make this
 * class [java.io.Closeable] too. By injecting it, we push the responsibility of closing the client to the caller.
 */
class LobbyServiceMongoDB(private val dbClient: MongoClient) : LobbyService {

    private val database: MongoDatabase by lazy {
        dbClient.getDatabase(DB_NAME)
    }
    private fun getAllUsers(): List<User> = database
        .getCollection<User>(LOBBY_COLLECTION_NAME)
        .find()
        .toList()

    override suspend fun getUsers(): List<User> =
        withContext(Dispatchers.IO) {
            getAllUsers()
        }

    override suspend fun enterLobby(user: User): List<User> =
        withContext(Dispatchers.IO) {
            database
                .getCollection<User>(LOBBY_COLLECTION_NAME)
                .insertOne(user)
            getAllUsers()
        }

    override suspend fun leaveLobby(user: User) {
        withContext(Dispatchers.IO) {
            database
                .getCollection<User>(LOBBY_COLLECTION_NAME)
                .deleteOne(
                    Filters.eq("name", user.name)
                )
        }
    }
}
