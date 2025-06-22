package com.jethings.study.presentation.view.screens.academy.components.tabSection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.presentation.ui.theme.customWhite2
import com.jethings.study.presentation.ui.theme.p_color1
import kotlinx.coroutines.launch


@Composable
fun TabSection(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {

    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = modifier
            .height(45.dp)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .clickable {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                }
                .drawBehind {
                    drawLine(
                        color = if (pagerState.currentPage == 0) p_color1 else customWhite2,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = if (pagerState.currentPage == 0) 2.dp.toPx() else 0.dp.toPx()
                    )
                }
                .padding(vertical = 8.dp, horizontal = 26.dp)
        ) {
            Text(
                text = "Post",
                style = TextStyle(fontSize = if (pagerState.currentPage == 0) 22.sp else 17.sp , fontWeight = FontWeight(if (pagerState.currentPage == 0) 600 else 500) , color = if (pagerState.currentPage == 0) p_color1 else customBlack3)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .clickable {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                }
                .drawBehind {
                    drawLine(
                        color = if (pagerState.currentPage == 1) p_color1 else customWhite2,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = if (pagerState.currentPage == 1) 2.dp.toPx() else 0.dp.toPx()
                    )
                }
                .padding(vertical = 8.dp, horizontal = 26.dp)

        ) {
            Text(
                text = "Training Program",
                style = TextStyle(fontSize = if (pagerState.currentPage == 1) 22.sp else 17.sp , fontWeight = FontWeight(if (pagerState.currentPage == 1) 600 else 500) , color = if (pagerState.currentPage == 1) p_color1 else customBlack3)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .clickable {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(2)
                    }
                }
                .drawBehind {
                    drawLine(
                        color = if (pagerState.currentPage == 2) p_color1 else customWhite2,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = if (pagerState.currentPage == 1) 2.dp.toPx() else 0.dp.toPx()
                    )
                }
                .padding(vertical = 8.dp, horizontal = 26.dp)

        ) {
            Text(
                text = "Info",
                style = TextStyle(fontSize = if (pagerState.currentPage == 2) 22.sp else 17.sp , fontWeight = FontWeight(if (pagerState.currentPage == 2) 600 else 500) , color = if (pagerState.currentPage == 2) p_color1 else customBlack3)
            )
        }
    }
}


@Preview
@Composable
private fun TabSection_prev() {
    TabSection(
        rememberPagerState {
            3
        }
    )
}