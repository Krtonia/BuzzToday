package com.example.buzztoday.data.response

import com.example.buzztoday.data.model.News

data class NewsResponse(
    val available: Int,
    val news: List<News>,
    val number: Int,
    val offset: Int
)