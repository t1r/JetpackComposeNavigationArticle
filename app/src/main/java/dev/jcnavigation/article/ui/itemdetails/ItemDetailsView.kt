package dev.jcnavigation.article.ui.itemdetails

interface ItemDetailsView {
    data class UiState(
        val itemId: Long,
        val result: String? = null,
    )
}