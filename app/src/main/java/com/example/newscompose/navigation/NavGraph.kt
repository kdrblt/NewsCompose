package com.example.newscompose.navigation

import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newscompose.domain.dto.ArticleDto
import com.example.newscompose.ui.newdetail.NewDetailScreen
import com.example.newscompose.ui.news.NewsScreen
import com.google.gson.Gson

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screens.NewsScreen.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(route = Screens.NewsScreen.route) {
            NewsScreen(
                navigateToNewDetail = {
                    val json = Uri.encode(Gson().toJson(it))
                    navController.navigate("${Screens.NewDetailScreen.route}/$json")
                }
            )
        }

        composable(
            route = "${Screens.NewDetailScreen.route}/{articleDto}",
            arguments = listOf(
                navArgument("articleDto") {
                    type = CustomNavType()
                }
            )
        ) {
            val articleDto = it.arguments?.getParcelable<ArticleDto>("articleDto")
            NewDetailScreen(article = articleDto) {
                navController.popBackStack()
            }
        }
    }
}
