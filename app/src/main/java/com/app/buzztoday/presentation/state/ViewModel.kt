package com.app.buzztoday.presentation.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.app.buzztoday.domain.usecases.news.NewsCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class ViewModel @Inject constructor(private var newsCases: NewsCases): ViewModel()
{
    private var _state = mutableStateOf(SearchSt())
    open val state: State<SearchSt> = _state


    fun onEvent(event: Event) {
        when (event) {
            is Event.Update -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is Event.SearchNews -> {
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val articles = newsCases.search(
            searchQuery = _state.value.searchQuery,
            sources = listOf("BBC-News", "The New York Times", "CNN")
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(articles = articles)
    }

}