package dev.jcnavigation.article.ui.takeresult

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun TakeResultScreen(
    onBackAction: () -> Unit,
    backWithResult: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = "Take Result Screen")
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
        bottomBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 12.dp),
                    onClick = { backWithResult("Result #${Random.nextInt()}") },
                ) {
                    Text(
                        text = "Back With Result",
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
                text = "Data",
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.weight(1F))
        }
    }
}