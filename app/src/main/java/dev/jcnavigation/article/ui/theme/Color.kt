package dev.jcnavigation.article.ui.theme

import androidx.compose.ui.graphics.Color

fun expressPrimaryColor(
    isSystemInDarkTheme: Boolean,
): Color {
    return if (isSystemInDarkTheme) Color(0xffc2185b)
    else Color(0xffc2185b)
}

fun primaryColor(
    isSystemInDarkTheme: Boolean,
): Color {
    return if (isSystemInDarkTheme) Color(0xff388e3c)
    else Color(0xff43a047)
}