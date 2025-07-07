package com.app.buzztoday

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.buzztoday.domain.usecases.AppEntry
import com.app.buzztoday.presentation.nav.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainModel @Inject constructor(private val appEntry: AppEntry) :
    ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntry.read().onEach { StartFromHomescreen ->
            if (StartFromHomescreen) {
                startDestination = Route.NewsNavigation.route
            } else {
                startDestination = Route.AppStartNavigation.route
            }
            delay(250)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}