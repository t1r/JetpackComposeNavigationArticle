package dev.jcnavigation.article.navigation.screens

import dev.jcnavigation.article.navigation.Destination
import dev.jcnavigation.article.navigation.NavigationConst.EXPRESS_FLOW_SCREEN_TYPE
import dev.jcnavigation.article.navigation.NavigationConst.ExpressScreenType
import dev.jcnavigation.article.navigation.NavigationConst.ITEM_ID

object ExpressScreen {
    object ExpressGraph : Destination.WithArguments {
        override val routeBody: String = "expressFlow"
        override val suffix: String = "/{$EXPRESS_FLOW_SCREEN_TYPE}"

        fun buildRoute(
            screenType: ExpressScreenType,
        ): String {
            return "${routeBody}/${screenType.id}"
        }

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