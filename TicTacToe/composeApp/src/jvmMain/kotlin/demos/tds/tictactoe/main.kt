package demos.tds.tictactoe

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.mongodb.kotlin.client.MongoClient

/**
 * Demo application for the TDS course.
 *
 * - Icons were downloaded from [here](https://fonts.google.com/icons)
 * - The theme was built with [this](https://material-foundation.github.io/material-theme-builder/)
 */
fun main() {
    val connectionString = System.getenv("CONN_STR") ?: throw IllegalArgumentException("Environment variable CONN_STR not set.")
    MongoClient.create(connectionString).use { dbClient ->
        application {
            Window(
                onCloseRequest = ::exitApplication,
                title = "",
            ) {
                App(dbClient = dbClient)
            }
        }
    }
}
