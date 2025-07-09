package com.app.buzztoday.presentation.detail

import com.app.buzztoday.domain.model.Article

sealed class Event {
    data class UpsertDeleteArticle(val article: Article) : Event()
    object RemoveSideEffect : Event()
    object SaveArticle : Event()
}