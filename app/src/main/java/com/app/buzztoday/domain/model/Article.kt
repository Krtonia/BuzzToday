package com.app.buzztoday.domain.model

import androidx.room.Entity
import androidx.room.Embedded
import androidx.room.PrimaryKey


@Entity
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    @Embedded(prefix = "source_") val source: Source,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
)