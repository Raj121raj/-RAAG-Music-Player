/**
 * RAGG Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.ragg.music.models

import com.ragg.innertube.models.YTItem

data class ItemsPage(
    val items: List<YTItem>,
    val continuation: String?,
)

