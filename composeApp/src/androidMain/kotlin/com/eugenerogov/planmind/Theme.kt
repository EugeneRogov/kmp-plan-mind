package com.eugenerogov.planmind

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.eugenerogov.planmind.theme.LocalColorsPalette
import com.eugenerogov.planmind.theme.OnDarkCustomColorsPalette
import com.eugenerogov.planmind.theme.OnLightCustomColorsPalette
import com.eugenerogov.planmind.theme.Typography

private val DarkColorScheme =
    darkColorScheme(
        primary = OnDarkCustomColorsPalette.primary
    )

private val LightColorScheme =
    lightColorScheme(
        primary = OnLightCustomColorsPalette.primary
//    secondary = PurpleGrey40,
//    tertiary = Pink40
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),
    )

@Composable
fun PlanMindTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }

            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

//    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    val customColorsPalette =
        if (darkTheme) {
            OnDarkCustomColorsPalette
        } else {
            OnLightCustomColorsPalette
        }

    CompositionLocalProvider(
        LocalColorsPalette provides customColorsPalette
    ) {
        MaterialTheme(
//            colors = colors,
            colorScheme = colorScheme,
            typography = Typography,
//            shapes = Shapes,
            content = content
        )
    }
}
