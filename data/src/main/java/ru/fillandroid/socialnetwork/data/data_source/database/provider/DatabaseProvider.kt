package ru.fillandroid.socialnetwork.data.data_source.database.provider

import android.content.Context
import androidx.room.Room
import ru.fillandroid.socialnetwork.data.data_source.AuthDao
import ru.fillandroid.socialnetwork.data.data_source.FeedDao
import ru.fillandroid.socialnetwork.data.data_source.database.SocialNetworkDatabase

object DatabaseProvider {

    fun provideDatabase(applicationContext: Context): SocialNetworkDatabase {
        return Room.databaseBuilder(
            applicationContext,
            SocialNetworkDatabase::class.java,
            "social_network_db.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideAuthDao(db: SocialNetworkDatabase): AuthDao {
        return db.authDao()
    }

    fun provideFeedDao(db: SocialNetworkDatabase): FeedDao {
        return db.feedDao()
    }
}
