package com.app.buzztoday.data.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.buzztoday.domain.model.Article

@Database(entities = [Article::class],version = 1)
@TypeConverters(Convertor::class)
abstract class Base:RoomDatabase() {
    abstract val Dao:Dao
}