package ru.fillandroid.socialnetwork.data.repository

import ru.fillandroid.socialnetwork.data.data_source.FeedDao
import ru.fillandroid.socialnetwork.data.data_source.data.PostEntity
import ru.fillandroid.socialnetwork.data.data_source.mapper.toDomain
import ru.fillandroid.socialnetwork.data.data_source.mapper.toEntity
import ru.fillandroid.socialnetwork.domain.model.Post
import ru.fillandroid.socialnetwork.domain.repository.FeedRepository

class FeedRepositoryImpl(
    private val dao: FeedDao
) : FeedRepository {

    override suspend fun likeImage(post: Post) {
        dao.updatePost(post.toEntity())
    }

    override suspend fun commentPost(post: Post) {
        dao.updatePost(post.toEntity())
    }

    override suspend fun getPosts(): List<Post> {
        dao.insertPosts(PostEntity.posts)
        return dao.getPosts().map { it.toDomain() }
    }
}