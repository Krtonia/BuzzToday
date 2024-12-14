package com.example.buzztoday.data.web

import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("")
    suspend fun  getNews() : Response<String>
}