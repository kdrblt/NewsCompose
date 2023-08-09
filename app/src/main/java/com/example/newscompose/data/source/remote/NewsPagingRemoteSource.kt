package com.example.newscompose.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newscompose.data.api.NewsRemoteService
import com.example.newscompose.domain.dto.ArticleDto
import com.example.newscompose.domain.mapper.toDbItem
import com.example.newscompose.domain.mapper.toDomainModel
import com.example.newscompose.domain.repository.NewsLocalRepository
import javax.inject.Inject

class NewsPagingRemoteSource @Inject constructor(
    private val newsRemoteService: NewsRemoteService,
    private val newsLocalRepository: NewsLocalRepository
) : PagingSource<Int, ArticleDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = newsRemoteService.getRemoteNews(page = page)
            if (response.articles.isNotEmpty()) {
                newsLocalRepository.insertArticles(response.articles.toDomainModel().toDbItem())
            }
            LoadResult.Page(
                data = response.articles.toDomainModel(),
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.articles.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}
