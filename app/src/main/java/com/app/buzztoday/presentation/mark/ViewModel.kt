package com.app.buzztoday.presentation.mark

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.app.buzztoday.domain.usecases.news.NewsCases
import com.app.buzztoday.presentation.state.BookmarkState
import com.app.buzztoday.presentation.state.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val newsUseCases: NewsCases, newsCases: NewsCases
) : ViewModel(newsCases) {

    private val _state = mutableStateOf(BookmarkState())
    override val state: State<BookmarkState> = _state

    init {
        getArticles()
    }

    private fun getArticles() {
        newsUseCases.getArticles().onEach {
            _state.value = _state.value.copy(articles = it)
        }.launchIn(viewModelScope)
    }
}
