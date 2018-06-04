package fr.meteordesign.domain.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.Song

interface MusicRepository {
    fun albumsByPage(): LiveData<PagedList<Album>>
    fun songs(albumId: Long): LiveData<List<Song>>
}