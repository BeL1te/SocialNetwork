package ru.fillandroid.socialnetwork.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.fillandroid.socialnetwork.data.repository.AuthRepositoryImpl
import ru.fillandroid.socialnetwork.data.repository.FeedRepositoryImpl
import ru.fillandroid.socialnetwork.domain.repository.AuthRepository
import ru.fillandroid.socialnetwork.domain.repository.FeedRepository

val dataModule = module {
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    singleOf(::FeedRepositoryImpl) { bind<FeedRepository>() }
}