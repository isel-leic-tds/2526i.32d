package demos.tds.demoshost.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MessageBoardApp() {
    MaterialTheme {
        MessageBoardSwitch("SLB GLORIOSO SLB!", "Eu AMO o BENFICA")
    }
}

@Composable
fun MessageBoardSwitch(message1: String, message2: String) {
    val showFirstMessage = remember { mutableStateOf(true) }
    MessageBoard(
        message = if (showFirstMessage.value) message1 else message2,
        onToggle = { showFirstMessage.value = !showFirstMessage.value }
    )
}

@Composable
fun MessageBoard(message: String, onToggle: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
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
fun MessageBoardSwitchPreview() {
    MessageBoardSwitch("SLB GLORIOSO SLB!", "Eu AMO o BENFICA")
}