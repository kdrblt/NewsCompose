package com.example.newscompose.domain.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleDto(
    val source: SourceDto?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
) : Parcelable {
    @Parcelize
    data class SourceDto(
        val id: String?,
        val name: String?
    ) : Parcelable
}
