package demos.tds.tictactoe.settings

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import demos.tds.tictactoe.AppScreen
import demos.tds.tictactoe.common.ui.TopBarSettingsButtonTag
import demos.tds.tictactoe.title.TitleScreen
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class SettingsScreenTests {

    @Test
    fun `contains the associated test tag`() = runComposeUiTest {

        setContent {
            SettingsScreen(
                user = null,
                onLeave = { }
            )
        }

        onNodeWithTag(testTag = AppScreen.Settings.name)
            .assertExists()
    }

    @Test
    fun `clicking on back navigation button should call onLeave`() = runComposeUiTest {

        var onLeaveCalled = false
        setContent { TitleScreen(onSettingsSelected = { onLeaveCalled = true }) }

        onNodeWithTag(testTag = TopBarSettingsButtonTag).performClick()
        assertTrue(onLeaveCalled)
    }
}