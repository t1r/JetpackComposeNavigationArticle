package dev.jcnavigation.article.navigation.navdelegates

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.jcnavigation.article.navigation.NavigationConst.BOTTOM_TITLE
import dev.jcnavigation.article.navigation.NavigationConst.CATEGORY_ID
import dev.jcnavigation.article.navigation.NavigationConst.ITEM_ID
import dev.jcnavigation.article.navigation.NavigationConst.ORDER_ID
import dev.jcnavigation.article.navigation.NavigationConst.TITLE
import dev.jcnavigation.article.navigation.screens.ExpressScreen
import dev.jcnavigation.article.navigation.screens.MainScreen
import dev.jcnavigation.article.ui.cart.CartScreen
import dev.jcnavigation.article.ui.category.CategoryScreen
import dev.jcnavigation.article.ui.fallback.FallbackScreen
import dev.jcnavigation.article.ui.home.HomeScreen
import dev.jcnavigation.article.ui.itemdetails.ItemDetailsScreen
import dev.jcnavigation.article.ui.order.OrderScreen
import dev.jcnavigation.article.ui.userprofile.UserProfileScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
) {
    val onBackAction: () -> Unit = { navController.navigateUp() }
    val goToHomeAction: () -> Unit = {
        navController.popBackStack(route = MainScreen.Home.route, inclusive = false)
    }

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
                },
                goToAuth = {
                    navController.navigate(MainScreen.AuthGraph.buildRoute())
                },
                goToExpress = {
                    navController.navigate(ExpressScreen.ExpressGraph.buildRoute())
                },
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
                goToItemDetails = { detailsId ->
                    navController.navigate(MainScreen.ItemDetails.buildRoute(detailsId))
                },
            )
            else FallbackScreen(
                onBackAction = onBackAction,
            )
        }

        authGraph(
            navController = navController,
            onBackAction = onBackAction,
        )

        composable(
            route = MainScreen.ItemDetails.route,
            arguments = listOf(
                navArgument(ITEM_ID) { type = NavType.LongType },
            ),
        ) { entry ->
            val argumentItemId = entry.arguments?.getLong(ITEM_ID)

            if (argumentItemId != null) ItemDetailsScreen(
                itemId = argumentItemId,
                onBackAction = onBackAction,
                onHomeClicked = goToHomeAction,
                goToCartAction = {
                    navController.navigate(MainScreen.Cart.buildRoute())
                },
            )
            else FallbackScreen(
                onBackAction = onBackAction,
            )
        }

        composable(
            route = MainScreen.Cart.route,
        ) {
            CartScreen(
                onBackAction = onBackAction,
                onBuyClicked = { orderId ->
                    navController.popBackStack(MainScreen.Home.route, false)
                    navController.navigate(MainScreen.UserProfile.buildRoute())
                    navController.navigate(MainScreen.Order.buildRoute(orderId))
                },
            )
        }

        composable(
            route = MainScreen.UserProfile.route,
        ) {
            UserProfileScreen(
                onBackAction = onBackAction,
            )
        }

        composable(
            route = MainScreen.Order.route,
            arguments = listOf(
                navArgument(ORDER_ID) { type = NavType.LongType },
            ),
        ) { entry ->
            val argumentOrderId = entry.arguments?.getLong(ORDER_ID)

            if (argumentOrderId != null) OrderScreen(
                orderId = argumentOrderId,
                onBackAction = onBackAction,
                onHomeClicked = goToHomeAction,
            )
            else FallbackScreen(
                onBackAction = onBackAction,
            )
        }

        expressGraph(
            navController = navController,
            onBackAction = onBackAction,
        )
    }
}