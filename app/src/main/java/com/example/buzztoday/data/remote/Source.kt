package com.example.buzztoday.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.buzztoday.domain.model.Article
import java.lang.Exception

class Source(
    private val api: NewsApi,
    private var searchQuery: String,
    private val sources: String
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val response = api.Searches(
                searchQuery = searchQuery,
                sources = sources,
                page = page
            )

            val articles = response.articles.distinctBy { it.title }

            // Check if we've reached the end based on current page and results
            val hasMoreData = articles.isNotEmpty() &&
                    (response.totalResults > (page * articles.size))

            LoadResult.Page(
                data = articles,
                nextKey = if (hasMoreData) page + 1 else null,
                prevKey = if (page > 1) page - 1 else null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }
}