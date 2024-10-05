package com.amaral.gabriel.mathgame.navigation

sealed class Route(val route: String) {
    object FirstScreen: Route("firstScreen")
}