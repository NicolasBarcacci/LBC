package fr.meteordesign.domain.repository

import android.arch.lifecycle.LiveData
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.Track

interface MusicRepository {
    fun albums(): LiveData<List<Album>>
    fun tracks(albumId: Long): LiveData<List<Track>>
}