package demos.tds.tictactoe.title

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import demos.tds.tictactoe.AppScreen
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.common.ui.ScreenScaffold
import demos.tds.tictactoe.common.ui.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import tictactoe.composeapp.generated.resources.Res
import tictactoe.composeapp.generated.resources.app_name
import tictactoe.composeapp.generated.resources.splash
import tictactoe.composeapp.generated.resources.title_screen_start_button_label

/**
 * Tag used to identify the start button in the title screen.
 */
const val TitleScreenStartButtonTag = "StartButtonTag"

/**
 * The application title screen.
 *
 * @param localUser the local user, or null if not yet set.
 * @param onStart the callback invoked when the start button is clicked.
 * @param onSettingsSelected the callback invoked when the settings button is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleScreen(
    localUser: User? = null,
    onStart: () -> Unit = { },
    onSettingsSelected: () -> Unit = { }
) =
    ScreenScaffold(
        title = stringResource(Res.string.app_name),
        onSettingsSelected = onSettingsSelected,
        modifier = Modifier
            .testTag(tag = AppScreen.Title.name)
            .background(color = Color.White.copy(alpha = 0.9f))
    ) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(Res.drawable.splash),
            contentDescription = "Splash Image",
            modifier = Modifier.requiredSize(364.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onStart,
            enabled = localUser != null,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .testTag(tag = TitleScreenStartButtonTag)
        ) {
            Text(text = stringResource(Res.string.title_screen_start_button_label))
        }
    }
}

@Preview
@Composable
private fun TitleScreenPreviewNoUser() {
    AppTheme {
        TitleScreen()
    }
}

@Preview
@Composable
private fun TitleScreenPreviewUser() {
    AppTheme {
        TitleScreen(
            localUser = User(name = "Palinho")
        )
    }
}