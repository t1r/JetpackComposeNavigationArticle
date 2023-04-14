package dev.jcnavigation.article.navigation.navdelegates

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.jcnavigation.article.navigation.screens.MainScreen
import dev.jcnavigation.article.ui.category.CategoryScreen
import dev.jcnavigation.article.ui.home.HomeScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
) {
    val onBackAction: () -> Unit = { navController.navigateUp() }

    NavHost(
        navController = navController,
        startDestination = MainScreen.Home.route,
    ) {
        composable(
            route = MainScreen.Home.route,
        ) {
            HomeScreen(
                goToCategory = {
                    navController.navigate(MainScreen.Category.buildRoute())
                }
            )
        }
        composable(
            route = MainScreen.Category.route,
        ) {
            CategoryScreen(
                onBackAction = onBackAction
            )
        }
    }
}