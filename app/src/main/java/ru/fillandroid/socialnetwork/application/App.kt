package ru.fillandroid.socialnetwork.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.fillandroid.socialnetwork.di.appModule
import ru.fillandroid.socialnetwork.di.dataModule
import ru.fillandroid.socialnetwork.di.domainModule
import ru.fillandroid.socialnetwork.feature.auth.di.authModule
import ru.fillandroid.socialnetwork.feature.feed.di.feedModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        koinInit()
    }

    private fun koinInit() {
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                dataModule,
                domainModule,
                authModule,
                feedModule
            )
        }
    }
}

