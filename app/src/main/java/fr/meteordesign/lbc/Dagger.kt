package fr.meteordesign.lbc

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import fr.meteordesign.data.repository.PhotosDataRepository
import fr.meteordesign.domain.repository.PhotosRepository
import fr.meteordesign.lbc.features.albums.AlbumsAdapter
import fr.meteordesign.lbc.features.albums.AlbumsViewModelProvider
import fr.meteordesign.lbc.features.photos.PhotosAdapter
import fr.meteordesign.lbc.features.photos.PhotosViewModelProvider
import fr.meteordesign.lbc.imageloader.GlideImageLoader
import fr.meteordesign.lbc.imageloader.ImageLoader
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
    PhotoRepositoryModule::class,
    ImageLoaderGlideModule::class])
interface DaggerComponent {
    fun inject(albumsViewModelProvider: AlbumsViewModelProvider)
    fun inject(albumsAdapter: AlbumsAdapter)
    fun inject(photosViewModelProvider: PhotosViewModelProvider)
    fun inject(photosAdapter: PhotosAdapter)
}

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun getContext(): Context = application
}

@Module
class PhotoRepositoryModule {

    @Provides
    @Singleton
    fun getPhotoRepository(): PhotosRepository = PhotosDataRepository()
}

@Module
class ImageLoaderGlideModule {

    @Provides
    @Singleton
    fun getImageLoader(context: Context): ImageLoader = GlideImageLoader(context)
}