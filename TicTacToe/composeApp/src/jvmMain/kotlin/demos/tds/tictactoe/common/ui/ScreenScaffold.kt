package demos.tds.tictactoe.common.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import demos.tds.tictactoe.common.ui.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import tictactoe.composeapp.generated.resources.Res
import tictactoe.composeapp.generated.resources.arrow_back
import tictactoe.composeapp.generated.resources.settings

const val TopBarBackButtonTag = "BackButtonTag"
const val TopBarSettingsButtonTag = "SettingsButtonTag"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenScaffold(
    title: String = "",
    onLeave: (() -> Unit)? = null,
    onSettingsSelected: (() -> Unit)? = null,
    modifier: Modifier = Modifier,

    content: @Composable () -> Unit = { }
) {
    MaterialTheme {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            val iconColors = IconButtonDefaults.iconButtonColors().copy(contentColor = MaterialTheme.colorScheme.primary)
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        color = MaterialTheme.colorScheme.primary,
                        text = title,
                    )
                },
                navigationIcon = {
                    if (onLeave != null) {
                        IconButton(
                            onClick = onLeave,
                            colors = iconColors,
                            modifier = Modifier.testTag(tag = TopBarBackButtonTag)
                        ) {
                            Icon(
                                painter = painterResource(resource = Res.drawable.arrow_back),
                                contentDescription = "Back"
                            )
                        }
                    }
                },
                actions = {
                    if (onSettingsSelected != null) {
                        IconButton(
                            onClick = onSettingsSelected,
                            colors = iconColors,
                            modifier = Modifier.testTag(tag = TopBarSettingsButtonTag)
                        ) {
                            Icon(
                                painter = painterResource(resource = Res.drawable.settings),
                                contentDescription = "Settings"
                            )
                        }
                    }
                }
            )
            content()
        }
    }
}

@Preview
@Composable
private fun ScreenScaffoldPreview() = AppTheme {
    ScreenScaffold(title = "Title", onSettingsSelected = { })
}