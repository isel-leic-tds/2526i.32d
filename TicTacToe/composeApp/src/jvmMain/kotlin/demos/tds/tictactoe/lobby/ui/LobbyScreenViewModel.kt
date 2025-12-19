package demos.tds.tictactoe.lobby.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.Snapshot
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.lobby.LobbyService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * The possible stats of the lobby screen.
 */
sealed interface LobbyScreenState {
    object Initial : LobbyScreenState
    data class Loading(val usersInLobby: List<User>) : LobbyScreenState
    data class Displaying(val usersInLobby: List<User>) : LobbyScreenState
    object Error : LobbyScreenState
}

/**
 * The view model for the lobby screen.
 *
 * @param localUser the user who is currently using the app.
 * @param scope the coroutine scope to be used.
 * @param service the lobby service that will be used to access the lobby.
 */
class LobbyScreenViewModel(
    val localUser: User,
    private val scope: CoroutineScope,
    private val service: LobbyService
) {

    private val logger: Logger = LoggerFactory.getLogger("LobbyScreenViewModel")

    // IMPLEMENTATION NOTE: I am using snapshot updates to ensure that state observers are notified, regardless of
    // being inside a @Composable function or not. This simplifies the view model tests for this view model design.
    // You are not expected to use this approach in this course. Instead, just write to the state holder value directly,
    // like this: _screenState.value = state
    private fun updateState(state: LobbyScreenState) {
        Snapshot.withMutableSnapshot { _screenState.value = state }
    }

    /**
     * The screen state holder, as used by the viewmodel implementation.
     */
    private val _screenState = mutableStateOf<LobbyScreenState>(LobbyScreenState.Initial)

    /**
     * The screen state to be consumed by the @Composable function that represents the screen
     */
    val screenState
        get() = _screenState as State<LobbyScreenState>

    // The job instance that is monitoring the lobby, if any.
    private var monitoringLobbyJob: Job? = null

    private suspend fun fetchUsersInLobby() {

        val displayedList = when (val currentState = _screenState.value) {
            is LobbyScreenState.Loading -> throw IllegalStateException("Already loading")
            is LobbyScreenState.Initial -> emptyList()
            is LobbyScreenState.Displaying -> currentState.usersInLobby
            is LobbyScreenState.Error -> emptyList()
        }

        updateState(LobbyScreenState.Loading(usersInLobby = displayedList))
        val usersInLobby = service.getUsers()
        updateState(LobbyScreenState.Displaying(usersInLobby = usersInLobby))
    }

    /**
     * Starts monitoring the lobby.
     * @throws IllegalStateException if the monitoring is already running.
     * @return the job instance that is monitoring the lobby.
     */
    fun startMonitoringLobby(): Job {
        if (monitoringLobbyJob != null) throw IllegalStateException("Already monitoring")

        return scope.launch {
            service.enterLobby(localUser)
            while (true) {
                fetchUsersInLobby()
                delay(5000)
            }
        }.also { monitoringLobbyJob = it }
    }

    /**
     * Stops monitoring the lobby.
     */
    fun stopMonitoringLobby() {
        monitoringLobbyJob?.let {
            monitoringLobbyJob = null
            it.cancel()
            scope.launch { service.leaveLobby(localUser) }
        }
    }
}