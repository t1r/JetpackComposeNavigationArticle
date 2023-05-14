package dev.jcnavigation.article.navigation.navdelegates

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import dev.jcnavigation.article.navigation.DeeplinkConst
import dev.jcnavigation.article.navigation.NavigationConst.BOTTOM_TITLE
import dev.jcnavigation.article.navigation.NavigationConst.CATEGORY_ID
import dev.jcnavigation.article.navigation.NavigationConst.ExpressScreenType
import dev.jcnavigation.article.navigation.NavigationConst.ITEM_ID
import dev.jcnavigation.article.navigation.NavigationConst.ORDER_ID
import dev.jcnavigation.article.navigation.NavigationConst.RESULT
import dev.jcnavigation.article.navigation.NavigationConst.TITLE
import dev.jcnavigation.article.navigation.getDataFromResultAndClear
import dev.jcnavigation.article.navigation.screens.ExpressScreen
import dev.jcnavigation.article.navigation.screens.MainScreen
import dev.jcnavigation.article.navigation.setDataForResult
import dev.jcnavigation.article.ui.cart.CartScreen
import dev.jcnavigation.article.ui.category.CategoryScreen
import dev.jcnavigation.article.ui.fallback.FallbackScreen
import dev.jcnavigation.article.ui.home.HomeScreen
import dev.jcnavigation.article.ui.itemdetails.ItemDetailsScreen
import dev.jcnavigation.article.ui.itemdetails.ItemDetailsViewModel
import dev.jcnavigation.article.ui.order.OrderScreen
import dev.jcnavigation.article.ui.takeresult.TakeResultScreen
import dev.jcnavigation.article.ui.userprofile.UserProfileScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    isExpanded: Boolean,
) {
    val onBackAction: () -> Unit = { navController.navigateUp() }
    val goToHomeAction: () -> Unit = {
        navController.popBackStack(route = MainScreen.Home.route, inclusive = false)
    }
    val goToCart: () -> Unit = {
        navController.navigate(MainScreen.Cart.buildRoute())
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
                    navController.navigate(ExpressScreen.ExpressGraph.buildRoute(ExpressScreenType.Home))
                },
                goToCart = goToCart,
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
            deepLinks = listOf(
                navDeepLink { uriPattern = DeeplinkConst.CATEGORY },
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
                onHomeClicked = goToHomeAction,
                goToCart = goToCart,
            )
            else FallbackScreen(
                onBackAction = onBackAction,
            )
        }

        authGraph(
            navController = navController,
            onBackAction = onBackAction,
            isExpanded = isExpanded,
        )

        composable(
            route = MainScreen.ItemDetails.route,
            arguments = listOf(
                navArgument(ITEM_ID) { type = NavType.LongType },
            ),
        ) { entry ->
            val argumentItemId = entry.arguments?.getLong(ITEM_ID)
            val vm: ItemDetailsViewModel? = argumentItemId?.let {
                viewModel(factory = ItemDetailsViewModel.factory(it))
            }

            navController.getDataFromResultAndClear<String>(RESULT)
                ?.let { vm?.setResult(it) }

            if (vm != null) ItemDetailsScreen(
                vm = vm,
                onBackAction = onBackAction,
                onHomeClicked = goToHomeAction,
                goToCart = goToCart,
                goForResult = {
                    navController.navigate(MainScreen.TakeResult.buildRoute())
                },
            )
            else FallbackScreen(
                onBackAction = onBackAction,
            )
        }

        composable(
            route = MainScreen.Cart.route,
            deepLinks = listOf(
                navDeepLink { uriPattern = DeeplinkConst.CART },
            ),
        ) {
            CartScreen(
                onBackAction = onBackAction,
                onBuyClicked = { orderId ->
                    navController.popBackStack(MainScreen.Home.route, false)
                    navController.navigate(MainScreen.UserProfile.buildRoute())
                    navController.navigate(MainScreen.Order.buildRoute(orderId))
                },
                goToExpressCart = {
                    navController.navigate(ExpressScreen.ExpressGraph.buildRoute(ExpressScreenType.Cart))
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

        composable(
            route = MainScreen.TakeResult.route,
        ) {
            TakeResultScreen(
                onBackAction = onBackAction,
                backWithResult = { result ->
                    navController.setDataForResult(RESULT, result)
                    onBackAction()
                },
            )
        }

        composable(
            route = "itemDetailsDeepLink",
            arguments = listOf(
                navArgument(ITEM_ID) { type = NavType.LongType },
                navArgument(CATEGORY_ID) { type = NavType.LongType },
                navArgument(TITLE) { type = NavType.StringType },
                navArgument(BOTTOM_TITLE) {
                    type = NavType.StringType
                    nullable = true
                },
            ),
            deepLinks = listOf(
                navDeepLink { uriPattern = DeeplinkConst.ITEM },
            ),
        ) { entry ->
            val argumentItemId = entry.arguments?.getLong(ITEM_ID)
            val argumentCategoryId = entry.arguments?.getLong(CATEGORY_ID)
            val argumentTitle = entry.arguments?.getString(TITLE)
            val argumentBottomTitle = entry.arguments?.getString(BOTTOM_TITLE)

            if (argumentItemId == null || argumentCategoryId == null || argumentTitle.isNullOrBlank()) {
                FallbackScreen(onBackAction = onBackAction)
                return@composable
            }

            val navigate by rememberUpdatedState {
                navController.popBackStack("itemDetailsDeepLink", true)
                navController.navigate(
                    MainScreen.Category.buildRoute(
                        categoryId = argumentCategoryId,
                        title = argumentTitle,
                        bottomTitle = argumentBottomTitle,
                    )
                )
                navController.navigate(MainScreen.ItemDetails.buildRoute(argumentItemId))
            }
            LaunchedEffect(Unit) {
                navigate()
            }
        }
    }
}