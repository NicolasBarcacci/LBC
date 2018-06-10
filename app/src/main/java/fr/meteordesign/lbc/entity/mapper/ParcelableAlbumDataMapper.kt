package fr.meteordesign.lbc.entity.mapper

import fr.meteordesign.domain.Album
import fr.meteordesign.lbc.entity.ParcelableAlbum

fun transform(album: Album): ParcelableAlbum = ParcelableAlbum(album.id, album.title, album.coverUrl)

fun transform(parcelableAlbum: ParcelableAlbum): Album =
        Album(parcelableAlbum.id, parcelableAlbum.title, parcelableAlbum.coverUrl)