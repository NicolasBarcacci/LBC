package fr.meteordesign.lbc.songs

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.repository.MusicRepository
import fr.meteordesign.lbc.dagger
import timber.log.Timber
import javax.inject.Inject

class SongsViewModelProvider(private val album: Album) : ViewModelProvider.NewInstanceFactory() {

    @Inject
    lateinit var musicRepository: MusicRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        dagger.inject(this)
        return SongsViewModel(musicRepository, album) as T
    }
}

class SongsViewModel(musicRepository: MusicRepository, album: Album): ViewModel() {

    val songs = musicRepository.songs(album.id)

    init {
        Timber.i("New instance, album: ${album}")
    }
}