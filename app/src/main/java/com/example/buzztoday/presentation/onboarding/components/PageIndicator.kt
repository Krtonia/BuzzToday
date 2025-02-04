package com.example.buzztoday.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
    modifier: Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = Color.DarkGray
) {
    Row(modifier = Modifier, horizontalArrangement = Arrangement.SpaceEvenly) {
        repeat(pageSize) { page ->
            Box(
                modifier = Modifier.size(12.dp).clip(RoundedCornerShape(30.dp))
                    .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}