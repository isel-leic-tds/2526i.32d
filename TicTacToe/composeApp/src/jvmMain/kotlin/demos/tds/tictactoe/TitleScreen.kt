package demos.tds.tictactoe

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

const val TitleScreenStartButtonTag = "StartButtonTag"

@Composable
fun TitleScreen(onStart: () -> Unit = {}) = Column {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.testTag(tag = AppScreen.Title.name)
    ) {
        Button(
            onClick = onStart,
            modifier = Modifier.testTag(tag = TitleScreenStartButtonTag)
        ) {
            Text(text = "Start")
        }
    }
}

@Preview
@Composable
private fun TitleScreenPreview() = TitleScreen()