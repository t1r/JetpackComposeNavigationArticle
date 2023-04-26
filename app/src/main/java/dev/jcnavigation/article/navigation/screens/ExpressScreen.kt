package dev.jcnavigation.article.navigation.screens

import dev.jcnavigation.article.navigation.Destination
import dev.jcnavigation.article.navigation.NavigationConst.ITEM_ID

object ExpressScreen {
    object ExpressGraph : Destination.WithoutArguments {
        override val routeBody: String = "expressFlow"

        object Resolver : Destination.WithoutArguments {
            override val routeBody: String = "expressResolver"
        }

        object Home : Destination.WithoutArguments {
            override val routeBody: String = "expressHome"
        }

        object Cart : Destination.WithoutArguments {
            override val routeBody: String = "expressCart"
        }

        object Item : Destination.WithArguments {
            override val routeBody: String = "expressItem"
            override val suffix: String = "/{$ITEM_ID}"

            fun buildRoute(
                itemId: Long,
            ): String {
                return "$routeBody/$itemId"
            }
        }
    }
}