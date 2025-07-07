package com.app.buzztoday.data.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.buzztoday.domain.model.Article

@Database(entities = [Article::class],version = 1)
abstract class Base:RoomDatabase() {
    abstract val dao:Dao
}