package dev.jcnavigation.article.navigation.screens

import dev.jcnavigation.article.navigation.Destination
import dev.jcnavigation.article.navigation.NavigationConst.BOTTOM_TITLE
import dev.jcnavigation.article.navigation.NavigationConst.CATEGORY_ID
import dev.jcnavigation.article.navigation.NavigationConst.TITLE
import dev.jcnavigation.article.navigation.NavigationHelper.buildOptionalArgumentsString

object MainScreen {
    object Home : Destination.WithoutArguments {
        override val routeBody: String = "home"
    }

    object Category : Destination.WithArguments {
        override val routeBody: String = "category"
        override val suffix: String = "/{$CATEGORY_ID}/{$TITLE}?$BOTTOM_TITLE={$BOTTOM_TITLE}"

        fun buildRoute(
            categoryId: Long,
            title: String,
            bottomTitle: String? = null,
        ): String = "$routeBody/$categoryId/$title" + buildOptionalArgumentsString(
            BOTTOM_TITLE to bottomTitle
        )
    }

    object AuthGraph : Destination.WithoutArguments {
        override val routeBody: String = "authFlow"

        object Username : Destination.WithoutArguments {
            override val routeBody: String = "username"
        }

        object Password : Destination.WithoutArguments {
            override val routeBody: String = "password"
        }

        object Email : Destination.WithoutArguments {
            override val routeBody: String = "email"
        }
    }
}

