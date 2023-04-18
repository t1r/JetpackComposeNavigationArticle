package dev.jcnavigation.article.navigation.navdelegates

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.jcnavigation.article.navigation.NavigationConst.BOTTOM_TITLE
import dev.jcnavigation.article.navigation.NavigationConst.CATEGORY_ID
import dev.jcnavigation.article.navigation.NavigationConst.TITLE
import dev.jcnavigation.article.navigation.screens.MainScreen
import dev.jcnavigation.article.ui.category.CategoryScreen
import dev.jcnavigation.article.ui.fallback.FallbackScreen
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
                goToCategory = { categoryId, title, bottomTitle ->
                    navController.navigate(
                        MainScreen.Category.buildRoute(
                            categoryId = categoryId,
                            title = title,
                            bottomTitle = bottomTitle,
                        )
                    )
                }
            )
        }
        composable(
            route = MainScreen.Category.route,
            arguments = listOf(
                navArgument(CATEGORY_ID) { type = NavType.LongType },
                navArgument(TITLE) { type = NavType.StringType },
                navArgument(BOTTOM_TITLE) {
                    type = NavType.StringType
                    nullable = true
                },
            ),
        ) { entry ->
            val argumentCategoryId = entry.arguments?.getLong(CATEGORY_ID)
            val argumentTitle = entry.arguments?.getString(TITLE)
            val argumentBottomTitle = entry.arguments?.getString(BOTTOM_TITLE)

            if (argumentCategoryId != null && !argumentTitle.isNullOrBlank()) CategoryScreen(
                argumentCategoryId = argumentCategoryId,
                argumentTitle = argumentTitle,
                argumentBottomTitle = argumentBottomTitle,
                onBackAction = onBackAction,
            )
            else FallbackScreen(
                onBackAction = onBackAction,
            )
        }
    }
}