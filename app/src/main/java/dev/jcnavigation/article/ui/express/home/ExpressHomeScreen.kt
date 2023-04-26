package dev.jcnavigation.article.ui.express.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExpressHomeScreen(
    onBackAction: () -> Unit,
    goToCart: () -> Unit,
    goToItem: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
                            Icon(Icons.Filled.ShoppingCart, "Cart")
                        },
                        onClick = goToCart,
                    )
                },
            )
        },
        content = { pv ->
            Card(
                modifier = Modifier
                    .clickable { goToItem() }
                    .padding(pv)
                    .padding(16.dp),
            ) {
                Column {
                    Surface(
                        modifier = Modifier
                            .height(156.dp)
                            .fillMaxWidth(),
                        color = Color(0xFFD50000),
                        content = {},
                    )
                    Text(
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                            .align(Alignment.CenterHorizontally),
                        text = "Item",
                    )
                }
            }
        }
    )
}