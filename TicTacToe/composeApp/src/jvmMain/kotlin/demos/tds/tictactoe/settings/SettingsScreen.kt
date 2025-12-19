package demos.tds.tictactoe.settings

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import demos.tds.tictactoe.AppScreen
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.common.ui.ScreenScaffold
import demos.tds.tictactoe.common.ui.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import tictactoe.composeapp.generated.resources.Res
import tictactoe.composeapp.generated.resources.settings_screen_title


/**
 * The application settings screen.
 *
 * @param user the current user, or null if not yet set.
 * @param onLeave the callback invoked when the user leaves the settings screen.
 */
@Composable
fun SettingsScreen(
    user: User?,
    onLeave: (User?) -> Unit,
) {
    var user by remember { mutableStateOf(value = user) }

    ScreenScaffold(
        title = stringResource(Res.string.settings_screen_title),
        onLeave = { onLeave(user) },
        modifier = Modifier
            .testTag(tag = AppScreen.Settings.name)
            .background(color = Color.White.copy(alpha = 0.9f))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(all = 64.dp)
        ) {
            LocalUserNameInput(user, onUserChanged = { user = it })
            Spacer(modifier = Modifier.weight(1f))
            HorizontalDivider(
                color = MaterialTheme.colorScheme.outline,
            )
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() = AppTheme {
    SettingsScreen(
        user = User(name = "Palinho"),
        onLeave = {}
    )
}
