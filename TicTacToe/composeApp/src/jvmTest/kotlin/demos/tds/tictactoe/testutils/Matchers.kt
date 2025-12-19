package demos.tds.tictactoe.testutils

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher

/**
 * Matcher that succeeds when the element is not editable, that is, its semantic node does nor include the
 * [SemanticsProperties.IsEditable] property.
 */
fun isNotEditable() = SemanticsMatcher.expectValue(SemanticsProperties.IsEditable, false)
