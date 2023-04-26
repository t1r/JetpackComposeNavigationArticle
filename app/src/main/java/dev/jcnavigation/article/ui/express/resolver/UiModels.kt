package dev.jcnavigation.article.ui.express.resolver

interface ExpressResolverView {
    data class UiState(
        val isResolved: Boolean = false,
    )
}