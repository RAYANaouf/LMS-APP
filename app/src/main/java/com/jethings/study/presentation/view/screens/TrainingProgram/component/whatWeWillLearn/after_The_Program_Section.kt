package com.jethings.study.presentation.view.screens.TrainingProgram.component.whatWeWillLearn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jethings.study.data.db.entities.entities.TrainingProgram
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.util.objects.TextStyles


@Composable
fun WhatWeWillLearn(
    trainingProgram: TrainingProgram,
    modifier: Modifier = Modifier
) {
    if (trainingProgram.whatYouWillLearn == null || trainingProgram.targetAudience == "")
        return
    Box(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "What we will learn",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(600)
            )
        )
    }
    Spacer(modifier = Modifier.height(6.dp))

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text =  trainingProgram.whatYouWillLearn ?: "",
            style = TextStyle(
                fontSize = 14.sp
            )
        )
    }
}

@Preview
@Composable
private fun WhatWeWillLearn_prev() {
    WhatWeWillLearn(
        trainingProgram = TrainingProgram()
    )
}