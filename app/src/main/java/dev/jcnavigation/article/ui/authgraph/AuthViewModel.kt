package dev.jcnavigation.article.ui.authgraph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jcnavigation.article.ui.authgraph.AuthView.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    fun onUsernameChanged(text: String) {
        _state.update { state ->
            state.copy(
                username = text,
                isUsernameValid = false,
            )
        }
    }

    fun validateUsername() {
        if (state.value.isUsernameValidationInProgress) return
        viewModelScope.launch {
            _state.update { state ->
                state.copy(isUsernameValidationInProgress = true)
            }
            delay(4000)
            _state.update { state ->
                state.copy(
                    isUsernameValid = state.username.isNotBlank(),
                    isUsernameValidationInProgress = false,
                )
            }
        }
    }

    fun onPasswordChanged(text: String) {
        _state.update { state ->
            state.copy(password = text)
        }
    }

    fun onEmailChanged(text: String) {
        _state.update { state ->
            state.copy(email = text)
        }
    }
}