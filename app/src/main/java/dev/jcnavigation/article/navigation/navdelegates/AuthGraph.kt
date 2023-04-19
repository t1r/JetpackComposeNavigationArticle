package dev.jcnavigation.article.navigation.navdelegates

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import dev.jcnavigation.article.navigation.screens.MainScreen
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
                onBackAction = onBackAction,
                goToPasswordScreen = {
                    navController.navigate(MainScreen.AuthGraph.Password.buildRoute())
                },
            )
        }
        composable(MainScreen.AuthGraph.Password.route) {
            PasswordScreen(
                onBackAction = onBackAction,
                goToEmailScreen = {
                    navController.navigate(MainScreen.AuthGraph.Email.buildRoute())
                }
            )
        }
        composable(MainScreen.AuthGraph.Email.route) {
            EmailScreen(
                onBackAction = onBackAction,
                finishFlow = {
                    navController.popBackStack(route = MainScreen.AuthGraph.route, inclusive = true)
                }
            )
        }
    }
}