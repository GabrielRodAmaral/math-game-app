package com.amaral.gabriel.mathgame.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amaral.gabriel.mathgame.screen.HomeScreen
import com.amaral.gabriel.mathgame.screen.ResultScreen
import com.amaral.gabriel.mathgame.ui.screen.GameScreen
import com.amaral.gabriel.mathgame.ui.viewmodel.GameViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Router.HomeScreen.route) {
        composable(Router.HomeScreen.route) {
            HomeScreen(modifier, navController)
        }

        composable(Router.GameScreen.route) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")?.toString() ?: ""
            val viewModel: GameViewModel = viewModel(backStackEntry)
            GameScreen(modifier = modifier, navController = navController, category = category, viewModel = viewModel)
        }

        composable(Router.ResultScreen.route) { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
            ResultScreen(navController = navController, score = score)
        }
    }
}