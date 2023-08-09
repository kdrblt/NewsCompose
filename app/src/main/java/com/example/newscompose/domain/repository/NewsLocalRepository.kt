package com.example.newscompose.domain.repository

import androidx.paging.PagingData
import com.example.newscompose.data.model.local.ArticleDbItem
import com.example.newscompose.domain.dto.ArticleDto
import kotlinx.coroutines.flow.Flow

interface NewsLocalRepository {
    suspend fun insertArticles(articles: List<ArticleDbItem>)
    suspend fun clearArticles()
    fun getArticleList(isDateFiltered: Boolean = false): Flow<PagingData<ArticleDto>>
    suspend fun addToArticleList(item: ArticleDbItem)
}
