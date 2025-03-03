package com.example.buzztoday.domain.usecases.news

import androidx.paging.PagingData
import com.example.buzztoday.domain.model.Article
import com.example.buzztoday.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow

class Search(private val Repo: NewsRepo)
{
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Repo.searchNews(
            searchQuery = searchQuery,
            sources = sources
        )
    }
}