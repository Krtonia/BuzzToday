package com.app.buzztoday.presentation.startup

import androidx.annotation.DrawableRes
import com.app.buzztoday.R

data class Page(
    val title:String,
    val description:String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Stay Ahead with the Latest in Tech",
        description = "Discover cutting-edge innovations, gadget reviews, and the latest trends shaping the future of technology.\uD83D\uDE80",
        image = R.drawable.tech
    ),
    Page(
        title = "Stay Informed, Stay Empowered",
        description = "Get real-time updates on breaking news, global events, and stories that matter to you.\uD83D\uDCF0",
        image = R.drawable.news
    ),
    Page(
        title = "Master the Market with Insights",
        description = "Track market movements, analyze trends, and make smarter investment decisions with expert insights.\uD83D\uDCC8",
        image = R.drawable.trade
    ),
)