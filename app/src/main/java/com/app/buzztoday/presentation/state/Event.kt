package com.app.buzztoday.presentation.state

sealed class Event {

    data class Update(val searchQuery: String) : Event()

    object SearchNews : Event()

}