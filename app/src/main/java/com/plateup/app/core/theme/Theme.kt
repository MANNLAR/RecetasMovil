package com.plateup.app.core.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = VerdePrincipal,
    onPrimary = TextoClaro,
    secondary = Jade,
    onSecondary = TextoOscuro,
    tertiary = Coral,
    onTertiary = TextoClaro,
    background = Crema,
    onBackground = TextoOscuro,
    surface = GrisSuave,
    onSurface = TextoOscuro
)

private val DarkColors = darkColorScheme(
    primary = VerdeClaro,
    onPrimary = TextoOscuro,
    secondary = Jade,
    onSecondary = TextoOscuro,
    tertiary = AmarilloDorado,
    onTertiary = TextoOscuro,
    background = ColorPaletteDark.background,
    onBackground = ColorPaletteDark.onBackground,
    surface = ColorPaletteDark.surface,
    onSurface = ColorPaletteDark.onSurface
)

private object ColorPaletteDark {
    val background = Color(0xFF0F0F0F)
    val onBackground = Color(0xFFEDEDED)
    val surface = Color(0xFF1A1A1A)
    val onSurface = Color(0xFFEDEDED)
}

@Composable
fun PlateUpTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        useDarkTheme -> DarkColors
        else -> LightColors
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !useDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = PlateUpTypography,
        content = content
    )
}
