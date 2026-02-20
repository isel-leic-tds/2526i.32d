package demos.tds.tictactoe.game.domain

import kotlin.test.Test
import kotlin.test.assertEquals


class GameTests {

    @Test
    fun `making a move when on the local player's turn succeeds`() {
        val initial = Game(
            localPlayerMarker = Marker.firstToMove,
            board = Board(turn = Marker.firstToMove)
        )

        val moveCoordinate = Coordinate(row = 0, column = 0)
        val sut = initial.makeMove(at = moveCoordinate)

        assertEquals(expected = Marker.firstToMove.other, actual = sut.board.turn)
        assertEquals(expected = Marker.firstToMove, actual = sut.board[moveCoordinate])
    }

    @Test(expected = IllegalStateException::class)
    fun `making a move when not on the local player's turn throws`() {
        val sut = Game(
            localPlayerMarker = Marker.firstToMove.other,
            board = Board(turn = Marker.firstToMove)
        )

        sut.makeMove(at = Coordinate(row = 0, column = 0))
    }
}