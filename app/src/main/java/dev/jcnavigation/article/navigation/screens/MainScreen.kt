package dev.jcnavigation.article.navigation.screens

import dev.jcnavigation.article.navigation.Destination
import dev.jcnavigation.article.navigation.NavigationConst.CATEGORY_ID
import dev.jcnavigation.article.navigation.NavigationConst.TITLE

object MainScreen {
    object Home : Destination.WithoutArguments {
        override val routeBody: String = "home"
    }

    object Category : Destination.WithArguments {
        override val routeBody: String = "category"
        override val suffix: String = "/{$CATEGORY_ID}/{$TITLE}"

        fun buildRoute(
            categoryId: Long,
            title: String,
        ): String = "$routeBody/$categoryId/$title"
    }
}

