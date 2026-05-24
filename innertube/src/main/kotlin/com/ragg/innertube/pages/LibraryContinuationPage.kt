package com.ragg.innertube.pages

import com.ragg.innertube.models.YTItem

data class LibraryContinuationPage(
    val items: List<YTItem>,
    val continuation: String?,
)

