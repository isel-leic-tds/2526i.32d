package demos.tds.puzzle.slidingpuzzlecompose.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import demos.tds.puzzle.slidingpuzzlecompose.domain.Coordinate
import demos.tds.puzzle.slidingpuzzlecompose.domain.Piece

const val CellViewTestTag = "CellViewTestTag"

fun getTestTagForCellView(piece: Piece?): String =
    if (piece == null) CellViewTestTag
    else "${CellViewTestTag}_${piece.face}"

/**
 * Represents a composable UI element for displaying a single cell in the sliding puzzle grid.
 *
 * @param piece The puzzle piece to be displayed in this cell. Can be null if the cell is empty.
 * @param coordinate The coordinate of this cell in the sliding puzzle grid.
 * @param onClick A callback invoked when the cell is clicked. Receives the piece (if any) and the cell's coordinate.
 * @param modifier The modifier for this composable. Defaults to an empty modifier.
 */
@Composable
fun CellView(
    piece: Piece?,
    coordinate: Coordinate,
    onClick: (Piece?, Coordinate) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size = 140.dp)
            .border(width = 1.dp, color = Color.Black)
            .clickable(enabled = piece != null) { onClick(piece, coordinate) }
            .testTag(tag = getTestTagForCellView(piece))
    ) {
        piece?.let {
            Text(
                fontSize = 24.sp,
                text = piece.face
            )
        }
    }
}

/**
 * A preview composable function for displaying a static cell view of the sliding puzzle.
 * This preview sets up a `CellView` with predefined parameters to visually inspect its appearance
 * and behavior in isolation within the Compose Preview tool.
 *
 * Key features:
 * - Uses a sample `Piece` with a face value of "1".
 * - Positions the cell at coordinate (0, 0).
 * - Includes a placeholder for the click callback, which ignores the interaction during preview.
 */
@Preview
@Composable
private fun CellViewPreview() {
    CellView(
        piece = Piece(face = "1"),
        coordinate = Coordinate(0, 0),
        onClick = { _, _ -> }
    )
}
