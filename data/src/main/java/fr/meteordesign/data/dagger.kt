package fr.meteordesign.data

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import fr.meteordesign.data.repository.DATABASE_NAME
import fr.meteordesign.data.repository.PhotoDatabase
import fr.meteordesign.data.repository.PhotosDataRepository
import fr.meteordesign.data.repository.photostorage.PhotoStorage
import fr.meteordesign.data.repository.photostorage.PhotoStorageMockup
import fr.meteordesign.data.source.PhotosDataSource
import fr.meteordesign.data.source.photosdataprovider.PhotosProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

lateinit var dagger: DataDaggerComponent

fun initDataDagger(application: Application) {
    dagger = DaggerDataDaggerComponent.builder()
            .appModule(AppModule(application))
            .photosStorageModule(PhotosStorageModule(BuildConfig.MOCK_PHOTO_DATA))
            .photosProviderModule(PhotosProviderModule(BuildConfig.PHOTO_SERVICE_URL))
            .build()
}

@Singleton
@Component(modules = [AppModule::class, PhotosStorageModule::class, PhotosProviderModule::class])
interface DataDaggerComponent {
    fun inject(photosDataRepository: PhotosDataRepository)
    fun inject(photosDataSource: PhotosDataSource)
}

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun getContext(): Context = application
}

@Module
class PhotosStorageModule(private val isMock: Boolean) {

    @Provides
    @Singleton
    fun getPhotosDatabase(context: Context): PhotoDatabase =
            Room.databaseBuilder(context, PhotoDatabase::class.java, DATABASE_NAME)
                    .build()

    @Provides
    @Singleton
    fun getPhotosStorage(roomDatabase: PhotoDatabase): PhotoStorage =
            if (!isMock) roomDatabase.musicDao() else PhotoStorageMockup()
}

@Module
class PhotosProviderModule(private val serviceUrl: String) {

    @Provides
    @Singleton
    fun getPhotosSource(): PhotosProvider = Retrofit.Builder()
            .baseUrl(serviceUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(PhotosProvider::class.java)
}