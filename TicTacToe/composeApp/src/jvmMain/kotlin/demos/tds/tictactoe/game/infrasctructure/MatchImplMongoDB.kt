package demos.tds.tictactoe.game.infrasctructure

import com.mongodb.kotlin.client.MongoClient
import com.mongodb.kotlin.client.MongoDatabase
import demos.tds.tictactoe.common.domain.Challenge
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.game.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private const val ONGOING_MATCHES_COLLECTION_NAME = "ongoing"

/**
 * A MongoDB based implementation of the [Match] service.
 * IMPLEMENTATION NOTE:
 * For simplicity, we do not deal with concurrency hazards. But in a real application,
 * this should be taken into account. This is a subject of the Concurrent Programming course.
 */
class MatchImplMongoDB(private val dbClient: MongoClient, private val dbName: String) : Match {

    private val logger: Logger = LoggerFactory.getLogger("LobbyServiceMongoDB")

    private val database: MongoDatabase by lazy {
        dbClient.getDatabase(databaseName = dbName)
    }

    private var ongoingMatch: Pair<Game, Challenge>? = null

    override suspend fun startMatch(localPlayer: User, challenge: Challenge): Game {

        if (ongoingMatch != null)
            throw IllegalStateException("There's already an ongoing match")

        val game = Game(localPlayerMarker = getLocalPlayerMarker(localPlayer, challenge))
            .also { ongoingMatch = Pair(it, challenge) }

        return withContext(Dispatchers.IO) {
            database
                .getCollection<MatchDocument>(ONGOING_MATCHES_COLLECTION_NAME)
                .insertOne(game.toMatchDocument(challenge))
            game
        }
    }


    override suspend fun makeMove(at: Coordinate): Game {
        TODO("Not yet implemented")
    }

    override suspend fun forfeit(): Game {
        TODO("Not yet implemented")
    }

    override suspend fun endMatch() {
        TODO("Not yet implemented")
    }
}

/**
 * Names of the fields used in the document representations.
 */
const val TURN_FIELD = "turn"
const val BOARD_FIELD = "board"

/**
 * [Board] extension function used to convert an instance to a map of key-value
 * pairs containing the object's properties
 */
fun Board.toDocumentContent() = mapOf(
    TURN_FIELD to turn.toSymbol(),
    BOARD_FIELD to toMovesList().joinToString(separator = "") {
        when (it) {
            Marker.CROSS -> "X"
            Marker.CIRCLE -> "O"
            null -> "-"
        }
    }
)

fun Marker.toSymbol() = when (this) {
    Marker.CROSS -> "X"
    Marker.CIRCLE -> "O"
}

fun Game.toMatchDocument(challenge: Challenge) = MatchDocument(
    challengeInfo = challenge.toMatchDocumentContent(),
    localPlayerMarker = localPlayerMarker.toSymbol(),
    forfeitedBy = forfeitedBy?.toSymbol(),
    board = board.toDocumentContent()
)

data class MatchDocument(
    val challengeInfo: Map<String, String>,
    val localPlayerMarker: String,
    val forfeitedBy: String?,
    val board: Map<String, String>
)

fun Challenge.toMatchDocumentContent(): Map<String, String> = mapOf(
    "challenger" to challenger.name,
    "challenged" to challenged.name
)