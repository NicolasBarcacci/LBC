package fr.meteordesign.data.repository.photostorage

import android.arch.lifecycle.LiveData
import fr.meteordesign.data.entity.AlbumEntity
import fr.meteordesign.data.entity.PhotoEntity

interface PhotoStorage {
    fun albums(): LiveData<List<AlbumEntity>>
    fun insert(albumEntity: AlbumEntity)
    fun photos(albumId: Long): LiveData<List<PhotoEntity>>
    fun insert(photoEntity: PhotoEntity)
}