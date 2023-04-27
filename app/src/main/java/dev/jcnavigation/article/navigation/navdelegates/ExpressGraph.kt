package dev.jcnavigation.article.navigation.navdelegates

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import dev.jcnavigation.article.navigation.NavigationConst
import dev.jcnavigation.article.navigation.NavigationConst.EXPRESS_FLOW_SCREEN_TYPE
import dev.jcnavigation.article.navigation.NavigationConst.ExpressScreenType
import dev.jcnavigation.article.navigation.screens.ExpressScreen
import dev.jcnavigation.article.ui.express.cart.ExpressCartScreen
import dev.jcnavigation.article.ui.express.home.ExpressHomeScreen
import dev.jcnavigation.article.ui.express.item.ExpressItemScreen
import dev.jcnavigation.article.ui.express.resolver.ExpressResolverRepositoryImpl
import dev.jcnavigation.article.ui.express.resolver.ExpressResolverScreen
import dev.jcnavigation.article.ui.express.resolver.ExpressResolverViewModel
import dev.jcnavigation.article.ui.theme.JetpackComposeNavigationArticleExpressTheme

fun NavGraphBuilder.expressGraph(
    navController: NavController,
    onBackAction: () -> Unit,
) {
    val goToHome: () -> Unit = {
        navController.navigate(ExpressScreen.ExpressGraph.Home.buildRoute()) {
            popUpTo(ExpressScreen.ExpressGraph.route) {
                inclusive = false
            }
        }
    }
    val goToCart: () -> Unit = {
        navController.navigate(ExpressScreen.ExpressGraph.Cart.buildRoute())
    }
    val goToItem = { id: Long ->
        navController.navigate(ExpressScreen.ExpressGraph.Item.buildRoute(id))
    }
    val finishExpressFlow: () -> Unit = {
        navController.popBackStack(
            route = ExpressScreen.ExpressGraph.route,
            inclusive = true,
        )
    }

    navigation(
        route = ExpressScreen.ExpressGraph.route,
        startDestination = ExpressScreen.ExpressGraph.Resolver.route,
        arguments = listOf(
            navArgument(EXPRESS_FLOW_SCREEN_TYPE) { type = NavType.IntType },
        )
    ) {
        composable(ExpressScreen.ExpressGraph.Resolver.route) { entry ->
            val screenType = entry.arguments?.getInt(EXPRESS_FLOW_SCREEN_TYPE)
                ?: ExpressScreenType.Home.id

            JetpackComposeNavigationArticleExpressTheme {
                ExpressResolverScreen(
                    vm = viewModel(
                        factory = ExpressResolverViewModel.factory(
                            ExpressResolverRepositoryImpl
                        ),
                    ),
                    onBackAction = onBackAction,
                    onNavigateAction = {
                        navController.navigate(
                            when (screenType) {
                                ExpressScreenType.Cart.id -> ExpressScreen.ExpressGraph.Cart.buildRoute()
                                else -> ExpressScreen.ExpressGraph.Home.buildRoute()
                            }
                        ) {
                            popUpTo(ExpressScreen.ExpressGraph.Resolver.route) {
                                inclusive = true
                            }
                        }
                    },
                )
            }
        }
        composable(ExpressScreen.ExpressGraph.Home.route) {
            JetpackComposeNavigationArticleExpressTheme {
                ExpressHomeScreen(
                    onBackAction = onBackAction,
                    goToCart = goToCart,
                    goToItem = { goToItem(0L) },
                )
            }
        }
        composable(ExpressScreen.ExpressGraph.Cart.route) {
            JetpackComposeNavigationArticleExpressTheme {
                ExpressCartScreen(
                    onBackAction = onBackAction,
                    goToHome = goToHome,
                )
            }
        }
        composable(
            ExpressScreen.ExpressGraph.Item.route,
            arguments = listOf(
                navArgument(NavigationConst.ITEM_ID) { type = NavType.LongType },
            ),
        ) { entry ->
            val argumentItemId = entry.arguments?.getLong(NavigationConst.ITEM_ID) ?: 0L
            JetpackComposeNavigationArticleExpressTheme {
                ExpressItemScreen(
                    argumentItemId = argumentItemId,
                    onBackAction = onBackAction,
                    goToItem = goToItem,
                    goToCart = goToCart,
                    goToHome = goToHome,
                    finishExpressFlow = finishExpressFlow,
                )
            }
        }
    }
}