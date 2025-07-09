package com.app.buzztoday.domain.usecases.news

import com.app.buzztoday.data.internal.Dao
import com.app.buzztoday.domain.model.Article
import kotlinx.coroutines.flow.Flow

class Get(private val newsDao: Dao) {
    operator fun invoke(url: String): Flow<List<Article>>{
        return newsDao.get(url=url)
    }
}