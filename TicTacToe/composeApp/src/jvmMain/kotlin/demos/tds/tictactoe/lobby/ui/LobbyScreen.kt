package demos.tds.tictactoe.lobby.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import demos.tds.tictactoe.common.domain.Challenge

/**
 * The lobby screen.
 *
 * @param viewModel the lobby screen view model.
 * @param onUserChallenged callback invoked when a user is selected to be challenged.
 * @param onLeave callback invoked when the leave button is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyScreen(
    viewModel: LobbyScreenViewModel,
    onUserChallenged: (Challenge) -> Unit = { },
    onLeave: () -> Unit = { }
) {

    DisposableEffect(key1 = viewModel) {
        viewModel.startMonitoringLobby()
        onDispose {
            viewModel.stopMonitoringLobby()
        }
    }

    val usersInLobby = when(val state = viewModel.screenState.value) {
        is LobbyScreenState.Loading -> state.usersInLobby
        is LobbyScreenState.Displaying -> state.usersInLobby
        is LobbyScreenState.Error -> emptyList()
        is LobbyScreenState.Initial -> emptyList()
    }

    LobbyScreenView(
        usersInLobby = usersInLobby,
        onUserSelected = {
            onUserChallenged(Challenge(challenger = viewModel.localUser, challenged = it))
        },
        onLeave = onLeave
    )
}

