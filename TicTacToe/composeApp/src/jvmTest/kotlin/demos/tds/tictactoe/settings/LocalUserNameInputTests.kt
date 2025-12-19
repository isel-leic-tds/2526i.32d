package demos.tds.tictactoe.settings

import androidx.compose.ui.test.*
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.testutils.isNotEditable
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class LocalUserNameInputTests {

    @Test
    fun `initially input is in edit mode if user is null`() = runComposeUiTest {
        setContent {
            LocalUserNameInput()
        }

        onNodeWithTag(testTag = LocalUserNameInputTag)
            .assert(matcher = isEditable())
    }

    @Test
    fun `initially input is not edit in mode if a user is passed in`() = runComposeUiTest {
        setContent {
            LocalUserNameInput(user = User(name = "Test User"))
        }

        onNodeWithTag(testTag = LocalUserNameInputTag)
            .assert(matcher = isNotEditable())
    }

    @Test
    fun `clicking on trailing edit icon input should toggle to edit mode`() = runComposeUiTest {

        setContent {
            LocalUserNameInput(
                user = User(name = "Test User")
            )
        }

        onNodeWithTag(testTag = LocalUserNameInputTag)
            .assert(matcher = isNotEditable())

        onNodeWithTag(testTag = EditModeToggleButtonTag).performClick()
        onNodeWithTag(testTag = LocalUserNameInputTag)
            .assert(matcher = isEditable())
    }
}