package demos.tds.demoshost.counter

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceEvenly
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun TallyCounterApp() {
    var counter by remember { mutableStateOf(TallyCounter(10)) }
    TallyCounterView(
        counter,
        onIncrement = { counter = counter.increment() },
        onDecrement = { counter = counter.decrement() }
    )
}

@Composable
fun TallyCounterView(
    counter: TallyCounter,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            fontSize = 46.sp,
            text = counter.currentValue.toString()
        )
        Row(
            horizontalArrangement = SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = onDecrement) {
                Text(
                    fontSize = 24.sp,
                    text = "-"
                )
            }
            Button(onClick = onIncrement) {
                Text(
                    fontSize = 24.sp,
                    text = "+"
                )
            }
        }
    }
}

@Preview
@Composable
fun TallyCounterPreview() {
    TallyCounterView(counter = TallyCounter(10), onDecrement = { }, onIncrement = { })
}