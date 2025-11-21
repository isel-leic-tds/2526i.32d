package demos.tds.tictactoe

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
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Displays a list of users in the lobby.
 */
@Composable
fun LobbyScreen(usersInLobby: List<User>, onUserSelected: (User) -> Unit = { }) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(all = 16.dp).testTag(tag = AppScreen.Lobby.name)
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

@Preview
@Composable
private fun LobbyScreenPreview() = LobbyScreen(
    usersInLobby = listOf(
        User(name = "Palecas"),
        User(name = "Darth Vader"),
        User(name = "Palinho"),
        User(name = "BP"),
        User(name = "Jabba the Hutt"),
        User(name = "Han Solo"),
        User(name = "Leia Organa"),
        User(name = "C3-PO"),
        User(name = "R2-D2"),
        User(name = "Luke Skywalker"),
        User(name = "Obi-Wan Kenobi"),
    )
)