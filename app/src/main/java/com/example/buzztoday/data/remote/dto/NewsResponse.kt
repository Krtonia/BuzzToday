package com.example.buzztoday.data.remote.dto

import com.example.buzztoday.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)