package dev.jcnavigation.article.ui.authgraph

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UsernameScreen(
    vm: AuthViewModel,
    onBackAction: () -> Unit,
    goToPasswordScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by vm.state.collectAsState()
    var isValidationInProgress: Boolean by rememberSaveable { mutableStateOf(false) }
    val onValidateSuccessAction by rememberUpdatedState {
        isValidationInProgress = false
        goToPasswordScreen()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = "Username Screen")
                },
                navigationIcon = {
                    IconButton(
                        content = {
                            Icon(Icons.Filled.ArrowBack, "Back")
                        },
                        onClick = onBackAction,
                    )
                },
            )
        },
    ) { pv ->
        Column(
            modifier = Modifier
                .padding(pv)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.weight(1F))
            TextField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                value = uiState.username,
                label = {
                    Text(text = "Username")
                },
                onValueChange = vm::onUsernameChanged,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            )
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                onClick = {
                    vm.validateUsername()
                    isValidationInProgress = true
                },
            ) {
                if (uiState.isUsernameValidationInProgress) CircularProgressIndicator(
                    modifier = Modifier.size(12.dp),
                )
                else Text(text = "Continue")
            }
            Spacer(modifier = Modifier.weight(1F))
        }
    }

    LaunchedEffect(
        uiState.isUsernameValid,
        isValidationInProgress,
    ) {
        if (uiState.isUsernameValid && isValidationInProgress) onValidateSuccessAction()
    }
}