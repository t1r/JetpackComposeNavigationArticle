package dev.jcnavigation.article.ui.express.resolver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class ExpressResolverViewModel(
    private val repository: ExpressResolverRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(ExpressResolverView.UiState())
    val state: StateFlow<ExpressResolverView.UiState> = _state

    init {
        repository
            .isResolved
            .onEach { value ->
                _state.update { state ->
                    state.copy(
                        isResolved = value,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun resolve() {
        repository.resolve()
    }

    companion object {
        fun factory(
            repository: ExpressResolverRepository,
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer { ExpressResolverViewModel(repository) }
        }
    }
}