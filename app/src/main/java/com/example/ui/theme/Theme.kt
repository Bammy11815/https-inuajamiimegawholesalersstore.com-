package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = ForestGreenPrimary,
    onPrimary = Color.White,
    primaryContainer = ForestGreenContainer,
    onPrimaryContainer = ForestGreenOnContainer,
    secondary = GoldAccent,
    onSecondary = Color.White,
    secondaryContainer = GoldAccentLight,
    onSecondaryContainer = GoldAccentDark,
    tertiary = MPesaGreen,
    onTertiary = Color.White,
    background = Slate50,
    onBackground = Slate900,
    surface = SurfaceWhite,
    onSurface = Slate900,
    surfaceVariant = Slate100,
    onSurfaceVariant = Slate700,
    outline = Slate200,
    outlineVariant = Slate400,
    error = CrimsonError,
    onError = Color.White,
    errorContainer = CrimsonErrorContainer,
    onErrorContainer = CrimsonError
)

private val DarkColorScheme = darkColorScheme(
    primary = ForestGreenLight,
    onPrimary = Color.White,
    primaryContainer = ForestGreenDark,
    onPrimaryContainer = ForestGreenContainer,
    secondary = GoldAccent,
    onSecondary = Color.White,
    secondaryContainer = GoldAccentDark,
    onSecondaryContainer = GoldAccentLight,
    tertiary = MPesaGreen,
    onTertiary = Color.White,
    background = Slate900,
    onBackground = Slate50,
    surface = Slate800,
    onSurface = Slate50,
    surfaceVariant = Slate700,
    onSurfaceVariant = Slate200,
    outline = Slate600,
    outlineVariant = Slate500,
    error = CrimsonError,
    onError = Color.White
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.primary.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
