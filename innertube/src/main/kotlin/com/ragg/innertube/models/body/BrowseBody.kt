package com.ragg.innertube.models.body

import com.ragg.innertube.models.Context
import com.ragg.innertube.models.Continuation
import kotlinx.serialization.Serializable

@Serializable
data class BrowseBody(
    val context: Context,
    val browseId: String?,
    val params: String?,
    val continuation: String?
)

