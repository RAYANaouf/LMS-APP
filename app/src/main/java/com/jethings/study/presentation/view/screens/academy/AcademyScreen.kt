package com.jethings.study.presentation.view.screens.academy

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.academyOwnerList
import com.jethings.study.presentation.nvgraph.academyOwnerScreen
import com.jethings.study.presentation.nvgraph.academyScreen
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack2
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customBlack5
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.customWhite2
import com.jethings.study.presentation.ui.theme.customWhite3
import com.jethings.study.presentation.ui.theme.customWhite5
import com.jethings.study.presentation.ui.theme.customWhite7
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.ui.theme.p_color3
import com.jethings.study.presentation.ui.theme.p_color4
import com.jethings.study.presentation.view.screens.academy.events.AcademyEvent
import com.jethings.study.util.objects.TextStyles
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AcademyScreen(
    animatedVisibilityScope : AnimatedVisibilityScope,
    academyId : Int,
    academy   : Academy? = null,
    onEvent : (AcademyEvent , ()->Unit , ()->Unit)->Unit = {_,_,_->},
    onNavigate : (AppScreen)->Unit = {},
    modifier: Modifier = Modifier
) {

    /*** vars ***/
    val context = LocalContext.current


    val pagerState = rememberPagerState {
        3
    }
    val coroutineScope = rememberCoroutineScope()


    /*** effect  ***/
    LaunchedEffect(key1 = true) {
        onEvent(
            AcademyEvent.GetAcademyDetails(
                academy_id = academyId
            ),{
                //Toast.makeText(context , "Academy details refreshed. ${academy?.logo}" , Toast.LENGTH_SHORT).show()
            },{
                //Toast.makeText(context , "Error in getting Academy details." , Toast.LENGTH_SHORT).show()
            }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {


        Surface(
            color = background_color_0,
            shadowElevation = 2.dp,
            modifier = Modifier
                .zIndex(1f)
        ) {
            Column {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(customWhite2)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .align(Alignment.Center)
                        //.offset(y = 120.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .sharedElement(
                                    state = rememberSharedContentState(key = "Academy-${academyId}"),
                                    animatedVisibilityScope = animatedVisibilityScope
                                )
                                .size(150.dp)
                                .clip(CircleShape)
                                .background(customWhite5)
                        ) {
                            AsyncImage(
                                model = academy?.logo,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .height(40.dp)
                                .widthIn(max = 150.dp)
                        ) {
                            Text(
                                text = academy?.name  ?: "Loading..."     ,
                                style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(45.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(horizontal = 12.dp)
                ) {
                    Surface(
                        shadowElevation = 2.dp,
                        onClick = {
                            onNavigate(academyOwnerScreen(academyId))
                        },
                        border = BorderStroke(
                            width = 0.dp,
                            color = customWhite3
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)

                            ){
                                Text(
                                    text = "Owner",
                                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                                )
                            }
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)
                            ){
                                Text(
                                    text = if(academy?.owners?.size != null ) academy.owners.size.toString() else  "0",
                                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        shadowElevation = 2.dp,
                        onClick = {
                            onNavigate(academyOwnerScreen(academyId))
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)
                            ){
                                Text(
                                    text = "Employee",
                                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                                )
                            }
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)
                            ){
                                Text(
                                    text = "21",
                                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Surface(
                        shadowElevation = 2.dp,
                        shape = RoundedCornerShape(12.dp),
                        onClick = {
                            onNavigate(academyOwnerScreen(academyId))
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)
                            ){
                                Text(
                                    text = "Teacher",
                                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                                )
                            }
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)
                            ){
                                Text(
                                    text = "13",
                                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(horizontal = 12.dp)
                ) {
                    Surface(
                        shadowElevation = 2.dp,
                        onClick = {
                            academyOwnerScreen(academyId)
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .background(customWhite0)

                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)

                            ){
                                Text(
                                    text = "Student",
                                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                                )
                            }
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)
                            ){
                                Text(
                                    text = "126",
                                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(35.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Spacer(modifier = Modifier.width(16.dp))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(40.dp)
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(p_color1)
                            .clickable {

                            }
                    ) {
                        Text(
                            text = "Edit",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customWhite0)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(40.dp)
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(customWhite3)
                            .clickable {

                            }
                    ) {
                        Text(
                            text = "Analitics",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                }


                Spacer(modifier = Modifier.height(35.dp))


                Row(
                    modifier = Modifier
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
                            text = "Courses",
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
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (pagerState.currentPage == 0) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(750.dp)
                ) {
                    Text(text = "Courses")
                }
            } else if (pagerState.currentPage == 1){
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(horizontal = 16.dp)
                            .border(
                                width = 1.dp,
                                color = customWhite7,
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {

                    }
                }

            } else if (pagerState.currentPage == 2){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(280.dp)
                ) {
                    Text(text = "Info")
                }
            }
        }


    }


}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun AcademyScreen_prev() {

    SharedTransitionLayout {

        NavHost(navController = rememberNavController(), startDestination = academyScreen(5) ){
            composable<academyScreen> {
                AcademyScreen(
                    animatedVisibilityScope = this,
                    academyId = 1,
                    modifier = Modifier
                        .background(background_color_0)
                        .fillMaxHeight()
                )
            }
        }
    }

}