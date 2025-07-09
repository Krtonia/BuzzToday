package com.app.buzztoday.domain.repository

import com.app.buzztoday.domain.model.Article
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData

interface NewsRepo {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getArticles(): Flow<List<Article>>

    suspend fun getArticle(url: String): Article?

}