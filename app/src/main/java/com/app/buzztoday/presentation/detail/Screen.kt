package com.app.buzztoday.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.buzztoday.R
import com.app.buzztoday.domain.model.Article
import com.app.buzztoday.presentation.detail.topbar.TopBar

@Composable
fun Screen(
    article: Article,
    event: (Event) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    Column(modifier = Modifier.run {
        fillMaxSize()
            .statusBarsPadding()
    }) {
        TopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookMarkClick = {
                event(Event.SaveArticle)
            },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 5.dp,
                end = 5.dp,
                top = 5.dp
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(248.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(modifier = Modifier.padding(start = 10.dp),
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.title
                    )
                )
                Text(modifier = Modifier.padding(start = 10.dp),
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(
                        id = R.color.body
                    )
                )
            }
        }
    }
}