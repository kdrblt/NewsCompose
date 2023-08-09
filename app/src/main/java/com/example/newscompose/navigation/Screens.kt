package com.example.newscompose.navigation

sealed class Screens(val route: String) {
    object NewsScreen : Screens("news_screen")
    object NewDetailScreen : Screens("new_detail_screen")
}
