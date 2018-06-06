package fr.meteordesign.lbc

import android.os.Parcelable
import fr.meteordesign.domain.Album
import kotlinx.android.parcel.Parcelize

@Parcelize
class ParcelableAlbum(private val id: Long,
                      private val title: String,
                      private val coverUrl: String) : Parcelable {

    constructor(album: Album) : this(
            album.id,
            album.title,
            album.coverUrl
    )

    fun toAlbum(): Album = Album(id, title, coverUrl)
}