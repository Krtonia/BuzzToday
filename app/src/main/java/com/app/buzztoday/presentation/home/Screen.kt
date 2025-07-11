package com.app.buzztoday.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.app.buzztoday.R
import com.app.buzztoday.domain.model.Article
import com.app.buzztoday.presentation.common.ArticleList
import com.app.buzztoday.presentation.common.Search
import com.app.buzztoday.presentation.nav.Route

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Screen(article: LazyPagingItems<Article>, navigate: (String) -> Unit) {
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

