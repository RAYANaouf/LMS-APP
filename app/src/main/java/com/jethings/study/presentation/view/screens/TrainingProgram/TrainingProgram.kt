package com.jethings.study.presentation.view.screens.TrainingProgram

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
import androidx.compose.foundation.shape.CircleShape
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
import coil.compose.AsyncImage
import com.jethings.study.R
import com.jethings.study.data.db.entities.entities.TrainingProgram
import com.jethings.study.presentation.nvgraph.TrainingProgramScreen
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack0
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.view.screens.TrainingProgram.component.AfterThePogramSection.AfterTheProgramSection
import com.jethings.study.presentation.view.screens.TrainingProgram.component.budget.BudgetSection
import com.jethings.study.presentation.view.screens.TrainingProgram.component.cover.Cover
import com.jethings.study.presentation.view.screens.TrainingProgram.component.desc.Desc
import com.jethings.study.presentation.view.screens.TrainingProgram.component.forWho.ForWhoSection
import com.jethings.study.presentation.view.screens.TrainingProgram.component.name.TrainingName
import com.jethings.study.presentation.view.screens.TrainingProgram.component.whatWeWillLearn.WhatWeWillLearn
import com.jethings.study.util.objects.TextStyles

@Composable
fun trainingProgramScreen(
    trainingProgram: TrainingProgram,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {

        Cover(
            trainingProgram = trainingProgram
        )

        Spacer(modifier = Modifier.height(18.dp))

        TrainingName(
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


    }
}


@Preview
@Composable
private fun TrainingProgram_preview() {
    trainingProgramScreen(
        trainingProgram = TrainingProgram(),
        modifier = Modifier
            .width(340.dp)
            .background(background_color_0)
    )
}