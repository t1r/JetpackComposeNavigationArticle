package dev.jcnavigation.article

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavDestination
import androidx.navigation.compose.rememberNavController
import dev.jcnavigation.article.navigation.navdelegates.MainNavigation
import dev.jcnavigation.article.ui.theme.JetpackComposeNavigationArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController().apply {
                addOnDestinationChangedListener { _, destination, _ ->
                    showScreenNameIfDebug(context, destination)
                }
            }
            JetpackComposeNavigationArticleTheme {
                MainNavigation(
                    navController = navController,
                )
            }
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