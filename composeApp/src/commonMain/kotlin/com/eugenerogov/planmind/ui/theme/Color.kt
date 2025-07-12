package com.eugenerogov.planmind.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColorsPalette(
    val surface: Color = Color.Unspecified,
    val surfaceVariant: Color = Color.Unspecified,
    val primary: Color = Color.Unspecified,
    val primaryVariant: Color = Color.Unspecified,
    val onPrimary: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val secondaryVariant: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val onBackground: Color = Color.Unspecified,
    val outline: Color = Color.Unspecified,
    val outlineVariant: Color = Color.Unspecified,
    val onSurface: Color = Color.Unspecified,
    val onSurfaceVariant: Color = Color.Unspecified,
    val secondaryContainer: Color = Color.Unspecified,
    val onSecondaryContainer: Color = Color.Unspecified,
    val primaryContainer: Color = Color.Unspecified,
    val onPrimaryContainer: Color = Color.Unspecified,
    val orange: Color = Color.Unspecified,
    val orangeContainer: Color = Color.Unspecified,
    val onOrangeContainer: Color = Color.Unspecified,
    val green: Color = Color.Unspecified,
    val greenContainer: Color = Color.Unspecified,
    val onGreenContainer: Color = Color.Unspecified,
    val error: Color = Color.Unspecified,
)

val LocalColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }

val OnLightCustomColorsPalette =
    CustomColorsPalette(
        surface = Color(0xFFE4D6E5), // snuff – светлый фон
        surfaceVariant = Color(0xFFC7A1C9), // london-hue – вариант surface
        primary = Color(0xFF438AC5), // boston-blue – основной акцент
        primaryVariant = Color(0xFF1CACDC), // curious-blue – второй акцент
        onPrimary = Color(0xFFFFFFFF), // белый текст на кнопках
        secondary = Color(0xFFC24882), // mulberry – выделение/доп. элементы
        secondaryVariant = Color(0xFFBB88B5), // bouquet
        background = Color(0xFFE4D6E5), // snuff – unify фон и surface
        onBackground = Color(0xFF22202A), // тёмный для контраста
        outline = Color(0xFF944E97), // affair – обводки
        outlineVariant = Color(0xFF7565A9), // deluge – вариант обводки
        onSurface = Color(0xFF22202A), // основной текст
        onSurfaceVariant = Color(0xFFC24882), // mulberry – вариант текста
        secondaryContainer = Color(0xFFC7A1C9),
        onSecondaryContainer = Color(0xFF438AC5),
        primaryContainer = Color(0xFF438AC5),
        onPrimaryContainer = Color(0xFFE4D6E5),
        orange = Color(0xFFE75D74), // mandy – уведомления/варнинг/успех
        orangeContainer = Color(0xFFFDEDF2), // snuff, высветлим
        onOrangeContainer = Color(0xFFC24882),
        green = Color(0xFFC270A6), // hopbush – успех/special
        greenContainer = Color(0xFFF4E0EF),
        onGreenContainer = Color(0xFF944E97),
        error = Color(0xFFE75D74), // mandy
    )

val OnDarkCustomColorsPalette =
    CustomColorsPalette(
        surface = Color(0xFF22202A), // глубокий тёмный
        surfaceVariant = Color(0xFF7565A9), // deluge
        primary = Color(0xFF438AC5), // boston-blue (оставить акцентным)
        primaryVariant = Color(0xFF1CACDC), // curious-blue
        onPrimary = Color(0xFFE4D6E5), // светлый text
        secondary = Color(0xFFC24882), // mulberry
        secondaryVariant = Color(0xFF944E97), // affair
        background = Color(0xFF18171D), // самый тёмный для отличия
        onBackground = Color(0xFFE4D6E5), // светлый текст
        outline = Color(0xFF944E97), // affair
        outlineVariant = Color(0xFFC7A1C9), // london-hue/цвет мягче
        onSurface = Color(0xFFEEECEE), // почти белый
        onSurfaceVariant = Color(0xFFE75D74), // mandy
        secondaryContainer = Color(0xFF7565A9), // deluge
        onSecondaryContainer = Color(0xFFE4D6E5),
        primaryContainer = Color(0xFF1CACDC),
        onPrimaryContainer = Color(0xFFE4D6E5),
        orange = Color(0xFFE75D74), // mandy
        orangeContainer = Color(0xFFC24882), // mulberry
        onOrangeContainer = Color(0xFFE4D6E5),
        green = Color(0xFFBB88B5), // bouquet
        greenContainer = Color(0xFF7565A9), // чуть темнее
        onGreenContainer = Color(0xFFE4D6E5),
        error = Color(0xFFE75D74), // mandy
    )
