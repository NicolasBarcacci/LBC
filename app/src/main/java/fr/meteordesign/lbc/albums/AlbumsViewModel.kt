package fr.meteordesign.lbc.albums

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import fr.meteordesign.domain.repository.MusicRepository
import fr.meteordesign.lbc.dagger
import timber.log.Timber
import javax.inject.Inject

class AlbumsViewModelProvider : ViewModelProvider.NewInstanceFactory() {

    @Inject
    lateinit var musicRepository: MusicRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        dagger.inject(this)
        return AlbumsViewModel(musicRepository) as T
    }
}

class AlbumsViewModel(musicRepository: MusicRepository) : ViewModel() {

    val albums = musicRepository.albums()

    init {
        Timber.i("New instance")
    }
}

