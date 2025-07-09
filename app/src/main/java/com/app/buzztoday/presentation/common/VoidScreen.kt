package com.app.buzztoday.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.app.buzztoday.R
import com.app.buzztoday.ui.theme.Black
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    defaultMessage: String = "You have not saved news so far!",
    defaultIconId: Int = R.drawable.ic_search_document
) {
    val message = remember {
        mutableStateOf(parseErrorMessage(error = error))
    }

    val icon = remember {
        mutableStateOf(R.drawable.ic_network_error)
    }

    if (error == null) {
        message.value = "You have not saved news so far!"
        icon.value = R.drawable.ic_search_document
    }

    val startAnimation = remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation.value) 0.3f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(key1 = true) {
        startAnimation.value = true
    }

    EmptyContent(
        alphaAnim = alphaAnimation,
        message = message.value,
        iconId = icon.value,
        iconSize = 120.dp,
        textPadding = 10.dp,
        textStyle = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun EmptyContent(
    alphaAnim: Float,
    message: String,
    iconId: Int,
    iconSize: Dp = 120.dp,
    textPadding: Dp = 10.dp,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) Black else DarkGray,
            modifier = Modifier
                .size(iconSize)
                .graphicsLayer { alpha = alphaAnim }
        )
        Text(
            modifier = Modifier
                .padding(textPadding)
                .graphicsLayer { alpha = alphaAnim },
            text = message,
            fontWeight = FontWeight.Bold,
            style = textStyle,
            color = if (isSystemInDarkTheme()) Black else DarkGray,
        )
    }
}

fun parseErrorMessage(error: LoadState.Error?): String {
    return when (val exception = error?.error) {
        is SocketTimeoutException -> "Server is Currently Busy"
        is ConnectException -> "Internet Unavailable."
        else -> {
            Log.e("EmptyScreen", "Unknown error: ${exception?.message}")
            "Unexpected Error"
        }
    }
}