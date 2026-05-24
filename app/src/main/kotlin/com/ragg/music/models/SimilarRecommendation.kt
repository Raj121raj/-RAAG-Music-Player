/**
 * RAAG Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.ragg.music.models

import com.ragg.innertube.models.YTItem
import com.ragg.music.db.entities.LocalItem

data class SimilarRecommendation(
    val title: LocalItem,
    val items: List<YTItem>,
)

