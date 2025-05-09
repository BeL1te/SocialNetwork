package ru.fillandroid.socialnetwork.data.data_source.mapper

import ru.fillandroid.socialnetwork.data.data_source.data.PostEntity
import ru.fillandroid.socialnetwork.domain.model.Post

fun PostEntity.toDomain() = Post(
    id = id,
    description = description,
    firstImage = firstImage.toDomain(),
    secondImage = secondImage.toDomain(),
    comment = comment
)

fun Post.toEntity() = PostEntity(
    id = id,
    description = description,
    firstImage = firstImage.toEntity(),
    secondImage = secondImage.toEntity(),
    comment = comment
)
