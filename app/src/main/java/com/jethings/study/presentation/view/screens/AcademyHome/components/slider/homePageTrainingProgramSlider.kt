package com.jethings.study.presentation.view.screens.AcademyHome.components.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jethings.study.presentation.ui.theme.customBlack0
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.util.objects.TextStyles


@Composable
fun Slider(
    modifier: Modifier = Modifier
) {
    Surface(
        color = customWhite0,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(26.dp)
    ) {
        Box(
            modifier = Modifier
        ) {
            AsyncImage(
                model = "",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 16.dp , top = 16.dp )
                    .clip(CircleShape)
                    .size(45.dp)
                    .background(customBlack0.copy(alpha = 0.4f))
            )
            AsyncImage(
                model = "",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .background(customBlack0.copy(alpha = 0.3f))
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .widthIn(min = 120.dp, max = 250.dp)
                    .padding(bottom = 8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {

                    }
                    .background(p_color1)
                    .padding(horizontal = 16.dp , vertical = 6.dp)
                    .height(30.dp)
            ) {
                Text(
                    text = "Click Here",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customWhite0)
                )
            }
        }
    }
}


@Preview
@Composable
private fun Slider_preview() {
    Slider()
}