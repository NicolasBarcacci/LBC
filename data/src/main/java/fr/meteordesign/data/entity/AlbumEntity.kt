package fr.meteordesign.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

const val ALBUM_TABLE = "albums"
const val ALBUM_COLUMN_ID = "id"
const val ALBUM_COLUMN_TITLE = "title"
const val ALBUM_COLUMN_COVER_URL = "cover_url"

@Entity(tableName = ALBUM_TABLE)
data class AlbumEntity(
        @PrimaryKey
        @ColumnInfo(name = ALBUM_COLUMN_ID)
        val id: Long,
        @ColumnInfo(name = ALBUM_COLUMN_TITLE)
        val title: String,
        @ColumnInfo(name = ALBUM_COLUMN_COVER_URL)
        val coverUrl: String)
