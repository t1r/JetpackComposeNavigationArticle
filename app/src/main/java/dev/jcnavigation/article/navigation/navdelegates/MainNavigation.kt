package dev.jcnavigation.article.navigation.navdelegates

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.jcnavigation.article.navigation.NavigationConst.CATEGORY_ID
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
                    navController.navigate(MainScreen.Category.buildRoute(117))
                }
            )
        }
        composable(
            route = MainScreen.Category.route,
            arguments = listOf(
                navArgument(CATEGORY_ID) { type = NavType.LongType },
            ),
        ) { entry ->
            val argumentCategoryId = entry.arguments?.getLong(CATEGORY_ID)!!
            CategoryScreen(
                onBackAction = onBackAction
            )
        }
    }
}