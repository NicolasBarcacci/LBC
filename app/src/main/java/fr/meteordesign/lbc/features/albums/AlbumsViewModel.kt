package fr.meteordesign.lbc.features.albums

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.view.View
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

    val messageVisibility = MutableLiveData<Int>()
    val albumsVisibility = MutableLiveData<Int>()
    val albums = photosRepository.albums()

    init {
        Timber.i("New instance")

        messageVisibility.value = View.VISIBLE
        albumsVisibility.value = View.INVISIBLE

        albums.observeForever({ refreshVisibility() }) // TODO fix me
    }

    private fun refreshVisibility() {
        if (albums.value!!.isNotEmpty()) {
            messageVisibility.value = View.INVISIBLE
            albumsVisibility.value = View.VISIBLE

        } else {
            messageVisibility.value = View.VISIBLE
            albumsVisibility.value = View.INVISIBLE
        }
    }
}

