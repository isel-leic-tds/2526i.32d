package demos.tds.tictactoe.lobby.ui

import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.lobby.domain.FakeLobby
import demos.tds.tictactoe.utils.waitUntil
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertIs

class LobbyScreenViewModelTests {

    private val testUser = User("testUser")
    private val fakeService = FakeLobby()

    @Test
    fun `the view model starts in the Initial state`() = runTest {

        // Arrange & Act
        val sut = LobbyScreenViewModel(
            localUser = testUser,
            scope = this,
            fakeService
        )

        // Assert
        assertIs<LobbyScreenState.Initial>(value = sut.screenState.value)
    }

    @Test
    fun `startMonitoringLobby transitions to the Loading state`() = runTest {

        // Arrange
        val sut = LobbyScreenViewModel(
            localUser = testUser,
            scope = this,
            fakeService
        )

        // Act
        sut.startMonitoringLobby()

        // Assert
        val transitionedTo = waitUntil(sut.screenState) { it is LobbyScreenState.Loading }
        assertIs<LobbyScreenState.Loading>(value = transitionedTo)

        // Cleanup
        sut.stopMonitoringLobby()
    }

    @Test
    fun `startMonitoringLobby reaches the Displaying state`() = runTest {

        // Arrange
        val sut = LobbyScreenViewModel(
            localUser = testUser,
            scope = this,
            fakeService
        )

        // Act
        sut.startMonitoringLobby()

        // Assert
        val reachedState = waitUntil(sut.screenState) { it is LobbyScreenState.Displaying }
        assertIs<LobbyScreenState.Displaying>(value = reachedState)

        // Cleanup
        sut.stopMonitoringLobby()
    }
}