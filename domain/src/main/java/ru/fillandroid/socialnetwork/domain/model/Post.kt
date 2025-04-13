package ru.fillandroid.socialnetwork.domain.model

data class Post(
    val id: String,
    val description: String,
    val firstImage: Image,
    val secondImage: Image,
    val comment: String? = null
) {

    fun haveLike(): Boolean = firstImage.isLiked || secondImage.isLiked
}
