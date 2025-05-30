package com.jethings.study.presentation.view.screens.AcademyHome.components.AcademicPhases

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.view.screens.AcademyHome.components.AcademicPhases.sections.Header
import com.jethings.study.presentation.view.screens.AcademyHome.components.AcademicPhases.sections.PrimerySection


@Composable
fun AcademicPhases(
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {

        Header()

        Spacer(modifier = Modifier.height(36.dp))

        PrimerySection()


        Spacer(modifier = Modifier.height(16.dp))
    }

}


@Preview
@Composable
private fun AcademicPhases_prev() {
    AcademicPhases(
        modifier = Modifier
            .background(background_color_0)
    )
}