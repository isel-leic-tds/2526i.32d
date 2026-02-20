package demos.tds.tictactoe.lobby.ui

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.*
import demos.tds.tictactoe.AppScreen
import demos.tds.tictactoe.common.domain.Challenge
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.common.ui.TopBarBackButtonTag
import demos.tds.tictactoe.lobby.domain.FakeLobby
import demos.tds.tictactoe.lobby.domain.Lobby
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class LobbyScreenTests {

    private val localTestUser = User("LocalTestUser")

    @Test
    fun `contains the associated test tag`() = runComposeUiTest {

        // Arrange & Act
        setContent {
            val scope = rememberCoroutineScope()
            LobbyScreen(
                viewModel = LobbyScreenViewModel(
                    localUser = localTestUser,
                    scope = scope,
                    service = FakeLobby()
                )
            )
        }

        // Assert
        onNodeWithTag(testTag = AppScreen.Lobby.name)
            .assertExists()
    }

    @Test
    fun `pressing the back button calls onLeave`() = runComposeUiTest {

        // Arrange
        var onLeaveCalled = false
        setContent {
            val scope = rememberCoroutineScope()
            LobbyScreen(
                viewModel = LobbyScreenViewModel(
                    scope = scope,
                    localUser = localTestUser,
                    service = FakeLobby()
                ),
                onLeave = { onLeaveCalled = true }
            )
        }

        // Act
        onNodeWithTag(testTag = TopBarBackButtonTag).performClick()

        // Assert
        assert(onLeaveCalled)
    }

    @Test
    fun `selecting a user calls onUserChallenged`() = runComposeUiTest {

        // Arrange
        val userToSelect = User("Other Test User")

        var onUserChallengedArgs: Challenge? = null
        setContent {
            val scope = rememberCoroutineScope()
            LobbyScreen(
                viewModel = LobbyScreenViewModel(
                    scope = scope,
                    localUser = localTestUser,
                    service = object : Lobby {
                        override suspend fun getUsers(): List<User> = listOf(userToSelect)
                        override suspend fun enterLobby(user: User): List<User> = emptyList()
                        override suspend fun leaveLobby(user: User) {}
                    }
                ),
                onUserChallenged = { onUserChallengedArgs = it }
            )
        }

        // Act
        onNodeWithText(text = userToSelect.name).performClick()

        // Assert
        assertEquals(
            expected = Challenge(challenger = localTestUser, challenged = userToSelect),
            actual = onUserChallengedArgs
        )
    }

    @Test
    fun `localTestUser is not shown in the list of users`() = runComposeUiTest {

        // Arrange & Act
        val localTestUser = User("LocalTestUser")
        val usersInLobby = listOf(User("Other Test User"), localTestUser)

        setContent {
            val scope = rememberCoroutineScope()
            LobbyScreen(
                viewModel = LobbyScreenViewModel(
                    scope = scope,
                    localUser = localTestUser,
                    service = object : Lobby {
                        override suspend fun getUsers(): List<User> = usersInLobby
                        override suspend fun enterLobby(user: User): List<User> = emptyList()
                        override suspend fun leaveLobby(user: User) { }
                    }
                )
            )
        }

        // Assert
        onNodeWithText(text = localTestUser.name)
            .assertDoesNotExist()
    }
}