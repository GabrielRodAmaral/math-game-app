package com.amaral.gabriel.mathgame.navigation

sealed class Router(val route: String) {
    object HomeScreen: Router("homeScreen")
    object GameScreen: Router("gameScreen/{category}") {
        fun create(category: String) = "secondScreen/$category"
    }
}