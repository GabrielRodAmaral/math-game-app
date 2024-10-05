package com.amaral.gabriel.mathgame.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amaral.gabriel.mathgame.screen.FirstScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Route.FirstScreen.route) {
        composable(Route.FirstScreen.route) {
            FirstScreen(modifier, navController)
        }
    }
}