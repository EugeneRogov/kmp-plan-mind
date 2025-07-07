package com.eugenerogov.planmind.ui.component.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.eugenerogov.planmind.ui.component.typography.BodySmall
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import com.eugenerogov.planmind.ui.theme.LocalDim
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InputEmail(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = LocalColorsPalette.current.onSurface,
    hint: String = "",
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Send,
    inputType: KeyboardType = KeyboardType.Email,
    enabled: Boolean = true,
    focusRequester: FocusRequester = FocusRequester()
) {
    OutlinedTextField(
        modifier =
        modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = text,
        onValueChange = {
            onValueChange.invoke(it)
        },
        keyboardOptions =
        KeyboardOptions(
            imeAction = imeAction,
            keyboardType = inputType
        ),
        label = {
            BodySmall(
                text = hint,
                color = LocalColorsPalette.current.onSurfaceVariant
            )
        },
        singleLine = true,
        enabled = enabled,
        shape = RoundedCornerShape(LocalDim.current.roundingInput),
        colors =
        OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            cursorColor = LocalColorsPalette.current.primary,
            focusedBorderColor = LocalColorsPalette.current.primary,
            unfocusedBorderColor = LocalColorsPalette.current.outline,
            focusedPlaceholderColor = LocalColorsPalette.current.primary,
            unfocusedPlaceholderColor = LocalColorsPalette.current.onSurfaceVariant
        )
    )
}

@Preview()
@Composable
fun InputEmailPreview() {
    InputEmail(
        text = "",
        onValueChange = {},
        textColor = LocalColorsPalette.current.onSurface,
        hint = "Your email address"
    )
}
