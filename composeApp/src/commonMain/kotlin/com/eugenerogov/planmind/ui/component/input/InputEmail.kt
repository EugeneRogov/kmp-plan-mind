package com.eugenerogov.planmind.ui.component.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .focusRequester(focusRequester),
        value = text,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = inputType
        ),
        label = {
            Text(
                text = hint,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = if (enabled) LocalColorsPalette.current.onSurfaceVariant
                else LocalColorsPalette.current.onSurfaceVariant.copy(alpha = 0.6f)
            )
        },
        placeholder = {
            Text(
                text = hint,
                fontSize = 16.sp,
                color = LocalColorsPalette.current.onSurfaceVariant.copy(alpha = 0.7f)
            )
        },
        singleLine = true,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            disabledTextColor = textColor.copy(alpha = 0.6f),
            cursorColor = LocalColorsPalette.current.primary,
            focusedBorderColor = LocalColorsPalette.current.primary,
            unfocusedBorderColor = LocalColorsPalette.current.outline.copy(alpha = 0.5f),
            disabledBorderColor = LocalColorsPalette.current.outline.copy(alpha = 0.3f),
            focusedLabelColor = LocalColorsPalette.current.primary,
            unfocusedLabelColor = LocalColorsPalette.current.onSurfaceVariant,
            disabledLabelColor = LocalColorsPalette.current.onSurfaceVariant.copy(alpha = 0.6f),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = LocalColorsPalette.current.surfaceVariant.copy(alpha = 0.1f)
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
        hint = "Email"
    )
}
