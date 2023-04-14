package dev.jcnavigation.article.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    goToCategory: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { pv ->
        Column(
            modifier = Modifier
                .padding(pv)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Text(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Home Screen",
            )
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = goToCategory,
            ) {
                Text(
                    text = "Go To Category",
                )
            }
            Spacer(modifier = Modifier.weight(1F))
        }
    }
}