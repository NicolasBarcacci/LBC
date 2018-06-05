package fr.meteordesign.lbc

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

lateinit var dagger: DaggerComponent

fun initDagger(application: Application) {
    dagger = DaggerDaggerComponent.builder()
            .appModule(AppModule(application))
            .build()
}

@Singleton
@Component(modules = [AppModule::class])
interface DaggerComponent

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun getContext(): Context = application
}
