package dev.jcnavigation.article.ui.express.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExpressCartScreen(
    onBackAction: () -> Unit,
    goToHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = "Express Cart Screen")
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
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 24.dp),
                    text = "Express Cart",
                    fontSize = 30.sp,
                )
                Spacer(modifier = Modifier.weight(1F))
            }
        }
    )
}