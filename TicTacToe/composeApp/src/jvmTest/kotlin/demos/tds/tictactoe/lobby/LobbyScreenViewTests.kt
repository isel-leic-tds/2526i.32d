package demos.tds.tictactoe.lobby

import androidx.compose.ui.test.*
import demos.tds.tictactoe.AppScreen
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.common.ui.TopBarBackButtonTag
import demos.tds.tictactoe.lobby.ui.LobbyScreenView
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class LobbyScreenViewTests {

    @Test
    fun contains_the_associated_test_tag() = runComposeUiTest {
        setContent {
            LobbyScreenView(usersInLobby = emptyList())
        }

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
            LobbyScreenView(usersInLobby)
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
            LobbyScreenView(usersInLobby, onUserSelected = { selectedUser = it })
        }

        onNodeWithText(text = usersInLobby.last().name).performClick()
        assert(selectedUser == usersInLobby.last())
    }

    @Test
    fun contains_the_leave_button() = runComposeUiTest {
        setContent { LobbyScreenView(usersInLobby = listOf()) }
        onNodeWithTag(testTag = TopBarBackButtonTag).assertExists()
    }
}