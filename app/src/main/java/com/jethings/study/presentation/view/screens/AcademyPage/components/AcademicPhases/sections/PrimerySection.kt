package com.jethings.study.presentation.view.screens.AcademyPage.components.AcademicPhases.sections

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color1_dark
import com.jethings.study.util.objects.TextStyles

@Composable
fun PrimerySection(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = p_color1,
        border = BorderStroke(width = 2.dp , color = p_color1_dark),
        onClick = {

        },
        modifier = Modifier
            .height(38.dp)
            .width(100.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Primery",
                style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customWhite0)
            )
        }
    }

    Spacer(modifier = Modifier.height(18.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.width(16.dp))


        for (i in 1..5){
            Surface(
                shape = RoundedCornerShape(8.dp),
                shadowElevation = 2.dp,
                modifier = Modifier
                    .size(95.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Year",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack3)
                    )
                    Text(
                        text = "$i",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack3)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

        }

    }
}


@Preview
@Composable
private fun PrimerySection_prev() {
    PrimerySection()
}