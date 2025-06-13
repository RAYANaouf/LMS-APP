package com.jethings.study.presentation.view.screens.academy.components.tabPage

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jethings.study.R
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.TrainingProgramScreen
import com.jethings.study.presentation.nvgraph.createTrainingProgram
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.customWhite8
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.view.screens.academy.events.AcademyEvent
import com.jethings.study.util.objects.TextStyles



@Composable
fun TrainingProgramPage(
    onEvent : (AcademyEvent , ()->Unit , ()->Unit)->Unit = {_,_,_->},
    academyId : Int,
    academy : Academy?,
    onNavigate : (AppScreen)->Unit = {},
    modifier: Modifier = Modifier
) {

    LaunchedEffect(academyId) {
        onEvent(
            AcademyEvent.GetAllTrainingProgramByAcademy(academy_id = academyId),{
                //Toast.makeText(context , "Success" , Toast.LENGTH_SHORT).show()
            },{
                //Toast.makeText(context , "Failure" , Toast.LENGTH_SHORT).show()
            }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .height(750.dp)
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        if (academy?.trainingPrograms?.isEmpty() == true) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                ) {
                    val composition by rememberLottieComposition( LottieCompositionSpec.RawRes(R.raw.empty))
                    val progress = animateLottieCompositionAsState(composition = composition , iterations = LottieConstants.IterateForever)

                    LottieAnimation(
                        composition = composition ,
                        progress = progress.value ,
                        modifier = Modifier.fillMaxSize()
                    )

                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(35.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(p_color2)
                        .clickable {
                            onNavigate(createTrainingProgram)
                        }
                ) {
                    Text(
                        text = "Create Training Program",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customWhite0)
                    )
                }
            }
        }else{
            academy?.trainingPrograms?.forEach {
                Surface(
                    shadowElevation = 2.dp,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        onNavigate(
                            TrainingProgramScreen(
                                trainingProgram_id = it.id.toInt(),
                                title      = it.name,
                                desc       = it.description ?: "",
                                coverPhoto = it.coverPhoto ?: ""
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .height(100.dp)
                        ) {
                            if (it.coverPhoto == null || it.coverPhoto == ""){
                                Image(
                                    painter = painterResource(id = R.drawable.training_program),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .fillMaxHeight()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(customWhite1)
                                        .border(
                                            width = 1.dp,
                                            color = customWhite8,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                )
                            }
                            else{
                                AsyncImage(
                                    model = it.coverPhoto,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .fillMaxHeight()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(customWhite1)
                                        .border(
                                            width = 1.dp,
                                            color = customWhite8,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                )
                            }

                            Column {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(30.dp)
                                        .padding(start = 8.dp)
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .size(22.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(10.dp)
                                                .clip(CircleShape)
                                                .background(Color.Green)
                                        )
                                    }
                                    Text(
                                        text = "Active",
                                        style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack3)
                                    )
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(30.dp)
                                        .padding(start = 8.dp)
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .size(22.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.certificate),
                                            contentDescription = null ,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(12.dp)
                                        )
                                    }
                                    Text(
                                        text = "Certificated",
                                        style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack3)
                                    )
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(30.dp)
                                        .padding(start = 8.dp)
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .size(22.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.money),
                                            contentDescription = null ,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(12.dp)
                                        )
                                    }
                                    Text(
                                        text = "2600 DA",
                                        style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack3)
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                                .fillMaxWidth()
                        ){
                            Text(
                                text = it.name,
                                style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack3)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                                .fillMaxWidth()
                        ){
                            Text(
                                text = it.description ?: "",
                                style = TextStyles.Monospace_TextStyles.TextStyleSZ10
                                    .copy(color = customBlack3)
                            )
                        }
                    }
                }
            }
        }
    }
}