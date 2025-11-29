package demos.tds.tictactoe.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.jetbrains.compose.resources.painterResource
import tictactoe.composeapp.generated.resources.Res
import tictactoe.composeapp.generated.resources.arrow_back
import tictactoe.composeapp.generated.resources.exit

const val LeaveButtonTag = "LeaveButtonTag"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenScaffold(
    title: String = "",
    onLeave: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    MaterialTheme {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            CenterAlignedTopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    if (onLeave != null) {
                        IconButton(
                            onClick = onLeave,
                            modifier = Modifier.testTag(tag = LeaveButtonTag)
                        ) {
                            Icon(
                                painter = painterResource(resource = Res.drawable.arrow_back),
                                contentDescription = "Back"
                            )
                        }
                    }
                },
            )
            content()
        }
    }
}

@Preview
@Composable
private fun ScreenScaffoldPreview() = ScreenScaffold(title = "")