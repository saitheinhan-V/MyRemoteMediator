package com.my.remotemediator.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.my.remotemediator.MainViewModel
import com.my.remotemediator.data.ArticleVo
import kotlin.random.Random

@Composable
fun NewsListScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val newsFlow = viewModel.newsFlow.collectAsLazyPagingItems()

    newsFlow.apply {
        if (loadState.refresh is LoadState.Loading && loadState.source.refresh is LoadState.Loading && loadState.mediator?.refresh is LoadState.Loading) {
            //first time loading
            ShimmerListItem()
        }
        LazyColumn {
            items(newsFlow.itemCount) { index ->
                val article = newsFlow[index]
                article?.let {
                    NewsItem(article = it)
                }
            }

            //mediator
            item {
                if (loadState.mediator?.append is LoadState.Loading) {
                    LoadingItem()
                }
            }
            item {
                loadState.mediator?.let { state ->
                    if (state.append.endOfPaginationReached) {
                        Text(
                            text = "End of Page...",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NewsItem(article: ArticleVo) {
    Column(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = article.title)
            Text(text = article.description ?: "")
        }
        Spacer(
            modifier = Modifier
                .height(6.dp)
                .fillMaxWidth()
                .background(color = Color.LightGray)
        )
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp),
            strokeWidth = 2.dp
        )
    }
}

@Composable
fun ShimmerListItem() {
    val shimmerColors = listOf(
        MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
        MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
        MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
    )
    val transition = rememberInfiniteTransition(label = "")
    val anim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    val brush = Brush.linearGradient(
        start = Offset.Zero,
        end = Offset(x = anim.value, y = anim.value),
        colors = shimmerColors
    )

    Column {
        repeat(2) {
            ShimmerItem(
                brush = brush,
                fraction = 0.5f
            )
        }
        repeat(2) {
            ShimmerItem(
                brush = brush,
                fraction = 0.3f
            )
        }
        repeat(10) {
            ShimmerItem(
                brush = brush,
                fraction = 0.2f
            )
        }
    }
}

@Composable
fun ShimmerItem(
    modifier: Modifier = Modifier,
    brush: Brush,
    fraction: Float = Random.nextFloat().coerceIn(
        minimumValue = 0.5f,
        maximumValue = 9.0f
    )
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Spacer(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(brush = brush)
        )
        Spacer(
            modifier = modifier.padding(2.dp)
        )
        Spacer(
            modifier = modifier
                .fillMaxWidth(fraction)
                .height(6.dp)
                .clip(CircleShape)
                .background(brush = brush)
        )
    }
}

@Preview
@Composable
fun PreviewNewsItem() {
    val article = ArticleVo(
        id = 0,
        title = "Sai",
        url = "",
        description = "Iamsai"
    )
    Surface {
        NewsItem(
            article = article
        )
    }
}

@Preview
@Composable
fun PreviewShimmerList(){
    Surface {
        ShimmerListItem()
    }
}
