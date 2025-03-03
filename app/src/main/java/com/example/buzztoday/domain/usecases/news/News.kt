package com.example.buzztoday.domain.usecases.news

import androidx.paging.PagingData
import com.example.buzztoday.domain.model.Article
import com.example.buzztoday.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow

class News(private val newsRepo: NewsRepo) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>>{
        return newsRepo.getNews(sources=sources)

    }
}