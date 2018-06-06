package fr.meteordesign.lbc.albums

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import fr.meteordesign.domain.repository.PhotosRepository
import fr.meteordesign.lbc.dagger
import timber.log.Timber
import javax.inject.Inject

class AlbumsViewModelProvider : ViewModelProvider.NewInstanceFactory() {

    @Inject
    lateinit var photosRepository: PhotosRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        dagger.inject(this)
        return AlbumsViewModel(photosRepository) as T
    }
}

class AlbumsViewModel(photosRepository: PhotosRepository) : ViewModel() {

    val albums = photosRepository.albums()

    init {
        Timber.i("New instance")
    }
}

