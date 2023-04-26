package dev.jcnavigation.article.navigation.navdelegates

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import dev.jcnavigation.article.navigation.NavigationConst
import dev.jcnavigation.article.navigation.screens.ExpressScreen
import dev.jcnavigation.article.ui.express.cart.ExpressCartScreen
import dev.jcnavigation.article.ui.express.home.ExpressHomeScreen
import dev.jcnavigation.article.ui.express.item.ExpressItemScreen
import dev.jcnavigation.article.ui.express.resolver.ExpressResolverRepositoryImpl
import dev.jcnavigation.article.ui.express.resolver.ExpressResolverScreen
import dev.jcnavigation.article.ui.express.resolver.ExpressResolverViewModel

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

    navigation(
        route = ExpressScreen.ExpressGraph.route,
        startDestination = ExpressScreen.ExpressGraph.Resolver.route,
    ) {
        composable(ExpressScreen.ExpressGraph.Resolver.route) {
            ExpressResolverScreen(
                vm = viewModel(
                    factory = ExpressResolverViewModel.factory(
                        ExpressResolverRepositoryImpl
                    ),
                ),
                onBackAction = onBackAction,
                onNavigateAction = {
                    navController.navigate(ExpressScreen.ExpressGraph.Home.buildRoute()) {
                        popUpTo(ExpressScreen.ExpressGraph.Resolver.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }
        composable(ExpressScreen.ExpressGraph.Home.route) {
            ExpressHomeScreen(
                onBackAction = onBackAction,
                goToCart = goToCart,
                goToItem = { goToItem(0L) },
            )
        }
        composable(ExpressScreen.ExpressGraph.Cart.route) {
            ExpressCartScreen(
                onBackAction = onBackAction,
                goToHome = goToHome,
            )
        }
        composable(
            ExpressScreen.ExpressGraph.Item.route,
            arguments = listOf(
                navArgument(NavigationConst.ITEM_ID) { type = NavType.LongType },
            ),
        ) { entry ->
            val argumentItemId = entry.arguments?.getLong(NavigationConst.ITEM_ID) ?: 0L
            ExpressItemScreen(
                argumentItemId = argumentItemId,
                onBackAction = onBackAction,
                goToItem = goToItem,
                goToCart = goToCart,
                goToHome = goToHome,
            )
        }
    }
}