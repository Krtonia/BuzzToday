package com.example.buzztoday.domain.repository

import com.example.buzztoday.data.response.NewsResponse

// NewsRepository.kt
interface NewsRepository {
    suspend fun getNews(language: String, text: String?, country: String?): NewsResponse
}