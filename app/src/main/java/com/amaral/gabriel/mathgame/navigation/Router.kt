package com.amaral.gabriel.mathgame.navigation

sealed class Router(val route: String) {
    object HomeScreen: Router("homeScreen")
    object GameScreen: Router("gameScreen/{category}") {
        fun create(category: String) = "gameScreen/$category"
    }
    object ResultScreen: Router("resultScreen/{score}") {
        fun create(score: Int) = "resultScreen/$score"
    }
}