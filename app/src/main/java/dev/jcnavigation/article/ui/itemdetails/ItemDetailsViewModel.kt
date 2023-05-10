package dev.jcnavigation.article.ui.itemdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ItemDetailsViewModel(
    itemId: Long,
) : ViewModel() {
    private val _state = MutableStateFlow(ItemDetailsView.UiState(itemId))
    val state: StateFlow<ItemDetailsView.UiState> = _state

    fun setResult(result: String) {
        _state.update { state ->
            state.copy(
                result = result,
            )
        }
    }

    companion object {
        fun factory(
            itemId: Long,
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer { ItemDetailsViewModel(itemId) }
        }
    }
}