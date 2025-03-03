package com.example.buzztoday.presentation.state

import androidx.paging.PagingData
import com.example.buzztoday.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchSt(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
) {
}