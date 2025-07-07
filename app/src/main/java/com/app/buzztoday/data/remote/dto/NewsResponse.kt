package com.app.buzztoday.data.remote.dto

import com.app.buzztoday.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)