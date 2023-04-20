package dev.jcnavigation.article.navigation.screens

import android.net.Uri
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
        ): String {
            val encodedTitle = Uri.encode(title)
            val encodedBottomTitle = bottomTitle?.let { Uri.encode(it) }
            return "$routeBody/$categoryId/$encodedTitle" + buildOptionalArgumentsString(
                BOTTOM_TITLE to encodedBottomTitle
            )
        }
    }
}

