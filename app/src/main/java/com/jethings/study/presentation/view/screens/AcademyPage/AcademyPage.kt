package com.jethings.study.presentation.view.screens.AcademyPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack0
import com.jethings.study.presentation.ui.theme.customBlack2
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite2
import com.jethings.study.presentation.ui.theme.customWhite3
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.view.screens.AcademyPage.components.slider.Slider
import com.jethings.study.util.objects.TextStyles


@Composable
fun AcademyPage(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Slider()
        Spacer(modifier = Modifier.height(26.dp))

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = "Best Formation",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ6.copy(color = customBlack2)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = customBlack2,
                    modifier = Modifier
                        .size(26.dp)
                        .rotate(-90f)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp , vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                for ( i in 1..10){
                    item {
                        Surface(
                            shadowElevation = 2.dp,
                            shape = RoundedCornerShape(12.dp),
//                            border = BorderStroke(
//                                width = 1.dp,
//                                color = customWhite3
//                            ),
                            modifier = Modifier
                                .height(180.dp)
                                .width(150.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                AsyncImage(
                                    model = "",
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(customWhite2)
                                        .height(90.dp)
                                )
                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                ) {
                                    Text(
                                        text = "The name of the training programThe name of the training programThe name of the training program",
                                        style = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = customBlack3 ),
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis

                                    )
                                }

                                Spacer(modifier = Modifier.weight(1f))

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                ) {
                                    AsyncImage(
                                        model = "",
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clip(CircleShape)
                                            .background(customBlack0.copy(alpha = 0.3f))
                                    )
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = 8.dp)
                                    ) {
                                        Text(
                                            text = "Teacher name",
                                            style = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = customBlack3 , fontSize = 10.sp),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis

                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }
        }
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
    AcademyPage(
        modifier = Modifier
            .background(background_color_0)
    )
}