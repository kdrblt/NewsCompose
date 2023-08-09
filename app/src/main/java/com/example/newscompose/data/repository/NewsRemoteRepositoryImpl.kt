package com.example.newscompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newscompose.common.PagingHelper
import com.example.newscompose.data.api.NewsRemoteService
import com.example.newscompose.data.source.remote.NewsPagingRemoteSource
import com.example.newscompose.domain.dto.ArticleDto
import com.example.newscompose.domain.repository.NewsLocalRepository
import com.example.newscompose.domain.repository.NewsRemoteRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class NewsRemoteRepositoryImpl @Inject constructor(
    private val newsRemoteService: NewsRemoteService,
    private val newsLocalRepository: NewsLocalRepository
) : NewsRemoteRepository {
    override fun getRemoteNews(): Flow<PagingData<ArticleDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = PagingHelper.pageSize,
                prefetchDistance = 2
            ),
            pagingSourceFactory = {
                NewsPagingRemoteSource(newsRemoteService, newsLocalRepository)
            }
        ).flow
    }
}
