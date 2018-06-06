package fr.meteordesign.lbc

import android.app.Application
import fr.meteordesign.data.initDataDagger
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        initAppDagger(this)
        initDataDagger(this)
    }
}
