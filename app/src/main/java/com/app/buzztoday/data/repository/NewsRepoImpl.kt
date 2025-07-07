package com.app.buzztoday.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.buzztoday.data.remote.NewsApi
import com.app.buzztoday.data.remote.PagingSource
import com.app.buzztoday.data.remote.Source
import com.app.buzztoday.domain.model.Article
import com.app.buzztoday.domain.repository.NewsRepo
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
