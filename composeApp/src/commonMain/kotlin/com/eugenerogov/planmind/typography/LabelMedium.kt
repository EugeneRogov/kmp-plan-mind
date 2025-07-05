package com.eugenerogov.planmind.typography

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.eugenerogov.planmind.theme.LocalColorsPalette
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LabelMedium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = LocalColorsPalette.current.onPrimary,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = 1
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
//        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontWeight = W500,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = TextUnit(0.04F, TextUnitType.Sp)
    )
}

@Preview()
@Composable
fun LabelMediumPreview() {
    LabelMedium(
        text = "LabelMedium"
    )
}
