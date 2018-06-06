package fr.meteordesign.data.repository.musicstorage

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import fr.meteordesign.data.entity.*

@Dao
interface MusicDao : MusicStorage {

    @Query("SELECT * FROM ${ALBUM_TABLE}")
    override fun albums(): LiveData<List<AlbumEntity>>

    @Query("SELECT * FROM ${TRACK_TABLE} WHERE ${TRACK_ALBUM_ID} = :albumId")
    override fun tracks(albumId: Long): LiveData<List<TrackEntity>>
}
