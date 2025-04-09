package ru.fillandroid.socialnetwork.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.fillandroid.socialnetwork.data.data_source.data.PostEntity

@Dao
interface FeedDao {

    @Query("SELECT * FROM postentity")
    fun getPosts(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updatePost(post: PostEntity)

}
