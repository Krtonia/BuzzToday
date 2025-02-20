package com.example.buzztoday.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.buzztoday.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsUseCases: NewsUseCases):ViewModel() {

    val news = newsUseCases.getNews(sources = listOf("BBC-News","ABC-News","Al-jazera-english")).cachedIn(viewModelScope)

}