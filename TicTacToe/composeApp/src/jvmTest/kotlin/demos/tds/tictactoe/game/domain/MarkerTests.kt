package demos.tds.tictactoe.game.domain

import kotlin.test.Test

class MarkerTests {

    @Test
    fun `first to play is CIRCLE`() {
        val sut = Marker.firstToMove
        assert(sut == Marker.CIRCLE) {
            "First player to move always uses the circle marker"
        }
    }

    @Test
    fun `other player is CROSS`() {
        val sut = Marker.firstToMove.other
        assert(sut == Marker.CROSS) {
            "From the first to move, the other player marker should be the cross"
        }
    }
}