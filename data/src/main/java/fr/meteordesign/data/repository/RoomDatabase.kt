package fr.meteordesign.data.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import fr.meteordesign.data.entity.AlbumEntity
import fr.meteordesign.data.entity.TrackEntity
import fr.meteordesign.data.repository.musicstorage.MusicDao

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "music_database"

@Database(entities = [AlbumEntity::class, TrackEntity::class], version = DATABASE_VERSION)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun musicDao(): MusicDao
}


