package com.amp.pokeapi.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Define color sets for light/dark themes
private val LightColors = lightColorScheme(
    primary = Color(0xFFCC0000),
    onPrimary = Color.White,
    surface = Color(0xFFF3F3F3),
    onSurface = Color.Black
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFCC0000),
    onPrimary = Color.White,
    surface = Color(0xFF121212),
    onSurface = Color.White
)

// Keep only one definition of this function in your project
@Composable
fun PokeAPITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}