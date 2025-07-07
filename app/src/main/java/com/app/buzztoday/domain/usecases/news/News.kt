package com.app.buzztoday.domain.usecases.news

import androidx.paging.PagingData
import com.app.buzztoday.domain.model.Article
import com.app.buzztoday.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow

class News(private val newsRepo: NewsRepo) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>>{
        return newsRepo.getNews(sources=sources)

    }
}