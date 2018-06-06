package fr.meteordesign.data.repository.photostorage

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import fr.meteordesign.data.entity.*

@Dao
interface PhotoDao : PhotoStorage {

    @Query("SELECT * FROM ${ALBUM_TABLE}")
    override fun albums(): LiveData<List<AlbumEntity>>

    @Query("SELECT * FROM ${PHOTO_TABLE} WHERE ${PHOTO_ALBUM_ID} = :albumId")
    override fun photos(albumId: Long): LiveData<List<PhotoEntity>>
}
