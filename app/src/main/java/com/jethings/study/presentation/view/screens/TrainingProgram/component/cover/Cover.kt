package com.jethings.study.presentation.view.screens.TrainingProgram.component.cover

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jethings.study.R
import com.jethings.study.data.db.entities.entities.TrainingProgram
import com.jethings.study.presentation.ui.theme.customBlack0
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1


@Composable
fun Cover(
    trainingProgram : TrainingProgram,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(customWhite1)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp)
                .size(36.dp)
                .clip(CircleShape)
                .background(customBlack0.copy(alpha = 0.3f))
                .clickable {

                }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = customWhite0,
                modifier = Modifier
                    .size(28.dp)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 8.dp)
                .size(36.dp)
                .clip(CircleShape)
                .background(customBlack0.copy(alpha = 0.3f))
                .clickable {

                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.full),
                contentDescription = null,
                tint = customWhite0,
                modifier = Modifier
                    .size(28.dp)
            )
        }
        AsyncImage(
            model = trainingProgram.coverPhoto,
            contentDescription = null ,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}


@Preview
@Composable
private fun Cover_prev() {
    Cover(
        trainingProgram = TrainingProgram()
    )
}