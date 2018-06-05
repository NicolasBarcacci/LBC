package fr.meteordesign.domain.repository

import android.arch.lifecycle.LiveData
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.Song

interface MusicRepository {
    fun albums(): LiveData<List<Album>>
    fun songs(albumId: Long): LiveData<List<Song>>
}