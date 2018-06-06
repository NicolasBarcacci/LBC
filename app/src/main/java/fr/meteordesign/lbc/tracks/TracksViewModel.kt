package fr.meteordesign.lbc.tracks

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.repository.MusicRepository
import fr.meteordesign.lbc.dagger
import timber.log.Timber
import javax.inject.Inject

class TracksViewModelProvider(private val album: Album) : ViewModelProvider.NewInstanceFactory() {

    @Inject
    lateinit var musicRepository: MusicRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        dagger.inject(this)
        return TracksViewModel(musicRepository, album) as T
    }
}

class TracksViewModel(musicRepository: MusicRepository, val album: Album) : ViewModel() {

    val tracks = musicRepository.tracks(album.id)

    init {
        Timber.i("New instance, album: ${album}")
    }
}