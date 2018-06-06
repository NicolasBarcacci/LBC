package fr.meteordesign.data.source

import fr.meteordesign.data.dagger
import fr.meteordesign.data.entity.mapper.transformToAlbumEntity
import fr.meteordesign.data.entity.mapper.transformToPhotoEntity
import fr.meteordesign.data.repository.photostorage.PhotoStorage
import fr.meteordesign.data.source.photosdataprovider.PhotosProvider
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PhotosDataSource {

    @Inject
    lateinit var photosProvider: PhotosProvider

    @Inject
    lateinit var photoStorage: PhotoStorage

    init {
        dagger.inject(this)
    }

    fun download() {
        photosProvider.photos()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { jsonPhotos ->
                            for (jsonPhoto in jsonPhotos) {
                                photoStorage.insert(transformToAlbumEntity(jsonPhoto))
                                photoStorage.insert(transformToPhotoEntity(jsonPhoto))
                            }
                        },
                        { Timber.e(it) }
                )
    }
}