package demos.tds.tictactoe.game.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import demos.tds.tictactoe.game.domain.Marker
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class TileViewTests {

    @Test
    fun `Tile contains image of marker cross`() = runComposeUiTest {

        // Arrange & Act
        val move = Marker.CROSS
        setContent {
            TileView(
                move = move,
                enabled = true,
                onSelected = { },
            )
        }

        // Assert
        onNodeWithTag(testTag = TileViewImageTag, useUnmergedTree = true)
            .assertContentDescriptionContains(move.name)

    }

    @Test
    fun `Tile contains image of marker circle`() = runComposeUiTest {

        // Arrange & Act
        val move = Marker.CIRCLE
        setContent {
            TileView(
                move = move,
                enabled = true,
                onSelected = { },
            )
        }

        // Assert
        onNodeWithTag(testTag = TileViewImageTag, useUnmergedTree = true)
            .assertContentDescriptionContains(move.name)

    }

    @Test
    fun `onSelected is called when an empty enabled tile is clicked`() = runComposeUiTest {

        // Arrange
        var onSelectedCalled = false
        setContent {
            TileView(
                move = null,
                enabled = true,
                onSelected = { onSelectedCalled = true },
            )
        }

        // Act
        onNodeWithTag(testTag = TileViewTag).performClick()

        // Assert
        assertTrue(actual = onSelectedCalled)
    }

    @Test
    fun `onSelected is not called when a filled enabled tile is clicked`() = runComposeUiTest {

        // Arrange
        var onSelectedCalled = false
        setContent {
            TileView(
                move = Marker.CROSS,
                enabled = true,
                onSelected = { onSelectedCalled = true },
            )
        }

        // Act
        onNodeWithTag(testTag = TileViewTag).performClick()

        // Assert
        assertFalse(actual = onSelectedCalled)
    }

    @Test
    fun `onSelected is not called when a disabled tile is clicked`() = runComposeUiTest {

        // Arrange
        var onSelectedCalled = false
        setContent {
            TileView(
                move = null,
                enabled = false,
                onSelected = { onSelectedCalled = true },
            )
        }

        // Act
        onNodeWithTag(testTag = TileViewTag).performClick()

        // Assert
        assertFalse(actual = onSelectedCalled)
    }
}