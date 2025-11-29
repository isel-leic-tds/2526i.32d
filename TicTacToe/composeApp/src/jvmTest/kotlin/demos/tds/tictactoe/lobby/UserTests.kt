package demos.tds.tictactoe.lobby

import demos.tds.tictactoe.lobby.User
import kotlin.test.Test
import kotlin.test.assertFailsWith

class UserTests {

    @Test
    fun create_user_with_blank_name_throws() {
        assertFailsWith(exceptionClass = IllegalArgumentException::class) {
            User(name = "  ")
        }
    }

    @Test
    fun create_user_with_non_blank_name_succeeds() {
        User(name = "Palecas")
    }
}