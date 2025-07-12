package com.eugenerogov.planmind.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme as wasmIsSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
actual fun isSystemInDarkTheme(): Boolean = wasmIsSystemInDarkTheme()