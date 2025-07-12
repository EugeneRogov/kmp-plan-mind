package com.eugenerogov.planmind.ui.component.typography

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import planmind.composeapp.generated.resources.Res
import planmind.composeapp.generated.resources.title_large_preview

@Composable
fun TitleLarge(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = LocalColorsPalette.current.onBackground,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip,
    fontWeight: FontWeight = FontWeight.W400
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = 22.sp,
        fontWeight = fontWeight,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow,
        lineHeight = 28.sp
    )
}

@Preview()
@Composable
fun TitleLargePreview() {
    TitleLarge(
        text = stringResource(Res.string.title_large_preview)
    )
}
