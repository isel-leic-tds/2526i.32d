package demos.tds.tictactoe

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class LobbyScreenTests {

    @Test
    fun contains_the_associated_test_tag() = runComposeUiTest {
        setContent { LobbyScreen(usersInLobby = listOf()) }

        onNodeWithTag(testTag = AppScreen.Lobby.name).assertExists(
            "Lobby screen should be shown"
        )
    }

    @Test
    fun view_should_render_list_of_users() = runComposeUiTest {
        val usersInLobby = listOf(
            User(name = "Palecas"),
            User(name = "Palinho"),
            User(name = "BP"),
        )

        setContent {
            LobbyScreen(usersInLobby)
        }

        usersInLobby.forEach {
            onNodeWithText(text = it.name).assertExists()
        }
    }

    @Test
    fun clicking_on_a_user_should_trigger_onUserSelected() = runComposeUiTest {
        val usersInLobby = listOf(
            User(name = "Darth Vader"),
            User(name = "Luke Skywalker"),
        )

        var selectedUser: User? = null
        setContent {
            LobbyScreen(usersInLobby, onUserSelected = { selectedUser = it })
        }

        onNodeWithText(text = usersInLobby.last().name).performClick()
        assert(selectedUser == usersInLobby.last())
    }
}