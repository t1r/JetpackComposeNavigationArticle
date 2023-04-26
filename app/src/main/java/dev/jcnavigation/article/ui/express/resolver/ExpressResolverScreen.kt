package dev.jcnavigation.article.ui.express.resolver

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExpressResolverScreen(
    vm: ExpressResolverViewModel,
    onBackAction: () -> Unit,
    onNavigateAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by vm.state.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = "Express Resolver Screen")
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
        content = { pv ->
            if (!uiState.isResolved) Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(pv),
            ) {
                Button(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 12.dp),
                    onClick = { vm.resolve() },
                ) {
                    Text(
                        text = "Resolve",
                    )
                }
            }
        }
    )

    if (uiState.isResolved) {
        val onResolveAction by rememberUpdatedState { onNavigateAction() }
        LaunchedEffect(uiState) { onResolveAction() }
    }
}