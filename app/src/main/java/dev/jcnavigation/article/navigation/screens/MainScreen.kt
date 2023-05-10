package dev.jcnavigation.article.navigation.screens

import android.net.Uri
import dev.jcnavigation.article.navigation.Destination
import dev.jcnavigation.article.navigation.NavigationConst.BOTTOM_TITLE
import dev.jcnavigation.article.navigation.NavigationConst.CATEGORY_ID
import dev.jcnavigation.article.navigation.NavigationConst.ITEM_ID
import dev.jcnavigation.article.navigation.NavigationConst.ORDER_ID
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

    object ItemDetails : Destination.WithArguments {
        override val routeBody: String = "category"
        override val suffix: String = "/{$ITEM_ID}"

        fun buildRoute(
            itemId: Long,
        ): String {
            return "$routeBody/$itemId"
        }
    }

    object Cart : Destination.WithoutArguments {
        override val routeBody: String = "cart"
    }

    object UserProfile : Destination.WithoutArguments {
        override val routeBody: String = "userProfile"
    }

    object Order : Destination.WithArguments {
        override val routeBody: String = "order"
        override val suffix: String = "/{$ORDER_ID}"

        fun buildRoute(
            orderId: Long,
        ): String {
            return "$routeBody/$orderId"
        }
    }

    object TakeResult : Destination.WithoutArguments {
        override val routeBody: String = "takeResult"
    }
}

