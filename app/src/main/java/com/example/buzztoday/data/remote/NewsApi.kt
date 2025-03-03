package com.example.buzztoday.data.remote

import com.example.buzztoday.data.remote.dto.NewsResponse
import com.example.buzztoday.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apikey") apiKey: String = API_KEY,
        searchQuery: String
    ): NewsResponse

    @GET("top-headlines")
    suspend fun Searches(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apikey") apiKey: String = API_KEY
    ): NewsResponse
}

