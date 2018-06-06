package fr.meteordesign.data.repository

import fr.meteordesign.data.repository.musicstorage.MusicStorage
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.Track
import fr.meteordesign.domain.repository.MusicRepository
import javax.inject.Inject

class MusicDataRepository: MusicRepository {

    @Inject
    lateinit var musicStorage: MusicStorage

    init {
        dagger.inject(this)
    }

    override fun albums(): android.arch.lifecycle.LiveData<List<Album>> = musicStorage.albums()

    override fun tracks(albumId: Long): android.arch.lifecycle.LiveData<List<Track>> =
            musicStorage.tracks(albumId)
}