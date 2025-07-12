package com.eugenerogov.planmind.ui.component.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import com.eugenerogov.planmind.ui.theme.LocalDim
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import planmind.composeapp.generated.resources.Res
import planmind.composeapp.generated.resources.demo_password_label
import planmind.composeapp.generated.resources.demo_password_placeholder

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    placeholder: String = label,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    focusRequester: FocusRequester = FocusRequester(),
    textColor: Color = LocalColorsPalette.current.onSurface,
    isPassword: Boolean = false
) {
    val dim = LocalDim.current
    var isPasswordVisible by remember { mutableStateOf(false) }

    val actualVisualTransformation = if (isPassword && !isPasswordVisible) {
        PasswordVisualTransformation()
    } else {
        visualTransformation
    }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(dim.inputFieldHeight)
            .focusRequester(focusRequester),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = if (enabled) LocalColorsPalette.current.onSurfaceVariant
                else LocalColorsPalette.current.onSurfaceVariant.copy(alpha = 0.6f)
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 16.sp,
                color = LocalColorsPalette.current.onSurfaceVariant.copy(alpha = 0.7f)
            )
        },
        trailingIcon = if (isPassword) {
            {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Text(
                        text = if (isPasswordVisible) "🙈" else "👁️",
                        fontSize = 16.sp
                    )
                }
            }
        } else null,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        visualTransformation = actualVisualTransformation,
        singleLine = singleLine,
        enabled = enabled,
        shape = RoundedCornerShape(dim.roundingInput),
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
fun InputFieldPreview() {
    InputField(
        value = "",
        onValueChange = {},
        label = stringResource(Res.string.demo_password_label),
        placeholder = stringResource(Res.string.demo_password_placeholder),
        isPassword = true
    )
}