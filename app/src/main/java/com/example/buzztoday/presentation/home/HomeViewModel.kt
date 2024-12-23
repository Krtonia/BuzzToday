package com.example.buzztoday.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buzztoday.data.model.News
import com.example.buzztoday.domain.usecase.GetNewsUSeCse
import dagger.Binds
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class HomeViewModel @Inject constructor(val getNewsUSeCse: GetNewsUSeCse): ViewModel() {
    init {
        viewModelScope.launch {
            val result = getNews()
            result.forEach {
                Log.d("News",it.title)
            }
        }
    }
    suspend fun getNews(): List<News>{
        return getNewsUSeCse.invoke("en",null,null).news
    }
}