package com.example.newscompose.domain.usecase

import androidx.paging.PagingData
import com.example.newscompose.domain.dto.ArticleDto
import com.example.newscompose.domain.repository.NewsLocalRepository
import com.example.newscompose.domain.repository.NewsRemoteRepository
import com.example.newscompose.ui.internetconnection.ConnectionState
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetRemoteNews @Inject constructor(
    private val newsRemoteRepository: NewsRemoteRepository,
    private val newsLocalRepository: NewsLocalRepository
) {
    data class Params(
        val connectionState: ConnectionState,
        var isFiltered: Boolean
    )

    operator fun invoke(params: Params): Flow<PagingData<ArticleDto>> {
        return if (params.connectionState is ConnectionState.Available) {
            newsRemoteRepository.getRemoteNews()
        } else {
            newsLocalRepository.getArticleList()
        }
    }
}
