package dev.jcnavigation.article.ui.itemdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemDetailsScreen(
    vm: ItemDetailsViewModel,
    onBackAction: () -> Unit,
    onHomeClicked: () -> Unit,
    goToCart: () -> Unit,
    goForResult: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by vm.state.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = "Item Details Screen")
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
                        onClick = onHomeClicked,
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
        bottomBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 12.dp),
                    onClick = goToCart,
                ) {
                    Text(
                        text = "Go To Cart",
                    )
                }
            }
        },
    ) { pv ->
        Column(
            modifier = Modifier
                .padding(pv)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 24.dp),
                text = "Item Id #${uiState.itemId}",
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.weight(1F))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                backgroundColor = Color(0xFFECECEC),
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 12.dp, vertical = 12.dp),
                        text = uiState.result ?: "Empty Result",
                        fontSize = 18.sp,
                    )
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Button(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(vertical = 12.dp),
                            onClick = goForResult,
                        ) {
                            Text(
                                text = "Go For Result",
                            )
                        }
                    }
                }
            }
        }
    }
}