package fr.meteordesign.lbc.features.photos

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.repository.PhotosRepository
import fr.meteordesign.lbc.dagger
import timber.log.Timber
import javax.inject.Inject

class PhotosViewModelProvider(private val album: Album) : ViewModelProvider.NewInstanceFactory() {

    @Inject
    lateinit var photosRepository: PhotosRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        dagger.inject(this)
        return PhotosViewModel(photosRepository, album) as T
    }
}

class PhotosViewModel(photosRepository: PhotosRepository, val album: Album) : ViewModel() {

    val photos = photosRepository.photos(album.id)

    init {
        Timber.i("New instance, album: ${album}")
    }
}