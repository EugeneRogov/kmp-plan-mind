package com.eugenerogov.planmind.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import com.eugenerogov.planmind.ui.theme.PlanMindTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ThemePreviewCard(
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = LocalColorsPalette.current.surface
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = LocalColorsPalette.current.onSurface
            )

            Text(
                text = "Background color",
                color = LocalColorsPalette.current.onBackground
            )

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = LocalColorsPalette.current.primary,
                    contentColor = LocalColorsPalette.current.onPrimary
                )
            ) {
                Text("Primary Button")
            }

            Text(
                text = "Secondary text",
                color = LocalColorsPalette.current.secondary
            )
        }
    }
}

@Preview
@Composable
fun ThemePreview() {
    Row {
        PlanMindTheme(darkTheme = false) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(LocalColorsPalette.current.background)
                    .padding(8.dp)
            ) {
                ThemePreviewCard("Light Theme")
            }
        }

        PlanMindTheme(darkTheme = true) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(LocalColorsPalette.current.background)
                    .padding(8.dp)
            ) {
                ThemePreviewCard("Dark Theme")
            }
        }
    }
}