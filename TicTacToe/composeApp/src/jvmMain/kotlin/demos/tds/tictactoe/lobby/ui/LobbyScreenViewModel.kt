package demos.tds.tictactoe.lobby.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.lobby.LobbyService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

sealed interface LobbyScreenState {
    object Initial : LobbyScreenState
    data class Loading(val usersInLobby: List<User>) : LobbyScreenState
    data class Displaying(val usersInLobby: List<User>) : LobbyScreenState
    object Error : LobbyScreenState
}

class LobbyScreenViewModel(
    private val localUser: User,
    private val scope: CoroutineScope = CoroutineScope(EmptyCoroutineContext),
    private val service: LobbyService
) {

    private val _screenState = mutableStateOf<LobbyScreenState>(LobbyScreenState.Initial)
    val screenState by _screenState as State<LobbyScreenState>

    private var monitoringLobbyJob: Job? = null

    private suspend fun fetchUsersInLobby() {

        val displayedList = when (val currentState = _screenState.value) {
            is LobbyScreenState.Loading -> throw IllegalStateException("Already loading")
            is LobbyScreenState.Initial -> emptyList()
            is LobbyScreenState.Displaying -> currentState.usersInLobby
            is LobbyScreenState.Error -> emptyList()
        }

        _screenState.value = LobbyScreenState.Loading(usersInLobby = displayedList)
        val usersInLobby = service.getUsers()
        _screenState.value = LobbyScreenState.Displaying(usersInLobby = usersInLobby)
    }

    fun startMonitoringLobby() {
        if (monitoringLobbyJob != null) throw IllegalStateException("Already monitoring")

        monitoringLobbyJob = scope.launch {
            try {
                service.enterLobby(localUser)
                while (true) {
                    fetchUsersInLobby()
                    delay(5000)
                }
            }
            finally {
                // TODO: Refactor to remove cleanup from here.
                // Lets give ownership of the viewmodel to the screen.
                scope.launch {
                    service.leaveLobby(localUser)
                }
            }
        }
    }

    fun stopMonitoringLobby() {
        monitoringLobbyJob?.let {
            it.cancel()
            scope.launch { service.leaveLobby(localUser) }
        }
    }

}