package com.jethings.study.presentation.view.screens.home

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
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCancellationBehavior
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jethings.study.R
import com.jethings.study.data.db.entities.Account
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.data.db.entities.entities.Post
import com.jethings.study.data.db.entities.entities.SuperAdmin
import com.jethings.study.data.db.entities.entities.TrainingProgram
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.TrainingProgramScreen
import com.jethings.study.presentation.nvgraph.academyScreen
import com.jethings.study.presentation.nvgraph.homeScreen
import com.jethings.study.presentation.nvgraph.superAdminScreen
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack5
import com.jethings.study.presentation.ui.theme.customBlack7
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.customWhite3
import com.jethings.study.presentation.ui.theme.customWhite5
import com.jethings.study.presentation.ui.theme.customWhite8
import com.jethings.study.presentation.view.screens.home.components.SuperAdminSection.SuperAdminSection
import com.jethings.study.presentation.view.screens.home.components.academySection.academySection
import com.jethings.study.presentation.view.screens.home.components.bestTrainingProgramSection.BestTrainingPrograms
import com.jethings.study.presentation.view.screens.home.components.homeSlider.HomePageTrainingProgramSlider
import com.jethings.study.presentation.view.screens.home.components.postSection.PostSection
import com.jethings.study.presentation.view.screens.home.components.teacherSection.TeacherSection
import com.jethings.study.presentation.view.screens.home.events.HomeEvents
import com.jethings.study.util.objects.TextStyles
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Async

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    account             : Account? = null,
    animatedVisibilityScope: AnimatedVisibilityScope,
    academyList         : List<Academy>    = emptyList(),
    superAdminList      : List<SuperAdmin> = emptyList(),
    trainingProgramList : List<TrainingProgram> = emptyList(),
    postList            : List<Post> = emptyList(),
    onEvent             : (HomeEvents , onSuccess : () -> Unit, onFailure : () -> Unit) -> Unit = {_,_,_->},
    onNavigate          : (AppScreen) -> Unit = {},
    modifier            : Modifier = Modifier
) {

    /*** vars ***/
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        onEvent(
            HomeEvents.refreshAcademyList,
            {
                //Toast.makeText(context , "Academy list refreshed." , Toast.LENGTH_SHORT).show()
            },
            {
                //Toast.makeText(context , "Error on Academy list refreshing." , Toast.LENGTH_SHORT).show()
            }
        )
        onEvent(
            HomeEvents.refreshSuperAdminList,
            {
                //Toast.makeText(context , "super Admin list refreshed. ${superAdminList}" , Toast.LENGTH_SHORT).show()
            },
            {
                //Toast.makeText(context , "Error on Super Admin list refreshing." , Toast.LENGTH_SHORT).show()
            }
        )
    }

    if (account != null && account.isSuperAdmin){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(35.dp))

            SuperAdminSection(
                animatedVisibilityScope = animatedVisibilityScope,
                superAdminList = superAdminList,
                onSuperAdminClick =  {
                    onNavigate(superAdminScreen(superAdmin_id = it))
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(55.dp))


            academySection(
                animatedVisibilityScope = animatedVisibilityScope,
                academyList = academyList,
                onAcademyClick = {
                    onNavigate(academyScreen(academy_id = it))
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(55.dp))


            TeacherSection(
                modifier = Modifier
                    .fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(180.dp))

        }
    }
    else{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            HomePageTrainingProgramSlider()
            BestTrainingPrograms(
                animatedVisibilityScope = animatedVisibilityScope,
                trainingProgramList = trainingProgramList,
                onClick = {
                    onNavigate(
                        TrainingProgramScreen(
                            trainingProgram_id = it.id.toInt(),
                            title = it.name,
                            desc = it.description ?: "",
                            coverPhoto = it.coverPhoto ?: "",
                        )
                    )
                }
            )
            //posts section
            PostSection(
                postList = postList
            )

            Spacer(Modifier.height(80.dp))
        }
    }

}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun HomeScreen_preview() {
    SharedTransitionLayout {

        NavHost(navController = rememberNavController(), startDestination = homeScreen){
            composable<homeScreen> {
                HomeScreen(
                    animatedVisibilityScope = this ,
                    trainingProgramList = listOf(TrainingProgram(),TrainingProgram(),TrainingProgram()),
                    academyList = listOf(Academy(name = "Daracedemy") , Academy(name = "Djabali Academy"), Academy(name = "Achbal Academy")) ,
                    postList = listOf(
                        Post(photo = "https://storage.googleapis.com/daracademyfireproject.appspot.com/post_cover/dd6e06cf-900d-4594-9b85-68b58712556c.jpg" , content = "the content of the post..."),
                        Post(),
                        Post(),
                        Post()
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(background_color_0)
                )
            }
        }
    }


}