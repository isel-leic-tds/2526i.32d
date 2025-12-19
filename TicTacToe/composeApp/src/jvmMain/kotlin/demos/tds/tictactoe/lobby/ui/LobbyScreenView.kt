package demos.tds.tictactoe.lobby.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import demos.tds.tictactoe.AppScreen
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.common.ui.ScreenScaffold
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import tictactoe.composeapp.generated.resources.Res
import tictactoe.composeapp.generated.resources.lobby_screen_title


/**
 * Displays the lobby.
 * @param usersInLobby the list of users in the lobby.
 * @param onUserSelected the callback invoked when a user is selected.
 * @param onLeave the callback invoked when the leave button is clicked.
 */
@Composable
fun LobbyScreenView(
    usersInLobby: List<User>,
    onUserSelected: (User) -> Unit = { },
    onLeave: () -> Unit = { }
) {
    ScreenScaffold(
        title = stringResource(Res.string.lobby_screen_title),
        onLeave = onLeave,
        modifier = Modifier.testTag(tag = AppScreen.Lobby.name)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(all = 16.dp)
        ) {

            items(items = usersInLobby, key = { it.name }) { user ->
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(CardDefaults.outlinedShape)
                        .clickable { onUserSelected(user) }
                ) {
                    Text(
                        text = user.name,
                        modifier = Modifier
                            .testTag(tag = user.name)
                            .padding(all = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun LobbyScreenPreview() =
    LobbyScreenView(
        usersInLobby = listOf(
            User(name = "Captain America"),
            User(name = "Iron Man"),
            User(name = "Thor"),
            User(name = "Hulk"),
            User(name = "Black Widow"),
            User(name = "Spider-Man"),
        ),
    )
