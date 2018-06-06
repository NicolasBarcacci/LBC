package fr.meteordesign.data.entity.mapper

import fr.meteordesign.data.entity.AlbumEntity
import fr.meteordesign.data.entity.PhotoEntity
import fr.meteordesign.data.entity.JsonPhoto

fun transformToAlbumEntity(jsonPhoto: JsonPhoto): AlbumEntity = AlbumEntity(
        jsonPhoto.albumId,
        jsonPhoto.title,
        jsonPhoto.url)

fun transformToPhotoEntity(jsonPhoto: JsonPhoto): PhotoEntity = PhotoEntity(
        jsonPhoto.id,
        jsonPhoto.url,
        jsonPhoto.albumId)