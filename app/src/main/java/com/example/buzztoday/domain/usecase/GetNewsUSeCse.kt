package com.example.buzztoday.domain.usecase

import com.example.buzztoday.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUSeCse @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(
        language: String,
        text: String?,
        country: String?
    ) = newsRepository.getNews(language, text, country)
}