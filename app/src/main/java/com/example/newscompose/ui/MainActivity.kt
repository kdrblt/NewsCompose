package com.example.newscompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newscompose.navigation.NavGraph
import com.example.newscompose.ui.theme.NewsComposeTheme
import com.example.newscompose.ui.theme.PrimaryBlue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsComposeTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                Scaffold(
                    backgroundColor = PrimaryBlue
                ) {
                    NavGraph(navController = navController, paddingValues = it)
                }
            }
        }
    }
}
