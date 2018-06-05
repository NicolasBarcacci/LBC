package fr.meteordesign.lbc

import android.os.Parcelable
import fr.meteordesign.domain.Album
import kotlinx.android.parcel.Parcelize

@Parcelize
class ParcelableAlbum(private val id: Long,
                      private val coverUrl: String) : Parcelable {

    constructor(album: Album) : this(
            album.id,
            album.coverUrl
    )

    fun toAlbum(): Album = Album(id, coverUrl)
}