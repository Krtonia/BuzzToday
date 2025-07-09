package com.app.buzztoday.domain.usecases.news

import com.app.buzztoday.data.internal.Dao
import com.app.buzztoday.domain.model.Article

class Delete(private val newsDao: Dao) {
    suspend operator fun invoke(article: Article){
        newsDao.delete(article = article)
    }
}