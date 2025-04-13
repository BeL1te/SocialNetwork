package ru.fillandroid.socialnetwork.data.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.fillandroid.socialnetwork.data.data_source.AuthDao
import ru.fillandroid.socialnetwork.data.data_source.FeedDao
import ru.fillandroid.socialnetwork.data.data_source.data.AuthEntity
import ru.fillandroid.socialnetwork.data.data_source.data.PostEntity
import ru.fillandroid.socialnetwork.data.data_source.database.type_converters.ImageFieldTypeConverter

@TypeConverters(value = [ImageFieldTypeConverter::class])
@Database(
    entities = [PostEntity::class, AuthEntity::class],
    version = 2,

)
abstract class SocialNetworkDatabase : RoomDatabase() {

    abstract fun authDao(): AuthDao
    abstract fun feedDao(): FeedDao
}
