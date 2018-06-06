package fr.meteordesign.data.repository.musicstorage

import android.arch.lifecycle.LiveData
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.Track

interface MusicStorage {
    fun albums(): LiveData<List<Album>>
    fun tracks(albumId: Long): LiveData<List<Track>>
}