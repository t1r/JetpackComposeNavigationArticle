package dev.jcnavigation.article.ui.express.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@Composable
fun ExpressItemScreen(
    argumentItemId: Long,
    onBackAction: () -> Unit,
    goToItem: (Long) -> Unit,
    goToHome: () -> Unit,
    goToCart: () -> Unit,
    finishExpressFlow: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isServiceErrorShowing by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = "Express Home Screen")
                },
                navigationIcon = {
                    IconButton(
                        content = {
                            Icon(Icons.Filled.ArrowBack, "Back")
                        },
                        onClick = onBackAction,
                    )
                },
                actions = {
                    IconButton(
                        content = {
                            Icon(Icons.Filled.Home, "Home")
                        },
                        onClick = goToHome,
                    )
                    IconButton(
                        content = {
                            Icon(Icons.Filled.ShoppingCart, "Cart")
                        },
                        onClick = goToCart,
                    )
                },
            )
        },
        content = { pv ->
            Column(
                modifier = Modifier
                    .padding(pv)
                    .fillMaxSize(),
            ) {
                Spacer(modifier = Modifier.weight(1F))
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(vertical = 12.dp),
                        onClick = {
                            goToItem(argumentItemId + 1L)
                        },
                    ) {
                        Text(
                            text = "Go To Item #${argumentItemId + 1L}",
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 24.dp),
                    text = "Express Item #$argumentItemId",
                    fontSize = 30.sp,
                )
                Spacer(modifier = Modifier.weight(1F))
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(vertical = 12.dp),
                        onClick = {
                            isServiceErrorShowing = true
                        },
                    ) {
                        Text(
                            text = "Show Error",
                        )
                    }
                }
            }
        }
    )

    if (isServiceErrorShowing) ErrorServiceAlert(
        onButtonClicked = finishExpressFlow,
    )
}

@Composable
private fun ErrorServiceAlert(
    onButtonClicked: () -> Unit,
    onDismissRequest: (() -> Unit)? = null,
) {
    AlertDialog(
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        ),
        onDismissRequest = { onDismissRequest?.invoke() },
        title = {
            Text(
                "Something went wrong",
                modifier = Modifier.fillMaxWidth(),
            )
        },
        text = {
            Text(
                "Retry later",
                modifier = Modifier.fillMaxWidth(),
            )
        },
        buttons = {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                TextButton(
                    onClick = onButtonClicked,
                ) {
                    Text(
                        "GO TO MAIN SCREEN",
                    )
                }
            }
        }
    )
}