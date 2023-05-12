package dev.jcnavigation.article.ui.authgraph

interface AuthView {
    data class UiState(
        val username: String = "",
        val password: String = "",
        val email: String = "",
        val isUsernameValid: Boolean = false,
        val isUsernameValidationInProgress: Boolean = false,
    )
}

fun AuthView.UiState.isValid(): Boolean =
    isUsernameValid && password.isNotBlank() && email.isNotBlank()