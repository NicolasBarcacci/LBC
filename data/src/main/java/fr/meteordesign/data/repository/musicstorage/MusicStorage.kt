package fr.meteordesign.data.repository.musicstorage

import android.arch.lifecycle.LiveData
import fr.meteordesign.data.entity.AlbumEntity
import fr.meteordesign.data.entity.TrackEntity

interface MusicStorage {
    fun albums(): LiveData<List<AlbumEntity>>
    fun tracks(albumId: Long): LiveData<List<TrackEntity>>
}