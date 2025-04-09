package ru.fillandroid.socialnetwork.data.data_source.mapper

import ru.fillandroid.socialnetwork.data.data_source.data.ImageField
import ru.fillandroid.socialnetwork.domain.model.Image

fun ImageField.toDomain() = Image(
    id = imageId,
    url = url,
    isLiked = isLiked
)

fun Image.toEntity() = ImageField(
    imageId = id,
    url = url,
    isLiked = isLiked
)