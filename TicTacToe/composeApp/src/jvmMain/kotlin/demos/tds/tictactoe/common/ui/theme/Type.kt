package demos.tds.tictactoe.common.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import tictactoe.composeapp.generated.resources.Res
import tictactoe.composeapp.generated.resources.rusty_hooks

@Composable
fun AppTypography(): Typography {
    val appFont = FontFamily(
        Font(Res.font.rusty_hooks, FontWeight.Normal),
    )

    return with(MaterialTheme.typography) {
        copy(
            displayLarge = displayLarge.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 48.sp),
            displayMedium = displayMedium.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 34.sp),
            displaySmall = displaySmall.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 24.sp),
            headlineLarge = headlineLarge.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 24.sp),
            headlineMedium = headlineMedium.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 20.sp),
            headlineSmall = headlineSmall.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 16.sp),
            titleLarge = titleLarge.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 16.sp),
            titleMedium = titleMedium.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 14.sp),
            titleSmall = titleSmall.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 12.sp),
            labelLarge = labelLarge.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 14.sp),
            labelMedium = labelMedium.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 12.sp),
            labelSmall = labelSmall.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 12.sp),
            bodyLarge = bodyLarge.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 16.sp),
            bodyMedium = bodyMedium.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 14.sp),
            bodySmall = bodySmall.copy(fontFamily = appFont, fontWeight = FontWeight.Normal, fontSize = 12.sp),
        )
    }
}