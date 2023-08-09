package com.example.newscompose.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newscompose.data.model.local.ArticleDbItem

@Dao
interface ArticleListDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<ArticleDbItem>)

    @Query("SELECT * FROM articleList")
    fun getArticleList(): PagingSource<Int, ArticleDbItem>

    @Insert
    fun addToArticleList(item: ArticleDbItem)

    @Query("DELETE FROM articleList")
    fun clearArticles()
}
