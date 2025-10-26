package com.silkfinik.echolog.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.silkfinik.map.presentation.MapScreen

/**
 * Главный NavHost приложения.
 * Определяет все точки навигации и связи между ними.
 *
 */
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Map.route,
        modifier = modifier
    ) {
        // (:feature:map)
        composable(route = Screen.Map.route) {
            MapScreen(
                onFabClick = {
                    navController.navigate(Screen.EchoCreation.route)
                }
            )
        }

        // (:feature:echo_creation)
        composable(route = Screen.EchoCreation.route) {
            // TODO: Заменить на EchoCreationScreen()
            Text("Placeholder: Echo Creation Screen")
        }
    }
}