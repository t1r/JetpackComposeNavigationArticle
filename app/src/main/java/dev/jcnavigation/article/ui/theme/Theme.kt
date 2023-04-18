package dev.jcnavigation.article.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xff388e3c),
    primaryVariant = Color(0xff1b5e20),
    secondary = Color(0xffff8f00),
    secondaryVariant = Color(0xffffa000),
    background = Color(0xff455a64),
    surface = Color(0xff546e7a),
    error = Color(0xffd50000),
    onPrimary = Color(0xfffffde7),
    onSecondary = Color(0xff000000),
    onBackground = Color(0xffffffff),
    onSurface = Color(0xffffffff),
    onError = Color(0xffffffff),
)

private val LightColorPalette = lightColors(
    primary = Color(0xff43a047),
    primaryVariant = Color(0xff1b5e20),
    secondary = Color(0xfffdd835),
    secondaryVariant = Color(0xfff57f17),
    background = Color(0xffffffff),
    surface = Color(0xffffffff),
    error = Color(0xffd32f2f),
    onPrimary = Color(0xffffffff),
    onSecondary = Color(0xff000000),
    onBackground = Color(0xff000000),
    onSurface = Color(0xff000000),
    onError = Color(0xffffffff),
)

@Composable
fun JetpackComposeNavigationArticleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}