package ru.fillandroid.socialnetwork.domain.repository

import ru.fillandroid.socialnetwork.domain.model.Post

interface FeedRepository {

    suspend fun likeImage(post: Post)

    suspend fun commentPost(post: Post)

    suspend fun getPosts(): List<Post>

}
