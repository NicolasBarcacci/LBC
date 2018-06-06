package fr.meteordesign.data.entity.mapper

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import fr.meteordesign.data.entity.AlbumEntity
import fr.meteordesign.domain.Album

fun transform(albumEntities: LiveData<List<AlbumEntity>>): LiveData<List<Album>> =
        Transformations.map(albumEntities,
                { it.map { albumEntity -> transform(albumEntity) } })

fun transform(albumEntity: AlbumEntity): Album =
        Album(
                albumEntity.id,
                albumEntity.title,
                albumEntity.coverUrl)

