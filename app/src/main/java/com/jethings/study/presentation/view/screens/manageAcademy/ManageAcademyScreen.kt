package com.jethings.study.presentation.view.screens.manageAcademy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.R
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.createPost
import com.jethings.study.presentation.nvgraph.createTrainingProgram
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.p_color1_dark
import com.jethings.study.presentation.ui.theme.p_color2_dark
import com.jethings.study.util.objects.TextStyles
import kotlinx.serialization.Serializable


@Composable
fun ManageAcademyScreen(
    onNavigate : (AppScreen)->Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(55.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(p_color1_dark)
                .clickable {
                    onNavigate(createPost)
                }
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp, end = 22.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.post),
                    contentDescription = null ,
                    tint = customWhite0,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
            Text(
                text = "Create Post",
                style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customWhite0)
            )
        }
        Spacer(modifier = Modifier.height(22.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(55.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(p_color2_dark)
                .clickable {
                    onNavigate(createTrainingProgram)
                }
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp, end = 22.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.teacher),
                    contentDescription = null ,
                    tint = customWhite0,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
            Text(
                text = "Create Training Program",
                style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customWhite0)
            )
        }
    }
}


@Preview
@Composable
private fun ManageAcademyScreen_prev() {
    ManageAcademyScreen(
        modifier = Modifier
            .fillMaxSize()
    )
}