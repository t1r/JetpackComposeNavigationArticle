package dev.jcnavigation.article.navigation

import androidx.navigation.NavController

internal fun <T : Any?> NavController.setDataForResult(
    key: String,
    value: T?,
) {
    previousBackStackEntry?.savedStateHandle?.set(key, value)
}

internal fun <T : Any?> NavController.getDataFromResultAndClear(key: String): T? {
    val result = currentBackStackEntry?.savedStateHandle?.get<T?>(key)
    currentBackStackEntry?.savedStateHandle?.remove<T?>(key)
    return result
}