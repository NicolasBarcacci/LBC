package fr.meteordesign.data.repository

import dagger.Component
import dagger.Module
import dagger.Provides
import fr.meteordesign.data.repository.musicstorage.MusicStorage
import fr.meteordesign.data.repository.musicstorage.MusicStorageMockup
import javax.inject.Singleton

lateinit var dagger: DataDaggerComponent

fun initDataDagger() {
    dagger = DaggerDataDaggerComponent.builder()
            .build()
}

@Singleton
@Component(modules = [MusicStorageMockupModule::class])
interface DataDaggerComponent {
    fun inject(musicDataRepository: MusicDataRepository)
}

@Module
class MusicStorageMockupModule {

    @Provides
    @Singleton
    fun getMusicStorage(): MusicStorage = MusicStorageMockup()
}