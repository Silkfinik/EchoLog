package com.silkfinik.echolog.navigation

/**
 * Sealed class для определения type-safe маршрутов в приложении.
 */
sealed class Screen(val route: String) {
    data object Map : Screen("map_screen")
    data object EchoCreation : Screen("echo_creation_screen")
}