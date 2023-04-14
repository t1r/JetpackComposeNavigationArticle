package dev.jcnavigation.article.navigation

interface Destination {
    // Это свойство мы будем использовать для
    // настройки навигации
    val route: String

    interface WithoutArguments : Destination {
        val routeBody: String
        override val route: String get() = routeBody

        // Этот метод мы будем использовать
        // для навигации
        fun buildRoute() = routeBody
    }

    interface WithArguments : Destination {
        val routeBody: String
        val suffix: String
        override val route: String get() = routeBody + suffix
    }
}