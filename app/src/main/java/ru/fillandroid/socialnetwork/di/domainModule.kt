package ru.fillandroid.socialnetwork.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.fillandroid.socialnetwork.domain.use_case.auth.RequestCodeUseCase
import ru.fillandroid.socialnetwork.domain.use_case.auth.ValidateCodeUseCase
import ru.fillandroid.socialnetwork.domain.use_case.feed.CommentPostUseCase
import ru.fillandroid.socialnetwork.domain.use_case.feed.GetPostsUseCase
import ru.fillandroid.socialnetwork.domain.use_case.feed.LikeImageUseCase

val domainModule = module {
    factoryOf(::RequestCodeUseCase)
    factoryOf(::ValidateCodeUseCase)
    factoryOf(::CommentPostUseCase)
    factoryOf(::GetPostsUseCase)
    factoryOf(::LikeImageUseCase)
}
