package com.amaral.gabriel.mathgame.navigation

sealed class Route(val route: String) {
    object FirstScreen: Route("firstScreen")
    object SecondScreen: Route("secondScreen/{category}") {
        fun create(category: String) = "secondScreen/$category"
    }
}