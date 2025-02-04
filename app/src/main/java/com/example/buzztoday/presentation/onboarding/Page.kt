package com.example.buzztoday.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.buzztoday.R

data class Page(
    val title:String,
    val description:String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Example Text for Tech here",
        description = "Example Tech Text here",
        image = R.drawable.tech
    ),
    Page(
        title = "Example Text for News here",
        description = "Example News Text here",
        image = R.drawable.news
    ),
    Page(
        title = "Example Text for Trade here",
        description = "Example Trade Text here",
        image = R.drawable.trade
    ),
)