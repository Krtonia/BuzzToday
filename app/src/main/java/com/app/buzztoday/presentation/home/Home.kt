package com.app.buzztoday.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.app.buzztoday.domain.usecases.news.NewsCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Home @Inject constructor(private val newsCases: NewsCases) : ViewModel() {

    val news =
        newsCases.news(sources = listOf("BBC-News", "The New York Times", "CNN"))
            .cachedIn(viewModelScope)

}