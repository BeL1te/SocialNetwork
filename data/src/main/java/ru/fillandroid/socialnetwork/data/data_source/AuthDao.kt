package ru.fillandroid.socialnetwork.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.fillandroid.socialnetwork.data.data_source.data.AuthEntity

@Dao
interface AuthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCode(code: AuthEntity)

    @Query("SELECT code FROM authentity LIMIT 1")
    fun requestCode(): String
}
