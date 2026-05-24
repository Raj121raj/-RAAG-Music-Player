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
import com.ragg.music.constants.RaagThemeStyle

// ═══════════════════════════════════════════════════════════════
// RAAG FUTURISTIC GLASSMORPHISM PALETTE
// ═══════════════════════════════════════════════════════════════

/** Deep AMOLED Background for AMOLED Dark theme */
val RaagAmoledBackground = Color(0xFF070B14)

/** Glass Surface — premium frosted glass effect */
val RaagGlassSurface = Color(0x14FFFFFF) // rgba(255,255,255,0.08)

/** Glass Border — subtle edge definition */
val RaagGlassBorder = Color(0x1FFFFFFF) // rgba(255,255,255,0.12)

/** Primary Glow */
val RaagPrimaryGlow = Color(0xFF8B5CF6)

/** Soft Lavender */
val RaagSoftLavender = Color(0xFFC4B5FD)

/** Blue Violet */
val RaagBlueViolet = Color(0xFF6366F1)

/** Glow Highlight */
val RaagGlowHighlight = Color(0xFFA78BFA)

/** White Text */
val RaagTextWhite = Color(0xFFF8FAFC)

/** Default theme color for legacy compatibility */
val DefaultThemeColor = RaagPrimaryGlow

// ═══════════════════════════════════════════════════════════════
// THEME SCHEMES
// ═══════════════════════════════════════════════════════════════

private val RaagGlassScheme = darkColorScheme(
    primary = RaagPrimaryGlow,
    onPrimary = RaagTextWhite,
    primaryContainer = RaagBlueViolet,
    onPrimaryContainer = RaagTextWhite,
    secondary = RaagSoftLavender,
    onSecondary = RaagTextWhite,
    secondaryContainer = RaagPrimaryGlow.copy(alpha = 0.4f),
    onSecondaryContainer = RaagTextWhite,
    background = Color(0xFF070B14), // Unified background
    onBackground = RaagTextWhite,
    surface = RaagGlassSurface,
    onSurface = RaagTextWhite,
    surfaceVariant = RaagGlassSurface,
    onSurfaceVariant = RaagTextWhite.copy(alpha = 0.7f),
    surfaceTint = RaagPrimaryGlow,
    outline = RaagGlassBorder, // Use the specified glass border
    surfaceContainer = RaagGlassSurface,
    surfaceContainerHigh = RaagGlassSurface,
    surfaceContainerHighest = RaagGlassSurface,
    surfaceContainerLow = RaagGlassSurface,
    surfaceContainerLowest = RaagGlassSurface,
)

private val RaagAmoledScheme = darkColorScheme(
    primary = RaagPrimaryGlow,
    onPrimary = RaagTextWhite,
    primaryContainer = RaagBlueViolet,
    onPrimaryContainer = RaagTextWhite,
    secondary = RaagSoftLavender,
    onSecondary = RaagTextWhite,
    secondaryContainer = RaagPrimaryGlow.copy(alpha = 0.3f),
    onSecondaryContainer = RaagTextWhite,
    background = RaagAmoledBackground,
    onBackground = RaagTextWhite,
    surface = RaagAmoledBackground, // Dark surfaces
    onSurface = RaagTextWhite,
    surfaceVariant = RaagAmoledBackground,
    onSurfaceVariant = RaagTextWhite.copy(alpha = 0.7f),
    surfaceTint = RaagPrimaryGlow,
    outline = RaagGlowHighlight.copy(alpha = 0.2f),
    surfaceContainer = RaagAmoledBackground,
    surfaceContainerHigh = RaagAmoledBackground,
    surfaceContainerHighest = RaagAmoledBackground,
    surfaceContainerLow = RaagAmoledBackground,
    surfaceContainerLowest = RaagAmoledBackground,
)

// ═══════════════════════════════════════════════════════════════
// RAAG THEME COMPOSABLE
// ═══════════════════════════════════════════════════════════════

@Composable
fun RaagTheme(
    themeStyle: RaagThemeStyle = RaagThemeStyle.GLASS_DEFAULT,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeStyle) {
        RaagThemeStyle.GLASS_DEFAULT -> RaagGlassScheme
        RaagThemeStyle.AMOLED_DARK -> RaagAmoledScheme
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
fun RAAGTheme(
    themeStyle: RaagThemeStyle = RaagThemeStyle.GLASS_DEFAULT,
    content: @Composable () -> Unit
) {
    RaagTheme(themeStyle = themeStyle, content = content)
}

// ═══════════════════════════════════════════════════════════════
// GLOBAL COLOR OBJECT
// ═══════════════════════════════════════════════════════════════

object RaagColors {
    val AmoledBackground = RaagAmoledBackground
    val PrimaryGlow = RaagPrimaryGlow
    val SoftLavender = RaagSoftLavender
    val BlueViolet = RaagBlueViolet
    val GlowHighlight = RaagGlowHighlight
    val Text = RaagTextWhite
    
    val GlassSurface = RaagGlassSurface
    val GlassBorder = RaagGlassBorder
    val PurpleGlow = RaagPrimaryGlow.copy(alpha = 0.4f)
    val DeepPurple = Color(0xFF070B14)

    // NEW PASTEL TOKENS for the "Frosted" theme
    val PastelLavender = Color(0xFFDCD6F7)
    val PastelPink = Color(0xFFFDE2E2)
    val PastelBlue = Color(0xFFA6B1E1)
    val AccentRed = Color(0xFFFF4B2B)
    val FrostedWhite = Color(0x66FFFFFF)
}


