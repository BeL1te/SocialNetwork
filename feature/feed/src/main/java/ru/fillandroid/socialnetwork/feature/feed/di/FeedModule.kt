package ru.fillandroid.socialnetwork.feature.feed.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.fillandroid.socialnetwork.feature.feed.presentation.FeedViewModel

val feedModule = module {
    viewModelOf(::FeedViewModel)
}