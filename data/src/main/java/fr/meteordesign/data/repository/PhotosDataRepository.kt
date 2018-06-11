package fr.meteordesign.data.repository

import android.arch.lifecycle.LiveData
import android.content.SharedPreferences
import fr.meteordesign.data.dagger
import fr.meteordesign.data.entity.mapper.transform
import fr.meteordesign.data.repository.photostorage.PhotoStorage
import fr.meteordesign.data.source.PhotosDataSource
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.Photo
import fr.meteordesign.domain.repository.PhotosRepository
import timber.log.Timber
import javax.inject.Inject

const val PHOTOS_PREFERENCES = "PHOTOS_PREFERENCES"
const val DOWNLOAD_COMPLETE = "DOWNLOAD_COMPLETE"

class PhotosDataRepository : PhotosRepository {

    @Inject
    lateinit var photoStorage: PhotoStorage

    @Inject
    lateinit var photosPrerences: SharedPreferences

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

    private fun arePhotosDownloaded(): Boolean = photosPrerences.getBoolean(DOWNLOAD_COMPLETE, false)

    override fun albums(): LiveData<List<Album>> = transform(photoStorage.albums())

    override fun photos(albumId: Long): LiveData<List<Photo>> = transform(photoStorage.photos(albumId))
}