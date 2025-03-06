package com.example.buzztoday.presentation.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.buzztoday.R
import com.example.buzztoday.presentation.common.ArticleList
import com.example.buzztoday.presentation.navgraph.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(state : SearchSt, event: (Event)->Unit,navigate:(String) -> Unit){
    Column(
        modifier = Modifier
            .padding(end = 15.dp)
            .statusBarsPadding()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                ) {

            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "logo",
                modifier = Modifier
                    .size(100.dp)
                    .padding(horizontal = 5.dp)
            )

            SearchBar(modifier = Modifier.padding(bottom = 10.dp),
                query = state.searchQuery,
                onQueryChange = { newQuery ->
                    event(Event.Update(newQuery))
                },
                onSearch = { event(Event.SearchNews) },
                active = false,
                onActiveChange = {},
                placeholder = { Text("Search news") }
            ) {
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(
                article = articles,
                onClick = {
                    navigate(Route.Detail.route)
                },
                modifier = Modifier
            )
        }
    }
}