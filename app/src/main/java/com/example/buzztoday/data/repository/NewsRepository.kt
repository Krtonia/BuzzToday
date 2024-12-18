package com.example.buzztoday.data.repository

import com.example.buzztoday.data.response.NewsResponse
import com.example.buzztoday.data.web.NewsApi
import com.example.buzztoday.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepository @Inject constructor(val api: NewsApi): NewsRepository {
    override suspend fun getNews(language: String, text: String?, country: String?): NewsResponse {
     val response = api.getNews(country,language, text)
     return response.body()!!
    }
}