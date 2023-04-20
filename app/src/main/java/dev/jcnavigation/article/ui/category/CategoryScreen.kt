package dev.jcnavigation.article.ui.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CategoryScreen(
    argumentCategoryId: Long,
    argumentTitle: String,
    argumentBottomTitle: String?,
    onBackAction: () -> Unit,
    goToItemDetails: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val list by remember {
        derivedStateOf {
            buildList {
                repeat(6) {
                    val index = argumentCategoryId * 100L + it
                    add("Details #$index" to index)
                }
            }
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = "Category: $argumentTitle #${argumentCategoryId}")
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
            if (!argumentBottomTitle.isNullOrBlank()) Surface(
                modifier = Modifier.fillMaxWidth(),
                elevation = 4.dp,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    text = argumentBottomTitle,
                    textAlign = TextAlign.Center,
                )
            }
        }
    ) { pv ->
        Column(
            modifier = Modifier
                .padding(pv)
                .fillMaxSize(),
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(pv),
                columns = GridCells.Fixed(2),
                content = {
                    items(list) { item ->
                        Card(
                            modifier = Modifier
                                .clickable { goToItemDetails(item.second) }
                                .padding(16.dp),
                        ) {
                            Column {
                                Surface(
                                    modifier = Modifier
                                        .height(156.dp)
                                        .fillMaxWidth(),
                                    color = Color(0xFFE65100),
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