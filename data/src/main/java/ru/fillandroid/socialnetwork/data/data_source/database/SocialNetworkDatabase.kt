package ru.fillandroid.socialnetwork.data.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.fillandroid.socialnetwork.data.data_source.AuthDao
import ru.fillandroid.socialnetwork.data.data_source.FeedDao
import ru.fillandroid.socialnetwork.data.data_source.data.AuthEntity
import ru.fillandroid.socialnetwork.data.data_source.data.PostEntity

@Database(
    entities = [PostEntity::class, AuthEntity::class],
    version = 1,

)
abstract class SocialNetworkDatabase : RoomDatabase() {

    abstract fun authDao(): AuthDao
    abstract fun feedDao(): FeedDao
}