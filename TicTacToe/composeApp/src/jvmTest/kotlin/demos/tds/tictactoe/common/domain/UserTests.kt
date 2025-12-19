package demos.tds.tictactoe.common.domain

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class UserTests {

    @Test
    fun `create user with blank name throws`() {
        assertFailsWith(exceptionClass = IllegalArgumentException::class) {
            User(name = "  ")
        }
    }

    @Test
    fun `create user with non blank name succeeds`() {
        User(name = "Palecas")
    }

    @Test
    fun `isValidUsername for non blank string returns true`() {
        val sut = "Should be valid"
        assertTrue { sut.isValidUserName() }
    }

    @Test
    fun `isValidUsername for blank string returns false`() {
        val sut = " "
        assertFalse { sut.isValidUserName() }
    }

    @Test
    fun `toUserOrNull for invalid username returns null`() {
        val sut = " "
        assertNull(actual = sut.toUserOrNull())
    }

    @Test
    fun `toUserOrNull for valid username returns User`() {
        val sut = "Palinho"
        val expected = User(name = sut)
        assertEquals(expected = expected, actual = sut.toUserOrNull())
    }
}