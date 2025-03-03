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
    private var totalNewsCount = 0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val Response = api.getNews(
                searchQuery = searchQuery,
                sources = sources,
                page = page
            )
            totalNewsCount += Response.articles.size
            val articles = Response.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == Response.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }
}