package dev.jcnavigation.article.navigation.navdelegates

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import dev.jcnavigation.article.navigation.screens.MainScreen
import dev.jcnavigation.article.ui.authgraph.AuthViewModel
import dev.jcnavigation.article.ui.authgraph.EmailScreen
import dev.jcnavigation.article.ui.authgraph.PasswordScreen
import dev.jcnavigation.article.ui.authgraph.UsernameScreen

fun NavGraphBuilder.authGraph(
    navController: NavController,
    onBackAction: () -> Unit,
) {
    navigation(
        route = MainScreen.AuthGraph.route,
        startDestination = MainScreen.AuthGraph.Username.route,
    ) {
        composable(MainScreen.AuthGraph.Username.route) {
            UsernameScreen(
                vm = getAuthViewModel(navController = navController, backStackEntry = it),
                onBackAction = onBackAction,
                goToPasswordScreen = {
                    navController.navigate(MainScreen.AuthGraph.Password.buildRoute())
                },
            )
        }
        composable(MainScreen.AuthGraph.Password.route) {
            PasswordScreen(
                vm = getAuthViewModel(navController = navController, backStackEntry = it),
                onBackAction = onBackAction,
                goToEmailScreen = {
                    navController.navigate(MainScreen.AuthGraph.Email.buildRoute())
                }
            )
        }
        composable(MainScreen.AuthGraph.Email.route) {
            EmailScreen(
                vm = getAuthViewModel(navController = navController, backStackEntry = it),
                onBackAction = onBackAction,
                finishFlow = {
                    navController.popBackStack(route = MainScreen.AuthGraph.route, inclusive = true)
                }
            )
        }
    }
}

@Composable
private fun getAuthViewModel(
    navController: NavController,
    backStackEntry: NavBackStackEntry,
): AuthViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(MainScreen.AuthGraph.route)
    }
    return viewModel(parentEntry)
}