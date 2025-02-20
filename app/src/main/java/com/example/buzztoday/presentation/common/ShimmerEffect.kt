package com.example.buzztoday.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.ui.res.colorResource
import com.example.buzztoday.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush

fun Modifier.ShimmerEffect(): Modifier = composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value

    val shimmerColor = colorResource(id = R.color.shimmer).copy(alpha = alpha)
    val brush = Brush.horizontalGradient(
        colors = listOf(
            shimmerColor.copy(alpha = 0.6f),
            shimmerColor,
            shimmerColor.copy(alpha = 0.6f)
        ),
        startX = 0f,
        endX = 1000f
    )

    this.drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(brush = brush)
        }
    }
}

/*@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.ShimmerEffect() = composed{
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}*/

