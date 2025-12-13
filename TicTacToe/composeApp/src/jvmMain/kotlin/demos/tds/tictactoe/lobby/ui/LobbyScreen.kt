package demos.tds.tictactoe.lobby.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import demos.tds.tictactoe.common.domain.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyScreen(
    viewModel: LobbyScreenViewModel,
    onUserSelected: (User) -> Unit = { },
    onLeave: () -> Unit = { }
) {
    // TODO: Explain the DisposableEffect
    DisposableEffect(key1 = viewModel) {
        viewModel.startMonitoringLobby()
        onDispose { viewModel.stopMonitoringLobby() }
    }

    val usersInLobby = when(val state = viewModel.screenState) {
        is LobbyScreenState.Loading -> state.usersInLobby
        is LobbyScreenState.Displaying -> state.usersInLobby
        is LobbyScreenState.Error -> emptyList()
        is LobbyScreenState.Initial -> emptyList()
    }

    LobbyScreenView(usersInLobby = usersInLobby, onUserSelected = onUserSelected, onLeave = onLeave)
}

