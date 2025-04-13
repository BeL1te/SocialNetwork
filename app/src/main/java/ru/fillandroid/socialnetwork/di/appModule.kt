package ru.fillandroid.socialnetwork.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.fillandroid.socialnetwork.data.data_source.database.provider.DatabaseProvider

val appModule = module {

    single {
        DatabaseProvider.provideDatabase(androidContext().applicationContext)
    }
    singleOf(DatabaseProvider::provideAuthDao)
    singleOf(DatabaseProvider::provideFeedDao)
}