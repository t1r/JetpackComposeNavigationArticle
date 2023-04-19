package dev.jcnavigation.article.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    goToCategory: (Long, String, String?) -> Unit,
    goToAuth: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val list by remember {
        derivedStateOf {
            listOf(
                "First/Screen" to "bottom title 1",
                "Second%Screen" to null,
                "Third^Screen" to "bottom title 1",
                "4 ! @ # $ % ^ & * ()_ +" to null,
            )
        }
    }
    Scaffold(
        modifier = modifier,
        bottomBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 12.dp),
                    onClick = goToAuth,
                ) {
                    Text(
                        text = "Go To Auth",
                    )
                }
            }
        }
    ) { pv ->
        Column(
            modifier = Modifier
                .padding(pv)
                .fillMaxSize(),
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                content = {
                    itemsIndexed(list) { index, item ->
                        Card(
                            modifier = Modifier
                                .clickable { goToCategory(index.toLong(), item.first, item.second) }
                                .padding(16.dp),
                        ) {
                            Column {
                                Surface(
                                    modifier = Modifier
                                        .height(156.dp)
                                        .fillMaxWidth(),
                                    color = darkColors().secondaryVariant,
                                    content = {},
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(bottom = 12.dp)
                                        .align(Alignment.CenterHorizontally),
                                    text = item.first,
                                )
                            }
                        }
                    }
                },
            )
        }
    }
}