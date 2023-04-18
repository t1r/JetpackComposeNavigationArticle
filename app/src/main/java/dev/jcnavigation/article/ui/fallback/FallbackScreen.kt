package dev.jcnavigation.article.ui.fallback

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
fun FallbackScreen(
    onBackAction: () -> Unit,
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
                text = "Something went wrong",
            )
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onBackAction,
            ) {
                Text(
                    text = "Back",
                )
            }
            Spacer(modifier = Modifier.weight(1F))
        }
    }
}