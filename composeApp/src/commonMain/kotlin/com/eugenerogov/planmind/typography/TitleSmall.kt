package com.eugenerogov.planmind.typography

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.eugenerogov.planmind.theme.LocalColorsPalette
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TitleSmall(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = LocalColorsPalette.current.onBackground,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1,
//    fontFamily: FontFamily = FontFamily(Font(R.font.roboto_regular))
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
//        fontFamily = fontFamily,
        fontWeight = W500,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
}

@Preview()
@Composable
fun TitleSmallPreview() {
    TitleSmall(
        text = "TitleSmall"
    )
}
