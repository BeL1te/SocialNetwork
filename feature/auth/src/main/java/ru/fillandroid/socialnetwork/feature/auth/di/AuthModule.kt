package ru.fillandroid.socialnetwork.feature.auth.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.fillandroid.socialnetwork.feature.auth.presentation.AuthViewModel

val authModule = module {
    viewModelOf(::AuthViewModel)
}
