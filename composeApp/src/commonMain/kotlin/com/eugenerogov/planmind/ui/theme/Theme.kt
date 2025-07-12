package com.eugenerogov.planmind.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun PlanMindTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorsPalette = if (darkTheme) {
        OnDarkCustomColorsPalette
    } else {
        OnLightCustomColorsPalette
    }

    CompositionLocalProvider(
        LocalColorsPalette provides colorsPalette,
        LocalDim provides Dimensions(),
        content = content
    )
}