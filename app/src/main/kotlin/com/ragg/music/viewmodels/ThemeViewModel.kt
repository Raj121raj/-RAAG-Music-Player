/**
 * RAAG Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.ragg.music.viewmodels

import androidx.lifecycle.ViewModel
import com.ragg.music.ui.screens.settings.DarkMode
import com.ragg.music.ui.theme.DefaultThemeColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThemeViewModel : ViewModel() {
    // Theme state flows - RAAG is strictly dark cinematic
    private val _darkMode = MutableStateFlow(DarkMode.ON)
    val darkMode: StateFlow<DarkMode> = _darkMode.asStateFlow()

    private val _pureBlack = MutableStateFlow(false)
    val pureBlack: StateFlow<Boolean> = _pureBlack.asStateFlow()

    private val _selectedThemeColorInt = MutableStateFlow(DefaultThemeColor.hashCode())
    val selectedThemeColorInt: StateFlow<Int> = _selectedThemeColorInt.asStateFlow()

    fun updateDarkMode(mode: DarkMode) {
        // No-op for RAAG
    }

    fun updatePureBlack(enabled: Boolean) {
        // No-op for RAAG
    }

    fun updateThemeColor(colorInt: Int) {
        // No-op for RAAG
    }
}

