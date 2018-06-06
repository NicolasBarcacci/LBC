package fr.meteordesign.data

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import fr.meteordesign.data.repository.DATABASE_NAME
import fr.meteordesign.data.repository.MusicDataRepository
import fr.meteordesign.data.repository.MusicDatabase
import fr.meteordesign.data.repository.musicstorage.MusicStorage
import fr.meteordesign.data.repository.musicstorage.MusicStorageMockup
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
    fun inject(musicDataRepository: MusicDataRepository)
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
    fun getMusicDatabase(context: Context): MusicDatabase =
            Room.databaseBuilder(context, MusicDatabase::class.java, DATABASE_NAME)
                    .build()

    @Provides
    @Singleton
    fun getMusicStorage(roomDatabase: MusicDatabase): MusicStorage =
            if (!BuildConfig.MOCK_MUSIC_DATA) roomDatabase.musicDao() else MusicStorageMockup()
}