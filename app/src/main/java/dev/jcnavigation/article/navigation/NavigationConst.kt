package dev.jcnavigation.article.navigation

import dev.jcnavigation.article.navigation.screens.ExpressScreen

object NavigationConst {
    const val CATEGORY_ID = "categoryId"
    const val TITLE = "title"
    const val BOTTOM_TITLE = "bottomTitle"
    const val ITEM_ID = "itemId"
    const val ORDER_ID = "orderId"
    const val EXPRESS_FLOW_SCREEN_TYPE = "expressFlowScreenType"

    enum class ExpressScreenType(val id: Int) {
        Home(1),
        Cart(2),
    }

    val expressRoutes = setOf(
        ExpressScreen.ExpressGraph.Resolver.route,
        ExpressScreen.ExpressGraph.Home.route,
        ExpressScreen.ExpressGraph.Cart.route,
        ExpressScreen.ExpressGraph.Item.route,
    )
}