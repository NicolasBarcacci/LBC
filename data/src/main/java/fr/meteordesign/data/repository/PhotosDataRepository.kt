package fr.meteordesign.data.repository

import android.arch.lifecycle.LiveData
import fr.meteordesign.data.dagger
import fr.meteordesign.data.entity.mapper.transform
import fr.meteordesign.data.repository.photostorage.PhotoStorage
import fr.meteordesign.data.source.PhotosDataSource
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.Photo
import fr.meteordesign.domain.repository.PhotosRepository
import timber.log.Timber
import javax.inject.Inject

class PhotosDataRepository : PhotosRepository {

    @Inject
    lateinit var photoStorage: PhotoStorage

    private val photosDataSource = PhotosDataSource()

    init {
        dagger.inject(this)

        checkArePhotoStored()
    }

    private fun checkArePhotoStored() {
        if (!arePhotosDownloaded()) {
            Timber.i("Photos are not stored, start download")
            photosDataSource.startDownload()
        }
    }

    private fun arePhotosDownloaded(): Boolean = false

    override fun albums(): LiveData<List<Album>> = transform(photoStorage.albums())

    override fun photos(albumId: Long): LiveData<List<Photo>> = transform(photoStorage.photos(albumId))
}