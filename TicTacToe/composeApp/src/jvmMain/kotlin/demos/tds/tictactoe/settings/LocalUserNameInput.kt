package demos.tds.tictactoe.settings

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.semantics
import demos.tds.tictactoe.common.domain.User
import demos.tds.tictactoe.common.domain.toUserOrNull
import demos.tds.tictactoe.common.ui.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import tictactoe.composeapp.generated.resources.Res
import tictactoe.composeapp.generated.resources.check
import tictactoe.composeapp.generated.resources.edit
import tictactoe.composeapp.generated.resources.username_input_label


const val LocalUserNameInputTag = "LocalUserNameInputTag"
const val EditModeToggleButtonTag = "EditModeToggleButtonTag"

/**
 * The input field for displaying or editing the user nickname.
 * @param user the current user, or null if not yet set.
 * @param onUserChanged the callback invoked when the user is changed.
 */
@Composable
fun LocalUserNameInput(user: User? = null, onUserChanged: (User?) -> Unit = { }) {

    var editMode by remember { mutableStateOf(value = user == null) }
    var currenNickName by remember { mutableStateOf(value = user?.name ?: "") }

    LocalUserNameInputStateless(
        editMode,
        currenNickName,
        onValueChanged = { currenNickName = it },
        onAction = {
            val wasOnEditMode = editMode
            editMode = !editMode
            if (wasOnEditMode) {
                onUserChanged(currenNickName.toUserOrNull())
            }
        }
    )
}

@Composable
private fun LocalUserNameInputStateless(
    editMode: Boolean,
    nickName: String,
    onValueChanged: (String) -> Unit = { },
    onAction: () -> Unit = { }) {

    Column {
        OutlinedTextField(
            value = nickName,
            onValueChange = onValueChanged,
            label = { Text(stringResource(Res.string.username_input_label)) },
            singleLine = true,
            enabled = editMode,
            trailingIcon = {
                Icon(
                    painter = painterResource(
                        resource = if (editMode) Res.drawable.check else Res.drawable.edit
                    ),
                    contentDescription = "Edit / Save",
                    modifier = Modifier
                        .clickable { onAction() }
                        .testTag(tag = EditModeToggleButtonTag)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag(tag = LocalUserNameInputTag)
                .semantics { if (editMode) SemanticsProperties.IsEditable else Unit }
        )
    }
}

@Preview
@Composable
private fun LocalUserNameInputWithUserPreview() = AppTheme {
    LocalUserNameInput(
        user = User(name = "Palinho")
    )
}

@Preview
@Composable
private fun LocalUserNameInputWithNoUserPreview() = AppTheme {
    LocalUserNameInput()
}
