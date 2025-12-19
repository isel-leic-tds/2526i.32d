package demos.tds.tictactoe.lobby

import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.lobby.ui.LobbyScreenState
import demos.tds.tictactoe.lobby.ui.LobbyScreenViewModel
import demos.tds.tictactoe.utils.waitUntil
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertIs

class LobbyScreenViewModelTests {

    private val testUser = User("testUser")
    private val fakeService = FakeLobbyService()

    @Test
    fun `the view model starts in the Initial state`() = runTest {
        val sut = LobbyScreenViewModel(
            localUser = testUser,
            scope = this,
            fakeService
        )

        assertIs<LobbyScreenState.Initial>(value = sut.screenState.value)
    }

    @Test
    fun `startMonitoringViewModel transitions to the Loading state`() = runTest {
        val sut = LobbyScreenViewModel(
            localUser = testUser,
            scope = this,
            fakeService
        )

        sut.startMonitoringLobby()

        val transitionedTo = waitUntil(sut.screenState) { it is LobbyScreenState.Loading }
        assertIs<LobbyScreenState.Loading>(value = transitionedTo)

        sut.stopMonitoringLobby()
    }

    @Test
    fun `startMonitoringViewModel reaches the Displaying state`() = runTest {
        val sut = LobbyScreenViewModel(
            localUser = testUser,
            scope = this,
            fakeService
        )

        sut.startMonitoringLobby()

        val reachedState = waitUntil(sut.screenState) { it is LobbyScreenState.Displaying }
        assertIs<LobbyScreenState.Displaying>(value = reachedState)

        sut.stopMonitoringLobby()
    }
}