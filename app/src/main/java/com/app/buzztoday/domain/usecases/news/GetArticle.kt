package com.app.buzztoday.domain.usecases.news

import com.app.buzztoday.data.internal.Dao
import com.app.buzztoday.domain.model.Article

class GetArticle(private val newsDao: Dao) {
    suspend operator fun invoke(url: String): Article?{
        return newsDao.getArticle(url = url)
    }
}