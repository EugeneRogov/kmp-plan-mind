package com.eugenerogov.planmind.typography

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.eugenerogov.planmind.theme.LocalColorsPalette
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TitleMedium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = LocalColorsPalette.current.onBackground,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
//        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontWeight = W500,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = TextUnit(0.16F, TextUnitType.Sp)
    )
}

@Preview()
@Composable
fun TitleMediumPreview() {
    TitleMedium(
        text = "TitleMedium"
    )
}
