package com.app.buzztoday.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.buzztoday.R
import com.app.buzztoday.domain.model.Article

@Composable
fun ArticleCard(
    article: Article,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Row(modifier = Modifier.clickable { onClick }) {

        ElevatedCard(
            colors = CardDefaults.elevatedCardColors(if (isSystemInDarkTheme()) Color(0xFF252525) else Color.White),
            elevation = CardDefaults.elevatedCardElevation(50.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column(
                verticalArrangement = Arrangement.SpaceAround, modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .height(85.dp)
            )
            {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.bodyMedium.copy(),
                    color = colorResource(id = R.color.titles),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Text(
                        text = article.source.name,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(id = R.color.body)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        tint = colorResource(id = R.color.body)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = article.publishedAt,
                        style = MaterialTheme.typography.labelSmall,
                        color = colorResource(id = R.color.body)
                    )
                }
            }
        }
    }
}