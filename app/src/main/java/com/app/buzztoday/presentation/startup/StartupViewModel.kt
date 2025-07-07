package com.app.buzztoday.presentation.startup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.buzztoday.domain.usecases.AppEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(private val appEntryUsecases: AppEntry) :
    ViewModel() {

    fun onEvent(event: StartupEvent) {
        when (event) {
            is StartupEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUsecases.save()
        }
    }
}