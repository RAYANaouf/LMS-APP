package com.jethings.study.presentation.view.screens.AcademyHome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.view.screens.AcademyHome.components.AcademicPhases.AcademicPhases
import com.jethings.study.presentation.view.screens.AcademyHome.components.bestTrainingPrograms.BestTrainingPrograms
import com.jethings.study.presentation.view.screens.AcademyHome.components.slider.Slider


@Composable
fun AcademyHome(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Slider()
        Spacer(modifier = Modifier.height(26.dp))
        BestTrainingPrograms()
        Spacer(modifier = Modifier.height(36.dp))
        AcademicPhases()
        Spacer(modifier = Modifier.height(150.dp))

    }
}

@Preview(
    name = "Galaxy S20 Preview",
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun AcademyPage_preview() {
    AcademyHome(
        modifier = Modifier
            .background(background_color_0)
    )
}