package com.example.buzztoday.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.buzztoday.data.remote.NewsApi
import com.example.buzztoday.data.remote.PagingSource
import com.example.buzztoday.data.remote.Source
import com.example.buzztoday.domain.model.Article
import com.example.buzztoday.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow

class NewsRepoImpl(private val newsApi: NewsApi) : NewsRepo {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            PagingSource(
                newsApi = newsApi, sources = sources.joinToString(separator = ",")
            )
        }).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            Source(
                api = newsApi, sources = sources.joinToString(separator = ","),
                searchQuery = searchQuery
            )
        }).flow
    }
}
