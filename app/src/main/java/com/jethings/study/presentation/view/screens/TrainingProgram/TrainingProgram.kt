package com.jethings.study.presentation.view.screens.TrainingProgram

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.jethings.study.R
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.data.db.entities.entities.Post
import com.jethings.study.data.db.entities.entities.TrainingProgram
import com.jethings.study.presentation.nvgraph.TrainingProgramScreen
import com.jethings.study.presentation.nvgraph.homeScreen
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack0
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.view.screens.TrainingProgram.component.AfterThePogramSection.AfterTheProgramSection
import com.jethings.study.presentation.view.screens.TrainingProgram.component.budget.BudgetSection
import com.jethings.study.presentation.view.screens.TrainingProgram.component.cover.Cover
import com.jethings.study.presentation.view.screens.TrainingProgram.component.desc.Desc
import com.jethings.study.presentation.view.screens.TrainingProgram.component.forWho.ForWhoSection
import com.jethings.study.presentation.view.screens.TrainingProgram.component.name.TrainingName
import com.jethings.study.presentation.view.screens.TrainingProgram.component.whatWeWillLearn.WhatWeWillLearn
import com.jethings.study.presentation.view.screens.TrainingProgram.event.CourseEvent
import com.jethings.study.presentation.view.screens.home.HomeScreen
import com.jethings.study.util.objects.TextStyles

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.trainingProgramScreen(
    animatedVisibilityScope : AnimatedVisibilityScope,
    onEvent                 : (CourseEvent , ()->Unit , ()->Unit )->Unit = {_,_,_->},
    trainingProgram         : TrainingProgram,
    modifier                : Modifier = Modifier
) {


    LazyColumn(
        modifier = modifier
    ) {

        item{
            Cover(
                animatedVisibilityScope = animatedVisibilityScope,
                trainingProgram = trainingProgram
            )
            Spacer(modifier = Modifier.height(18.dp))
            TrainingName(
                animatedVisibilityScope = animatedVisibilityScope,
                trainingProgram = trainingProgram
            )
            Spacer(modifier = Modifier.height(24.dp))
            BudgetSection(
                trainingProgram = trainingProgram
            )
            Spacer(modifier = Modifier.height(24.dp))
            Desc(
                trainingProgram = trainingProgram
            )
            Spacer(modifier = Modifier.height(28.dp))
        }

        item {
            ForWhoSection(
                trainingProgram = trainingProgram
            )

            Spacer(modifier = Modifier.height(28.dp))

            WhatWeWillLearn(
                trainingProgram = trainingProgram
            )

            Spacer(modifier = Modifier.height(28.dp))

            AfterTheProgramSection(
                trainingProgram = trainingProgram
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(p_color1)
                    .clickable {
                        onEvent(
                            CourseEvent.RequestCourse(
                                courseId = trainingProgram.id
                            ),{

                            },{

                            }
                        )
                    }
                    .fillMaxWidth()
                    .height(45.dp)
            ) {
                Text(
                    text = "Request Course",
                    style = TextStyle(
                        color = customWhite0,
                        fontSize = 20.sp
                    )
                )
            }
        }

        item {
            Spacer(Modifier.height(26.dp))
        }


    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun TrainingProgram_preview() {

    SharedTransitionLayout {

        NavHost(navController = rememberNavController(), startDestination = TrainingProgramScreen(trainingProgram_id = 0, title = "" , desc = "")){
            composable<TrainingProgramScreen> {
                trainingProgramScreen(
                    animatedVisibilityScope = this ,
                    trainingProgram = TrainingProgram(),
                    modifier = Modifier
                        .width(340.dp)
                        .background(background_color_0)
                )
            }
        }
    }

}