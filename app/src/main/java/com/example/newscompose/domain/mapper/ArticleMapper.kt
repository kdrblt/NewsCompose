package com.example.newscompose.domain.mapper

import com.example.newscompose.data.model.response.NewsResponse
import com.example.newscompose.domain.dto.ArticleDto
import com.example.newscompose.extensions.toDomainDate


fun NewsResponse.Article.toDomainModel() = ArticleDto(
    source?.toDomainModel(),
    author, title, description, url, urlToImage, publishedAt?.toDomainDate(), content
)

fun NewsResponse.Source.toDomainModel() = ArticleDto.SourceDto(
    id, name
)

// for list
fun List<NewsResponse.Article>?.toDomainModel() =
    this?.map { it.toDomainModel() } ?: arrayListOf()