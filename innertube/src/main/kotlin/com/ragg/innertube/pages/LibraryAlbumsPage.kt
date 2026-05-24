package com.ragg.innertube.pages

import com.ragg.innertube.models.Album
import com.ragg.innertube.models.AlbumItem
import com.ragg.innertube.models.Artist
import com.ragg.innertube.models.ArtistItem
import com.ragg.innertube.models.MusicResponsiveListItemRenderer
import com.ragg.innertube.models.MusicTwoRowItemRenderer
import com.ragg.innertube.models.PlaylistItem
import com.ragg.innertube.models.SongItem
import com.ragg.innertube.models.YTItem
import com.ragg.innertube.models.oddElements
import com.ragg.innertube.utils.parseTime

data class LibraryAlbumsPage(
    val albums: List<AlbumItem>,
    val continuation: String?,
) {
    companion object {
        fun fromMusicTwoRowItemRenderer(renderer: MusicTwoRowItemRenderer): AlbumItem? {
            return AlbumItem(
                        browseId = renderer.navigationEndpoint.browseEndpoint?.browseId ?: return null,
                        playlistId = renderer.thumbnailOverlay?.musicItemThumbnailOverlayRenderer?.content
                            ?.musicPlayButtonRenderer?.playNavigationEndpoint
                            ?.watchPlaylistEndpoint?.playlistId ?: return null,
                        title = renderer.title.runs?.firstOrNull()?.text ?: return null,
                        artists = null,
                        year = renderer.subtitle?.runs?.lastOrNull()?.text?.toIntOrNull(),
                        thumbnail = renderer.thumbnailRenderer.musicThumbnailRenderer?.getThumbnailUrl() ?: return null,
                        explicit = renderer.subtitleBadges?.find {
                            it.musicInlineBadgeRenderer?.icon?.iconType == "MUSIC_EXPLICIT_BADGE"
                        } != null
                    )
        }
    }
}

