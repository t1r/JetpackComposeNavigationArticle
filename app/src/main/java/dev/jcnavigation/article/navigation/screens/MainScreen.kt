package dev.jcnavigation.article.navigation.screens

import dev.jcnavigation.article.navigation.Destination

object MainScreen {
    object Home : Destination.WithoutArguments {
        override val routeBody: String = "home"
    }

    object Category : Destination.WithoutArguments {
        override val routeBody: String = "category"
    }
}

