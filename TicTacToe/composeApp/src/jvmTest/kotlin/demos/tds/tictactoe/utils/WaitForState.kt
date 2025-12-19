@file:Suppress("Unused")
package demos.tds.tictactoe.utils

import androidx.compose.runtime.State
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope


/**
 * Utility function to be used in tests to wait until a certain state is reached.
 * @param source The flow to listen to
 * @param matcher The predicate used to check if the state has been reached
 * @return The state that was reached
 * @throws TimeoutCancellationException If the state was not reached within the given time
 */
suspend fun <T> TestScope.waitUntil(source: Flow<T>, matcher: (T) -> Boolean): T {

    val sync = Channel<T>(capacity = Channel.CONFLATED)

    backgroundScope.launch {
        source.collect {
            if (matcher(it))
                sync.send(element = it)
        }
    }

    return try {
        val result = sync.receive()
        result
    }
    finally {
        sync.close()
    }
}

/**
 * Utility function to be used in tests to wait until a certain state is reached.
 * @param source The state to listen to
 * @param matcher The predicate used to check if the state has been reached
 */
suspend fun <T> TestScope.waitUntil(source: State<T>, matcher: (T) -> Boolean): T =
    waitUntil(source = snapshotFlow { source.value }, matcher)


