package com.app.buzztoday.domain.usecases.news

data class NewsCases(
    val news: News,
    val search: Search,
    val searchNews: Search,
    val upsertArticle: Upsert,
    val deleteArticle: Delete,
    val getArticles: Get,
    val getArticle: GetArticle
)
