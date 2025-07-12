package com.eugenerogov.planmind.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme as iosIsSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
actual fun isSystemInDarkTheme(): Boolean = iosIsSystemInDarkTheme()