package com.lucasn.filmesfavoritos.userinterface

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CoresFilmesFavoritos = lightColorScheme(
    primary = Color(0xFF8C1D2F),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFD9DC),
    onPrimaryContainer = Color(0xFF3F0010),
    secondary = Color(0xFF7D5A50),
    secondaryContainer = Color(0xFFFFDAD4),
    background = Color(0xFFFFF8F6),
    surface = Color(0xFFFFF8F6),
    surfaceVariant = Color(0xFFF5E9E7),
    surfaceContainer = Color(0xFFF9ECEA),
)

@Composable
fun FilmesFavoritosTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = CoresFilmesFavoritos, content = content)
}
