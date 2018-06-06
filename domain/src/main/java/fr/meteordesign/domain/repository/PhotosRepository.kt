package fr.meteordesign.domain.repository

import android.arch.lifecycle.LiveData
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.Photo

interface PhotosRepository {
    fun albums(): LiveData<List<Album>>
    fun photos(albumId: Long): LiveData<List<Photo>>
}