package fr.meteordesign.data.entity.mapper

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import fr.meteordesign.data.entity.PhotoEntity
import fr.meteordesign.domain.Photo

fun transform(photoEntities: LiveData<List<PhotoEntity>>): LiveData<List<Photo>> =
        Transformations.map(photoEntities,
                { it.map { photoEntity -> transform(photoEntity) } })

fun transform(photoEntity: PhotoEntity): Photo = Photo(photoEntity.id, photoEntity.title)

