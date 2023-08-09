package com.example.newscompose.domain.mapper

import com.example.newscompose.data.model.local.ArticleDbItem
import com.example.newscompose.data.model.response.NewsResponse
import com.example.newscompose.domain.dto.ArticleDto

fun ArticleDto.toDbItem() = ArticleDbItem(
    id = 0,
    sourceId = source?.id.toString(),
    sourceName = source?.name.toString(),
    author = author.toString(),
    title = title.toString(),
    description = description.toString(),
    url = url.toString(),
    urlToImage = urlToImage.toString(),
    publishedAt = publishedAt.toString(),
    content = content.toString()
)

// for list
fun List<ArticleDto>?.toDbItem() =
    this?.map { it.toDbItem() } ?: arrayListOf()


fun ArticleDbItem.toArticleDto() = ArticleDto(
    ArticleDto.SourceDto(
        id = this.sourceId,
        name = this.sourceName
    ),
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)

// for list
fun List<ArticleDbItem>?.toArticleDto() =
    this?.map { it.toArticleDto() } ?: arrayListOf()
