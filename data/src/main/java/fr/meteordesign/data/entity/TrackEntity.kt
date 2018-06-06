package fr.meteordesign.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

const val TRACK_TABLE = "songs"
const val TRACK_ID = "id"
const val TRACK_TITLE = "title"
const val TRACK_ALBUM_ID = "album_id"

@Entity(tableName = TRACK_TABLE,
        foreignKeys = [ForeignKey(entity = AlbumEntity::class,
                parentColumns = [ALBUM_COLUMN_ID],
                childColumns = [TRACK_ID])])
data class TrackEntity(
        @PrimaryKey
        @ColumnInfo(name = TRACK_ID)
        val id: Long,
        @ColumnInfo(name = TRACK_TITLE)
        val title: String,
        @ColumnInfo(name = TRACK_ALBUM_ID)
        val albumId: Long)