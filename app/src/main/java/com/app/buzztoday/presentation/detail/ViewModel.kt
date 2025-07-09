package com.app.buzztoday.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.app.buzztoday.domain.model.Article
import com.app.buzztoday.domain.usecases.news.NewsCases
import com.app.buzztoday.presentation.state.ViewModel
import com.app.buzztoday.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsCases, newsCases: NewsCases
) : ViewModel(newsCases) {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: Event) {
        when (event) {
            is Event.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.getArticle(url = event.article.url)
                    if (article == null){
                        upsertArticle(article = event.article)
                    }else{
                        deleteArticle(article = event.article)
                    }
                }
            }
            is Event.RemoveSideEffect ->{
                sideEffect = null
            }
            Event.SaveArticle -> TODO()
        }
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article = article)
        sideEffect = UIComponent.Toast("Article deleted")
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article = article)
        sideEffect = UIComponent.Toast("Article Inserted")
    }

}