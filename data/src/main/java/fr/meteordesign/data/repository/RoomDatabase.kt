package fr.meteordesign.data.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import fr.meteordesign.data.entity.AlbumEntity
import fr.meteordesign.data.entity.PhotoEntity
import fr.meteordesign.data.repository.photostorage.PhotoDao

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "music_database"

@Database(entities = [AlbumEntity::class, PhotoEntity::class], version = DATABASE_VERSION)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun musicDao(): PhotoDao
}


