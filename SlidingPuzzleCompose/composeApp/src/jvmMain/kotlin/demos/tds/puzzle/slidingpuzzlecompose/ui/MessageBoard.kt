package demos.tds.puzzle.slidingpuzzlecompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MessageBoard(message1: String, message2: String) {
    var showFirstMessage by remember { mutableStateOf(true) }
    MessageBoardStateless(
        message = if (showFirstMessage) message1 else message2,
        onToggle = { showFirstMessage = !showFirstMessage }
    )
}

@Composable
fun MessageBoardStateless(message: String, onToggle: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(all = 24.dp)
                    .background(color = Color.Red)
        )

        Button(onClick = onToggle) {
            Text(text = "Click me!", color = Color.White)
        }

    }
}

@Preview
@Composable
fun MessageBoardPreview() {
    MessageBoard("SLB GLORIOSO SLB!", "Eu AMO o BENFICA")
}