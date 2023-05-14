package dev.jcnavigation.article.navigation

import dev.jcnavigation.article.navigation.NavigationConst.CATEGORY_ID
import dev.jcnavigation.article.navigation.NavigationConst.ITEM_ID
import dev.jcnavigation.article.navigation.NavigationConst.TITLE

object DeeplinkConst {
    private const val SCHEME_AND_HOST = "mobileapp://jcn_article"
    const val CATEGORY = "$SCHEME_AND_HOST/catalog/{$CATEGORY_ID}/{$TITLE}"
    const val ITEM = "$SCHEME_AND_HOST/catalog/{$CATEGORY_ID}/{$TITLE}/item/{$ITEM_ID}"
    const val CART = "$SCHEME_AND_HOST/cart"
    const val EXPRESS_CART = "$SCHEME_AND_HOST/express_cart"
}