package com.example.buzztoday.domain.repository

import com.example.buzztoday.domain.model.Article
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

}