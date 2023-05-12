package dev.jcnavigation.article.ui.authgraph

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthScreen(
    vm: AuthViewModel,
    onBackAction: () -> Unit,
    finishFlow: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by vm.state.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val (passwordFocusRequester, emailFocusRequester) = remember { FocusRequester.createRefs() }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = "Auth Screen")
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
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
            if (!uiState.isUsernameValid) Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp, bottom = 24.dp),
                onClick = vm::validateUsername,
            ) {
                if (uiState.isUsernameValidationInProgress) CircularProgressIndicator(
                    modifier = Modifier.size(12.dp),
                )
                else Text(text = "Continue")
            }
            TextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .focusRequester(passwordFocusRequester),
                value = uiState.password,
                enabled = uiState.isUsernameValid,
                label = {
                    Text(text = "Password")
                },
                onValueChange = vm::onPasswordChanged,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(onNext = { emailFocusRequester.requestFocus() }),
            )
            TextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .focusRequester(emailFocusRequester),
                value = uiState.email,
                enabled = uiState.isUsernameValid,
                label = {
                    Text(text = "Email")
                },
                onValueChange = vm::onEmailChanged,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            )
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                enabled = uiState.isValid(),
                onClick = finishFlow,
            ) {
                Text(
                    text = "Finish",
                )
            }
            Spacer(modifier = Modifier.weight(1F))
        }
    }
}