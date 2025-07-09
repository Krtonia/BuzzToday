package com.app.buzztoday.domain.usecases.news

import androidx.paging.PagingData
import com.app.buzztoday.data.internal.Dao
import com.app.buzztoday.domain.model.Article
import com.app.buzztoday.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow

class Get(private val newsRepository: NewsRepo) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}