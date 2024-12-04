package com.example.buzztoday.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse

class NewsViewModel : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        fetchNewsTopHeadlines()
    }

    fun fetchNewsTopHeadlines() {
        val newsApiClient = NewsApiClient(Key.apiKey)

        val request = TopHeadlinesRequest.Builder().language("en").build()

        newsApiClient.getTopHeadlines(request, object : NewsApiClient.ArticlesResponseCallback {
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.let {
                    _articles.postValue(it)
                }
            }

            override fun onFailure(throwable: Throwable?) {
                val errorMessage = throwable?.localizedMessage ?: "Unknown error occurred"
                _error.postValue(errorMessage)
                Log.e("NewsAPI Response Failed", errorMessage)
            }
        })
    }
}


