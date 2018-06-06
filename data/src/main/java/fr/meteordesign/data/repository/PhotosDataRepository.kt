package fr.meteordesign.data.repository

import android.arch.lifecycle.LiveData
import fr.meteordesign.data.dagger
import fr.meteordesign.data.entity.mapper.transform
import fr.meteordesign.data.repository.photostorage.PhotoStorage
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.Photo
import fr.meteordesign.domain.repository.PhotosRepository
import javax.inject.Inject

class PhotosDataRepository : PhotosRepository {

    @Inject
    lateinit var photoStorage: PhotoStorage

    init {
        dagger.inject(this)
    }

    override fun albums(): LiveData<List<Album>> = transform(photoStorage.albums())

    override fun photos(albumId: Long): LiveData<List<Photo>> = transform(photoStorage.photos(albumId))
}