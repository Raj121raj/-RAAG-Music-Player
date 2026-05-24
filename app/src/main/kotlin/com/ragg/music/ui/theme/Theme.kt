package com.ragg.music.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.ragg.music.constants.RaggThemeStyle

// ═══════════════════════════════════════════════════════════════
// RAGG FUTURISTIC GLASSMORPHISM PALETTE
// ═══════════════════════════════════════════════════════════════

/** Deep AMOLED Background for AMOLED Dark theme */
val RaggAmoledBackground = Color(0xFF070B14)

/** Glass Surface — premium frosted glass effect */
val RaggGlassSurface = Color(0x14FFFFFF) // rgba(255,255,255,0.08)

/** Glass Border — subtle edge definition */
val RaggGlassBorder = Color(0x1FFFFFFF) // rgba(255,255,255,0.12)

/** Primary Glow */
val RaggPrimaryGlow = Color(0xFF8B5CF6)

/** Soft Lavender */
val RaggSoftLavender = Color(0xFFC4B5FD)

/** Blue Violet */
val RaggBlueViolet = Color(0xFF6366F1)

/** Glow Highlight */
val RaggGlowHighlight = Color(0xFFA78BFA)

/** White Text */
val RaggTextWhite = Color(0xFFF8FAFC)

/** Default theme color for legacy compatibility */
val DefaultThemeColor = RaggPrimaryGlow

// ═══════════════════════════════════════════════════════════════
// THEME SCHEMES
// ═══════════════════════════════════════════════════════════════

private val RaggGlassScheme = darkColorScheme(
    primary = RaggPrimaryGlow,
    onPrimary = RaggTextWhite,
    primaryContainer = RaggBlueViolet,
    onPrimaryContainer = RaggTextWhite,
    secondary = RaggSoftLavender,
    onSecondary = RaggTextWhite,
    secondaryContainer = RaggPrimaryGlow.copy(alpha = 0.4f),
    onSecondaryContainer = RaggTextWhite,
    background = Color(0xFF070B14), // Unified background
    onBackground = RaggTextWhite,
    surface = RaggGlassSurface,
    onSurface = RaggTextWhite,
    surfaceVariant = RaggGlassSurface,
    onSurfaceVariant = RaggTextWhite.copy(alpha = 0.7f),
    surfaceTint = RaggPrimaryGlow,
    outline = RaggGlassBorder, // Use the specified glass border
    surfaceContainer = RaggGlassSurface,
    surfaceContainerHigh = RaggGlassSurface,
    surfaceContainerHighest = RaggGlassSurface,
    surfaceContainerLow = RaggGlassSurface,
    surfaceContainerLowest = RaggGlassSurface,
)

private val RaggAmoledScheme = darkColorScheme(
    primary = RaggPrimaryGlow,
    onPrimary = RaggTextWhite,
    primaryContainer = RaggBlueViolet,
    onPrimaryContainer = RaggTextWhite,
    secondary = RaggSoftLavender,
    onSecondary = RaggTextWhite,
    secondaryContainer = RaggPrimaryGlow.copy(alpha = 0.3f),
    onSecondaryContainer = RaggTextWhite,
    background = RaggAmoledBackground,
    onBackground = RaggTextWhite,
    surface = RaggAmoledBackground, // Dark surfaces
    onSurface = RaggTextWhite,
    surfaceVariant = RaggAmoledBackground,
    onSurfaceVariant = RaggTextWhite.copy(alpha = 0.7f),
    surfaceTint = RaggPrimaryGlow,
    outline = RaggGlowHighlight.copy(alpha = 0.2f),
    surfaceContainer = RaggAmoledBackground,
    surfaceContainerHigh = RaggAmoledBackground,
    surfaceContainerHighest = RaggAmoledBackground,
    surfaceContainerLow = RaggAmoledBackground,
    surfaceContainerLowest = RaggAmoledBackground,
)

// ═══════════════════════════════════════════════════════════════
// RAGG THEME COMPOSABLE
// ═══════════════════════════════════════════════════════════════

@Composable
fun RaggTheme(
    themeStyle: RaggThemeStyle = RaggThemeStyle.GLASS_DEFAULT,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeStyle) {
        RaggThemeStyle.GLASS_DEFAULT -> RaggGlassScheme
        RaggThemeStyle.AMOLED_DARK -> RaggAmoledScheme
    }
    
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}

// ═══════════════════════════════════════════════════════════════
// LEGACY COMPAT ENTRY POINT
// ═══════════════════════════════════════════════════════════════

@Composable
fun RAGGTheme(
    themeStyle: RaggThemeStyle = RaggThemeStyle.GLASS_DEFAULT,
    content: @Composable () -> Unit
) {
    RaggTheme(themeStyle = themeStyle, content = content)
}

// ═══════════════════════════════════════════════════════════════
// GLOBAL COLOR OBJECT
// ═══════════════════════════════════════════════════════════════

object RaggColors {
    val AmoledBackground = RaggAmoledBackground
    val PrimaryGlow = RaggPrimaryGlow
    val SoftLavender = RaggSoftLavender
    val BlueViolet = RaggBlueViolet
    val GlowHighlight = RaggGlowHighlight
    val Text = RaggTextWhite
    
    val GlassSurface = RaggGlassSurface
    val GlassBorder = RaggGlassBorder
    val PurpleGlow = RaggPrimaryGlow.copy(alpha = 0.4f)
    val DeepPurple = Color(0xFF070B14)

    // NEW PASTEL TOKENS for the "Frosted" theme
    val PastelLavender = Color(0xFFDCD6F7)
    val PastelPink = Color(0xFFFDE2E2)
    val PastelBlue = Color(0xFFA6B1E1)
    val AccentRed = Color(0xFFFF4B2B)
    val FrostedWhite = Color(0x66FFFFFF)
}


