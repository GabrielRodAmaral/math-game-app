package com.amaral.gabriel.mathgame.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amaral.gabriel.mathgame.screen.HomeScreen
import com.amaral.gabriel.mathgame.screen.GameScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Router.HomeScreen.route) {
        composable(Router.HomeScreen.route) {
            HomeScreen(modifier, navController)
        }

        composable(Router.GameScreen.route) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")?.toString() ?: ""
            GameScreen(modifier, navController, category)
        }
    }
}