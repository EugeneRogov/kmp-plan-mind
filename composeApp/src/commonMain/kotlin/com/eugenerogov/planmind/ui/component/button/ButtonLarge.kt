package com.eugenerogov.planmind.ui.component.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eugenerogov.planmind.ui.component.typography.LabelLarge
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ButtonLarge(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    visible: Boolean = true,
    enabled: Boolean = true
) {
    AnimatedVisibility(
        visible = visible
    ) {
        Button(
            modifier =
            modifier
                .size(height = 58.dp, width = 358.dp),
            onClick = { onClick.invoke() },
            content = {
                LabelLarge(
                    text = text,
                    color = LocalColorsPalette.current.onPrimary
                )
            },
            enabled = enabled,
            colors =
            ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = LocalColorsPalette.current.primary,
                disabledContainerColor = LocalColorsPalette.current.outlineVariant
            )

        )
    }
}

@Preview()
@Composable
fun ButtonLargePreview() {
    ButtonLarge(
        onClick = {},
        text = "Get verification code"
//        image = painterResource(R.drawable.button_onoff_indicator_on)
    )
}
