package com.example.buzztoday.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.buzztoday.R
import com.example.buzztoday.domain.model.Article
import com.example.buzztoday.presentation.common.ArticleList
import com.example.buzztoday.presentation.common.Search
import com.example.buzztoday.presentation.navgraph.Route

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Screen(article: LazyPagingItems<Article>, navigate: (String) -> Unit) {
    /*val titles = remember {
        derivedStateOf {
            if (article.itemCount > 5) {
                article.itemSnapshotList.items.slice(IntRange(start = 0, endInclusive = 8))
                    .joinToString(separator = "\uD83C\uDF0D") { it.title }
            } else {
                ""
            }
        }
    }*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Row( verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "logo",
                modifier = Modifier
                    .size(100.dp)
                    .padding(horizontal = 5.dp)
            )

            Search(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "",
                readOnly = true,
                onValueChange = {},
                onSearch = {},
                onClick = { navigate(Route.SearchScreen.route) }
            )
        }

        /*Text(
            text = titles.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.ic)
        )*/

        ArticleList(modifier = Modifier.padding(horizontal = 25.dp), article = article, onClick = {
            navigate(Route.Detail.route)
        })
    }
}

