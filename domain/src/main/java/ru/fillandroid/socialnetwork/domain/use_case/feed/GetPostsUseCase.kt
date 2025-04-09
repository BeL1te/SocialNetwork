package ru.fillandroid.socialnetwork.domain.use_case.feed

import ru.fillandroid.socialnetwork.domain.repository.FeedRepository

class GetPostsUseCase(
    private val feedRepository: FeedRepository
) {

    suspend operator fun invoke() = feedRepository.getPosts()
}
