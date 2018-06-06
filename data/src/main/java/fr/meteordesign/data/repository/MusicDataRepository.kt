package fr.meteordesign.data.repository

import android.arch.lifecycle.LiveData
import fr.meteordesign.data.dagger
import fr.meteordesign.data.entity.mapper.transform
import fr.meteordesign.data.repository.musicstorage.MusicStorage
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.Track
import fr.meteordesign.domain.repository.MusicRepository
import javax.inject.Inject

class MusicDataRepository : MusicRepository {

    @Inject
    lateinit var musicStorage: MusicStorage

    init {
        dagger.inject(this)
    }

    override fun albums(): LiveData<List<Album>> = transform(musicStorage.albums())

    override fun tracks(albumId: Long): LiveData<List<Track>> = transform(musicStorage.tracks(albumId))
}