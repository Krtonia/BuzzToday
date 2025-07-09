package com.app.buzztoday.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.buzztoday.data.internal.Dao
import com.app.buzztoday.data.remote.NewsApi
import com.app.buzztoday.data.remote.PagingSource
import com.app.buzztoday.data.remote.Source
import com.app.buzztoday.domain.model.Article
import com.app.buzztoday.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow

class NewsRepoImpl(private val newsApi: NewsApi,private val newsDao: Dao) : NewsRepo {
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

    override suspend fun upsertArticle(article: Article) {
        newsDao.update(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override suspend fun getArticle(url: String): Article? {
        return newsDao.getArticle(url = url)
    }

    override fun getArticles(): Flow<List<Article>> {
        return newsDao.get()
    }
}
