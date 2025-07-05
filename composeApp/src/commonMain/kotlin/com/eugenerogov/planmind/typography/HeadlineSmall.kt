package com.eugenerogov.planmind.typography

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.eugenerogov.planmind.theme.LocalColorsPalette
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HeadlineSmall(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = LocalColorsPalette.current.onBackground,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
//        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontWeight = FontWeight.W400,
        fontSize = 24.sp,
        lineHeight = 32.sp
    )
}

@Preview()
@Composable
fun HeadlineSmallPreview() {
    HeadlineSmall(
        text = "HeadlineSmall"
    )
}
