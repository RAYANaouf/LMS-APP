package com.jethings.study.presentation.view.screens.AcademyPage.components.AcademicPhases

import android.graphics.Paint.Align
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.presentation.ui.theme.customBlack8
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color1_dark
import com.jethings.study.util.objects.TextStyles


@Composable
fun AcademicPhases(
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier
                .height(1.dp)
                .background(customBlack8)
                .weight(1f))


            Spacer(modifier = Modifier.width(2.dp))

            Spacer(modifier = Modifier
                .height(6.dp)
                .width(2.dp)
                .background(customBlack8)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Spacer(modifier = Modifier
                .height(12.dp)
                .width(2.dp)
                .background(customBlack8)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Spacer(modifier = Modifier
                .height(18.dp)
                .width(2.dp)
                .background(customBlack8)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Spacer(modifier = Modifier
                .height(30.dp)
                .width(2.dp)
                .background(customBlack8)
            )

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Academic",
                style = TextStyles.Monospace_TextStyles.TextStyleSZ6.copy(color = customBlack3 )
                )
            Spacer(modifier = Modifier.width(16.dp))


            Spacer(modifier = Modifier
                .height(30.dp)
                .width(2.dp)
                .background(customBlack8)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Spacer(modifier = Modifier
                .height(18.dp)
                .width(2.dp)
                .background(customBlack8)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Spacer(modifier = Modifier
                .height(12.dp)
                .width(2.dp)
                .background(customBlack8)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Spacer(modifier = Modifier
                .height(6.dp)
                .width(2.dp)
                .background(customBlack8)
            )


            Spacer(modifier = Modifier.width(2.dp))

            Spacer(modifier = Modifier
                .height(1.dp)
                .background(customBlack8)
                .weight(1f))

        }
        Spacer(modifier = Modifier.height(36.dp))

        Surface(
            shape = RoundedCornerShape(12.dp),
            color = p_color1,
            border = BorderStroke(width = 2.dp , color = p_color1_dark),
            onClick = { /*TODO*/ },
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