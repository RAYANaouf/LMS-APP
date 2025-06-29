package com.jethings.study.presentation.view.screens.academy

import android.graphics.Paint.Align
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jethings.study.R
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.data.db.entities.entities.Post
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.TrainingProgramScreen
import com.jethings.study.presentation.nvgraph.academyOwnerList
import com.jethings.study.presentation.nvgraph.academyOwnerScreen
import com.jethings.study.presentation.nvgraph.academyScreen
import com.jethings.study.presentation.nvgraph.createTrainingProgram
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
import com.jethings.study.presentation.ui.theme.customWhite8
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.ui.theme.p_color3
import com.jethings.study.presentation.ui.theme.p_color4
import com.jethings.study.presentation.view.screens.academy.components.actionBtns.ActionBtnsSection
import com.jethings.study.presentation.view.screens.academy.components.analiticsSection.AnaliticsSection
import com.jethings.study.presentation.view.screens.academy.components.coverSection.CoverSection
import com.jethings.study.presentation.view.screens.academy.components.tabPage.PostPage
import com.jethings.study.presentation.view.screens.academy.components.tabPage.TrainingProgramPage
import com.jethings.study.presentation.view.screens.academy.components.tabSection.TabSection
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



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {


        Surface(
            color = background_color_0,
            shadowElevation = 4.dp,
            modifier = Modifier
                .zIndex(1f)
        ) {
            Column {


                CoverSection(
                    animatedVisibilityScope = animatedVisibilityScope,
                    academyId = academyId,
                    academy = academy,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(45.dp))

                AnaliticsSection(
                    academyId = academyId,
                    academy = academy,
                    onNavigate = {
                        onNavigate(it)
                    }
                )

                Spacer(modifier = Modifier.height(35.dp))

                ActionBtnsSection(
                    onNavigate = onNavigate
                )


                Spacer(modifier = Modifier.height(35.dp))

                TabSection(
                    pagerState = pagerState
                )


            }
        }

        HorizontalPager(
            beyondViewportPageCount = pagerState.pageCount,
            userScrollEnabled = false,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->
            when(page){
                0 ->{
                    PostPage(
                        postList = academy?.posts ?: emptyList()
                    )
                }
                1 ->{
                    TrainingProgramPage(
                        academyId = academyId,
                        academy = academy,
                        onEvent = onEvent,
                        onNavigate = onNavigate
                    )
                }
                2 ->{
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(750.dp)
                    ) {
                        Text(text = "Info")
                    }
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