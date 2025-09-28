package com.example.projecttars.Members.UiElements

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoSwipeImageRow(
    imageList: List<Int>,
    autoSwipeDuration: Long = 3000L
) {
    val pagerState = rememberPagerState(pageCount = { imageList.size })
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(pagerState.currentPage) {
        delay(autoSwipeDuration)
        val nextPage = (pagerState.currentPage + 1) % imageList.size
        pagerState.animateScrollToPage(nextPage)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        HorizontalPager(state = pagerState) { page ->
            Image(
                painter = painterResource(id = imageList[page]),
                contentDescription = "Image $page",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    val prevPage =
                        if (pagerState.currentPage - 1 < 0) imageList.lastIndex
                        else pagerState.currentPage - 1
                    pagerState.animateScrollToPage(prevPage)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.6f)),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(8.dp)
        ) {
            Text("◀", color = Color.White)
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    val nextPage = (pagerState.currentPage + 1) % imageList.size
                    pagerState.animateScrollToPage(nextPage)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.6f)),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(8.dp)
        ) {
            Text("▶", color = Color.White)
        }
    }
}
