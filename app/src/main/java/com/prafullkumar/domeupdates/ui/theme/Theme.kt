package com.prafullkumar.domeupdates.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val DarkColorScheme = darkColorScheme(
    primary = Red,
    onPrimary = Color.White,
    primaryContainer = DarkRed,
    onPrimaryContainer = Color.White,

    secondary = Red,
    onSecondary = Color.White,
    secondaryContainer = DarkRed,
    onSecondaryContainer = Color.White,

    background = DarkBackground,
    onBackground = DarkText,

    surface = DarkSurface,
    onSurface = DarkText,
    surfaceVariant = DarkCard,
    onSurfaceVariant = DarkTextSecondary,

    error = Color(0xFFFF453A),
    onError = Color.White,

    outline = Color(0xFF3C3C3C)
)

// Light theme colors
val LightColorScheme = lightColorScheme(
    primary = Red,
    onPrimary = Color.White,
    primaryContainer = LightRed,
    onPrimaryContainer = Color.White,

    secondary = Red,
    onSecondary = Color.White,
    secondaryContainer = LightRed,
    onSecondaryContainer = Color.White,

    background = LightBackground,
    onBackground = LightText,

    surface = LightSurface,
    onSurface = LightText,
    surfaceVariant = LightCard,
    onSurfaceVariant = LightTextSecondary,

    error = Color(0xFFFF3B30),
    onError = Color.White,

    outline = Color(0xFFE5E5EA)
)

@Composable
fun DomeUpdatesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}