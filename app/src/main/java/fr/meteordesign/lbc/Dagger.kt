package fr.meteordesign.lbc

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import fr.meteordesign.data.repository.MusicDataRepository
import fr.meteordesign.domain.repository.MusicRepository
import fr.meteordesign.lbc.albums.AlbumsAdapter
import fr.meteordesign.lbc.albums.AlbumsViewModelProvider
import fr.meteordesign.lbc.imageloader.GlideImageLoader
import fr.meteordesign.lbc.imageloader.ImageLoader
import fr.meteordesign.lbc.tracks.TracksFragment
import fr.meteordesign.lbc.tracks.TracksViewModelProvider
import javax.inject.Singleton

lateinit var dagger: DaggerComponent

fun initAppDagger(application: Application) {
    dagger = DaggerDaggerComponent.builder()
            .appModule(AppModule(application))
            .build()
}

@Singleton
@Component(modules = [
    AppModule::class,
    MusicRepositoryModule::class,
    ImageLoaderGlideModule::class])
interface DaggerComponent {
    fun inject(albumsViewModelProvider: AlbumsViewModelProvider)
    fun inject(albumsAdapter: AlbumsAdapter)
    fun inject(tracksViewModelProvider: TracksViewModelProvider)
    fun inject(tracksFragment: TracksFragment)
}

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun getContext(): Context = application
}

@Module
class MusicRepositoryModule {

    @Provides
    @Singleton
    fun getMusicRepository(): MusicRepository = MusicDataRepository()
}

@Module
class ImageLoaderGlideModule {

    @Provides
    @Singleton
    fun getImageLoader(context: Context): ImageLoader = GlideImageLoader(context)
}