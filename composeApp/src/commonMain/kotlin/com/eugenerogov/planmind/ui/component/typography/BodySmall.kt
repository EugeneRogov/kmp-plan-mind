package com.eugenerogov.planmind.ui.component.typography

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BodySmall(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = LocalColorsPalette.current.onBackground,
    textAlign: TextAlign = TextAlign.Left,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
//        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontWeight = W400,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = TextUnit(0.24F, TextUnitType.Sp)
    )
}

@Preview()
@Composable
fun BodySmallPreview() {
    BodySmall(
        text = "BodySmall"
    )
}
