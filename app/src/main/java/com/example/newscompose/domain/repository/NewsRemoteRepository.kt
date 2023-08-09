package com.example.newscompose.domain.repository

import androidx.paging.PagingData
import com.example.newscompose.domain.dto.ArticleDto
import kotlinx.coroutines.flow.Flow

interface NewsRemoteRepository {
    fun getRemoteNews(): Flow<PagingData<ArticleDto>>
}
