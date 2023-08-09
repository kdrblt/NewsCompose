package com.example.newscompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.example.newscompose.common.DateHelper
import com.example.newscompose.common.PagingHelper
import com.example.newscompose.data.model.local.ArticleDbItem
import com.example.newscompose.data.source.local.ArticleListDAO
import com.example.newscompose.domain.dto.ArticleDto
import com.example.newscompose.domain.mapper.toArticleDto
import com.example.newscompose.domain.repository.NewsLocalRepository
import com.example.newscompose.extensions.isBetweenDates
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class NewsLocalRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineContext,
    private val articleListDAO: ArticleListDAO
) : NewsLocalRepository {
    override suspend fun insertArticles(articles: List<ArticleDbItem>) {
        return withContext(ioDispatcher) {
            articleListDAO.insertArticles(articles)
        }
    }

    override suspend fun clearArticles() {
        return withContext(ioDispatcher) {
            articleListDAO.clearArticles()
        }
    }

    override fun getArticleList(isDateFiltered: Boolean): Flow<PagingData<ArticleDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = PagingHelper.pageSize,
                initialLoadSize = PagingHelper.pageSize
            ),
            pagingSourceFactory = {
                articleListDAO.getArticleList()
            }
        ).flow.map {
            it.map { articleDbItem ->
                articleDbItem.toArticleDto()
            }.filter {
                it.publishedAt?.isBetweenDates(
                    DateHelper.fromDate,
                    DateHelper.toDate
                ) == true
            }
        }
    }

    override suspend fun addToArticleList(item: ArticleDbItem) {
        return withContext(ioDispatcher) {
            articleListDAO.addToArticleList(item)
        }
    }
}
