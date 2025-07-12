package com.eugenerogov.planmind.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import planmind.composeapp.generated.resources.Res
import planmind.composeapp.generated.resources.ic_launcher

@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
    showText: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App icon
        Image(
            painter = painterResource(Res.drawable.ic_launcher),
            contentDescription = "PlanMind Logo",
            modifier = Modifier
                .size(80.dp)
                .padding(bottom = if (showText) 8.dp else 0.dp)
        )

        if (showText) {
            Text(
                text = "PlanMind",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = LocalColorsPalette.current.primary
            )
        }
    }
}

@Preview
@Composable
fun AppLogoPreview() {
    AppLogo()
}