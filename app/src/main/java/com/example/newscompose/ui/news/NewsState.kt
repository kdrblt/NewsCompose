package com.example.newscompose.ui.news

data class NewsState constructor(
    var isFiltered: Boolean = false,
    var topBarFilteredText: String = ""
)
