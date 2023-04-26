package dev.jcnavigation.article

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavDestination
import androidx.navigation.compose.rememberNavController
import dev.jcnavigation.article.navigation.NavigationConst.expressRoutes
import dev.jcnavigation.article.navigation.navdelegates.MainNavigation
import dev.jcnavigation.article.ui.theme.JetpackComposeNavigationArticleTheme
import dev.jcnavigation.article.ui.theme.expressPrimaryColor
import dev.jcnavigation.article.ui.theme.primaryColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isSystemInDarkTheme = isSystemInDarkTheme()
            val navController = rememberNavController().apply {
                addOnDestinationChangedListener { _, destination, _ ->
                    showScreenNameIfDebug(context, destination)
                    resolveExpress(destination, isSystemInDarkTheme)
                }
            }
            JetpackComposeNavigationArticleTheme {
                MainNavigation(
                    navController = navController,
                )
            }
        }
    }

    private fun resolveExpress(
        destination: NavDestination,
        isSystemInDarkTheme: Boolean,
    ) {
        window?.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor =
                if (expressRoutes.contains(destination.route)) expressPrimaryColor(
                    isSystemInDarkTheme
                ).toArgb()
                else primaryColor(isSystemInDarkTheme).toArgb()
        }
    }
}

private fun showScreenNameIfDebug(
    context: Context,
    destination: NavDestination,
) {
    if (BuildConfig.DEBUG) Toast.makeText(
        context,
        destination.route ?: "unknown",
        Toast.LENGTH_SHORT,
    ).show()
}