package demos.tds.tictactoe.lobby

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

sealed interface LobbyScreenState {
    object Initial : LobbyScreenState
    data class Loading(val usersInLobby: List<User>) : LobbyScreenState
    data class Displaying(val usersInLobby: List<User>) : LobbyScreenState
    object Error : LobbyScreenState
}

class LobbyScreenViewModel(
    private val scope: CoroutineScope = CoroutineScope(EmptyCoroutineContext),
    private val service: LobbyService
) {

    private val _screenState = mutableStateOf<LobbyScreenState>(LobbyScreenState.Initial)
    val screenState = _screenState as State<LobbyScreenState>

    fun fetchLobbies() {

        val displayedList = when (val currentState = _screenState.value) {
            is LobbyScreenState.Loading -> throw IllegalStateException("Already loading")
            is LobbyScreenState.Initial -> emptyList()
            is LobbyScreenState.Displaying -> currentState.usersInLobby
            is LobbyScreenState.Error -> emptyList()
        }

        _screenState.value = LobbyScreenState.Loading(usersInLobby = displayedList)
        scope.launch {
            val usersInLobby = service.getUsers()
            _screenState.value = LobbyScreenState.Displaying(usersInLobby = usersInLobby)
        }
    }
}