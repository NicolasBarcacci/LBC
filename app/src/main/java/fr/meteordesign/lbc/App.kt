package fr.meteordesign.lbc

import android.app.Application
import fr.meteordesign.data.initDataDagger
import fr.meteordesign.data.source.PhotosDataSource
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        initAppDagger(this)
        initDataDagger(this)

        PhotosDataSource().download()
    }
}
