package demos.tds.tictactoe.game.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import demos.tds.tictactoe.common.ui.theme.AppTheme
import demos.tds.tictactoe.game.domain.Marker
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import tictactoe.composeapp.generated.resources.Res
import tictactoe.composeapp.generated.resources.circle_red
import tictactoe.composeapp.generated.resources.cross_red

internal const val TileViewTag = "TileView"
internal const val TileViewImageTag = "TileViewImage"

@Composable
fun TileView(
    move: Marker?,
    enabled: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .fillMaxSize(1.0f)
        .padding(12.dp)
        .testTag(TileViewTag)
        .clickable(enabled = move == null && enabled) { onSelected() }
    ) {
        if (move != null) {
            val marker = when (move) {
                Marker.CIRCLE -> Res.drawable.circle_red
                Marker.CROSS -> Res.drawable.cross_red
            }
            Image(
                painter = painterResource(resource = marker),
                contentDescription = move.name,
                modifier = Modifier.fillMaxSize().testTag(TileViewImageTag)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TileViewCirclePreview() {
    AppTheme {
        TileView(move = Marker.CIRCLE, enabled = true, onSelected = { })
    }
}


@Preview(showBackground = true)
@Composable
private fun TileViewCrossPreview() {
    AppTheme {
        TileView(move = Marker.CROSS, enabled = true, onSelected = { })
    }
}

@Preview(showBackground = true)
@Composable
private fun TileViewEmptyPreview() {
    AppTheme {
        TileView(move = null, enabled = true, onSelected = { })
    }
}