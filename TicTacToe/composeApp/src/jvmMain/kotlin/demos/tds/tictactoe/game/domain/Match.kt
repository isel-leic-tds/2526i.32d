package demos.tds.tictactoe.game.domain

import demos.tds.tictactoe.common.domain.Challenge
import demos.tds.tictactoe.common.domain.User

/**
 * The set of operations required to play a match across devices.
 */
interface Match {

    /**
     * Starts a new match.
     * @param localPlayer the local player
     * @param challenge the challenge
     * @return the game
     * @throws IllegalStateException if the match is already in progress.
     */
    suspend fun startMatch(localPlayer: User, challenge: Challenge): Game

    /**
     * Makes a move at the given coordinates.
     * @param at the coordinates where the move is to be made
     * @throws IllegalStateException if a game is not in progress, or the move is illegal, either because it's not the
     * local player's turn or the position is not free.
     */
    suspend fun makeMove(at: Coordinate): Game

    /**
     * Forfeits the match. The local player loses.
     * @return The resulting game.
     */
    suspend fun forfeit(): Game

    /**
     * Ends the match, cleaning up resources if necessary.
     */
    suspend fun endMatch()
}