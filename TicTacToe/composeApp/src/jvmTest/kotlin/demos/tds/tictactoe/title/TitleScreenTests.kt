package demos.tds.tictactoe.title

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import demos.tds.tictactoe.AppScreen
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.common.ui.TopBarSettingsButtonTag
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class TitleScreenTests {

    @Test
    fun `contains the associated test tag`() = runComposeUiTest {

        setContent { TitleScreen() }

        onNodeWithTag(testTag = AppScreen.Title.name)
            .assertExists()
    }

    @Test
    fun `clicking on start button should call onStart`() = runComposeUiTest {

        var onStartCalled = false
        setContent {
            TitleScreen(
                localUser = User(name = "Palinho"),
                onStart = { onStartCalled = true }
            )
        }

        onNodeWithTag(testTag = TitleScreenStartButtonTag).performClick()
        assert(onStartCalled)
    }

    @Test
    fun `clicking on settings button should call onSettings`() = runComposeUiTest {

        var onSettingsCalled = false
        setContent { TitleScreen(onSettingsSelected = { onSettingsCalled = true }) }

        onNodeWithTag(testTag = TopBarSettingsButtonTag).performClick()
        assert(onSettingsCalled)
    }

    @Test
    fun `start button is disabled when there is no user information`() = runComposeUiTest {

        setContent { TitleScreen() }

        onNodeWithTag(testTag = TitleScreenStartButtonTag)
            .assertIsNotEnabled()
    }
}