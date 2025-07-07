package com.app.buzztoday.data.internal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.buzztoday.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM Article")
    fun get(): Flow<List<Article>>
}