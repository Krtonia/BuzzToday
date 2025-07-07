package com.app.buzztoday.presentation.startup

sealed class StartupEvent {
    object SaveAppEntry : StartupEvent()
}