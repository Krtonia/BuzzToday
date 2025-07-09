package com.app.buzztoday.presentation.state

import com.app.buzztoday.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)