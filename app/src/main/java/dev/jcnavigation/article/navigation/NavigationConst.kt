package dev.jcnavigation.article.navigation

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
}