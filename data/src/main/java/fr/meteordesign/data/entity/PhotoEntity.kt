package fr.meteordesign.data.entity

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE

const val PHOTO_TABLE = "photos"
const val PHOTO_ID = "id"
const val PHOTO_URL = "url"
const val PHOTO_ALBUM_ID = "album_id"

@Entity(tableName = PHOTO_TABLE,
        foreignKeys = [ForeignKey(entity = AlbumEntity::class,
                parentColumns = [ALBUM_COLUMN_ID],
                childColumns = [PHOTO_ALBUM_ID],
                onDelete = CASCADE)],
        indices = [(Index(PHOTO_ALBUM_ID))])
data class PhotoEntity(
        @PrimaryKey
        @ColumnInfo(name = PHOTO_ID)
        val id: Long,
        @ColumnInfo(name = PHOTO_URL)
        val url: String,
        @ColumnInfo(name = PHOTO_ALBUM_ID)
        val albumId: Long)