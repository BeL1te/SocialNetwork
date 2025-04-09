package ru.fillandroid.socialnetwork.domain.use_case.feed

import ru.fillandroid.socialnetwork.domain.model.Post
import ru.fillandroid.socialnetwork.domain.repository.FeedRepository

class CommentPostUseCase(
    private val feedRepository: FeedRepository
) {

    suspend operator fun invoke(post: Post) = feedRepository.commentPost(post)
}