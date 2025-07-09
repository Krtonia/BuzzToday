package com.app.buzztoday.data.remote

import com.app.buzztoday.data.remote.dto.NewsResponse
import com.app.buzztoday.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apikey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("everything")
    fun Searches(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apikey") apiKey: String = API_KEY
    ): NewsResponse
}