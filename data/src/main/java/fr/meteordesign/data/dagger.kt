package fr.meteordesign.data

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import fr.meteordesign.data.repository.DATABASE_NAME
import fr.meteordesign.data.repository.PhotosDataRepository
import fr.meteordesign.data.repository.PhotoDatabase
import fr.meteordesign.data.repository.photostorage.PhotoStorage
import fr.meteordesign.data.repository.photostorage.PhotoStorageMockup
import javax.inject.Singleton

lateinit var dagger: DataDaggerComponent

fun initDataDagger(application: Application) {
    dagger = DaggerDataDaggerComponent.builder()
            .appModule(AppModule(application))
            .build()
}

@Singleton
@Component(modules = [AppModule::class, MusicStorageModule::class])
interface DataDaggerComponent {
    fun inject(musicDataRepository: PhotosDataRepository)
}

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun getContext(): Context = application
}

@Module
class MusicStorageModule {

    @Provides
    @Singleton
    fun getMusicDatabase(context: Context): PhotoDatabase =
            Room.databaseBuilder(context, PhotoDatabase::class.java, DATABASE_NAME)
                    .build()

    @Provides
    @Singleton
    fun getMusicStorage(roomDatabase: PhotoDatabase): PhotoStorage =
            if (!BuildConfig.MOCK_PHOTO_DATA) roomDatabase.musicDao() else PhotoStorageMockup()
}