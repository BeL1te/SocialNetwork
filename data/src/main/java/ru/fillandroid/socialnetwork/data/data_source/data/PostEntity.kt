package ru.fillandroid.socialnetwork.data.data_source.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    @PrimaryKey val id: String,
    val title: String,
    @Embedded val firstImage: ImageField,
//    @Embedded val secondImage: ImageField,
    val comment: String?
)
