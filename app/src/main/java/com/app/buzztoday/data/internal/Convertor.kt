package com.app.buzztoday.data.internal

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.app.buzztoday.domain.model.Source

@ProvidedTypeConverter
class Convertor {

    @TypeConverter
    fun str(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun src(source: String): Source {
        return source.split(",").let { sourceArr -> Source(sourceArr[0], sourceArr[1]) }
    }

}