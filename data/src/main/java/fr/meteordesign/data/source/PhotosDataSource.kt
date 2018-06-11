package fr.meteordesign.data.source

import android.content.*
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import fr.meteordesign.data.dagger
import fr.meteordesign.data.entity.mapper.transformToAlbumEntity
import fr.meteordesign.data.entity.mapper.transformToPhotoEntity
import fr.meteordesign.data.repository.DOWNLOAD_COMPLETE
import fr.meteordesign.data.repository.photostorage.PhotoStorage
import fr.meteordesign.data.source.photosdataprovider.PhotosProvider
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PhotosDataSource {

    @Inject
    lateinit var context: Context
    private val connectivityManager by lazy { context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager }
    private val networkChangeReceiver by lazy { initNetworkChangeReceiver() }
    private val networkChangeFilter by lazy { IntentFilter(CONNECTIVITY_ACTION) }

    @Inject
    lateinit var photosProvider: PhotosProvider
    @Inject
    lateinit var photoStorage: PhotoStorage
    @Inject
    lateinit var photosPreferences: SharedPreferences

    init {
        dagger.inject(this)
    }

    private fun initNetworkChangeReceiver(): BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                CONNECTIVITY_ACTION -> onNetworkStateChange()
            }
        }
    }

    fun startDownload() {
        Timber.d("startDownload")
        context.registerReceiver(networkChangeReceiver, networkChangeFilter)
    }

    private fun onNetworkStateChange() {
        val connected = isConnected()

        Timber.d("onNetworkStateChange, connected ? ${connected}")
        if (connected) {
            downloadPhotos()
        }
    }

    private fun isConnected(): Boolean = connectivityManager.activeNetworkInfo?.isConnected ?: false

    private fun downloadPhotos() {
        Timber.d("downloadPhotos")
        photosProvider.photos()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { jsonPhotos ->
                            for (jsonPhoto in jsonPhotos) {
                                photoStorage.insert(transformToAlbumEntity(jsonPhoto))
                                photoStorage.insert(transformToPhotoEntity(jsonPhoto))
                            }
                            onDownloadCompleted()
                        },
                        { Timber.e(it, "Unable to save all the photos") }
                )
    }

    private fun onDownloadCompleted() {
        Timber.d("onDownloadCompleted")
        context.unregisterReceiver(networkChangeReceiver)
        photosPreferences.edit()
                .putBoolean(DOWNLOAD_COMPLETE, true)
                .apply()
    }
}