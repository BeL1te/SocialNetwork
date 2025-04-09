package ru.fillandroid.socialnetwork.data.data_source.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AuthEntity(
    @PrimaryKey val id: String,
    val code: String
)
