package com.example.buzztoday.presentation.state

import android.view.SearchEvent

sealed class Event {

    data class Update(val searchQuery: String) : Event()

    object SearchNews : Event()

}