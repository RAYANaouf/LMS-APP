package com.jethings.study.presentation.view.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jethings.study.R
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack2
import com.jethings.study.presentation.ui.theme.customBlack5
import com.jethings.study.presentation.ui.theme.customBlack8
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.customWhite3
import com.jethings.study.presentation.ui.theme.customWhite5
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.ui.theme.p_color5
import com.jethings.study.util.objects.TextStyles
import java.time.format.TextStyle

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 35.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            shadowElevation = 2.dp,
            onClick = {

            },
            color = customWhite0,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(50.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(p_color1)
                            .padding(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.academy_icon),
                            tint = customWhite1,
                            contentDescription = null
                        )
                    }

                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "Academies" ,
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack5)
                        )
                    }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(p_color5)
                    ) {
                        Text(
                            text = "35",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = customWhite0),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }


                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {

            Spacer(modifier = Modifier.width(16.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = p_color1,
                        shape = CircleShape
                    )
                    .clickable {

                    }
                    .padding(horizontal = 7.dp, vertical = 2.dp)


            ) {
                Text(
                    text = "created",
                    style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight(600) , fontSize = 12.sp, color = p_color1),
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {

                    }
                    .padding(horizontal = 6.dp, vertical = 2.dp)


            ) {
                Text(
                    text = "pending",
                    style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight(500) , fontSize = 12.sp, color = customBlack2),
                )
            }

            Spacer(modifier = Modifier.width(6.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {

                    }
                    .padding(horizontal = 6.dp, vertical = 2.dp)


            ) {
                Text(
                    text = "refused",
                    style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight(500) , fontSize = 12.sp, color = customBlack2),
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {

                    }
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "deactivated",
                    style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight(500) , fontSize = 12.sp, color = customBlack2),
                )
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .horizontalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.width(16.dp))






            for ( i in 1..5){
                Surface(
                    shadowElevation = 2.dp,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = customWhite5
                    ),
                    color = customWhite0,
                    modifier = Modifier
                        .height(160.dp)
                        .width(120.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .border(
                                    width = 1.dp,
                                    color = p_color1,
                                    shape = CircleShape
                                )
                                .background(customWhite1)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

            }



        }


        Spacer(modifier = Modifier.height(55.dp))


        Surface(
            shape = RoundedCornerShape(10.dp),
            shadowElevation = 2.dp,
            onClick = {

            },
            color = customWhite0,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(50.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(p_color1)
                            .padding(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.teacher),
                            tint = customWhite1,
                            contentDescription = null
                        )
                    }

                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "Teachers" ,
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack5)
                        )
                    }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(p_color5)
                    ) {
                        Text(
                            text = "129",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = customWhite0),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }


                }
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.width(16.dp))

            for(i in 1..5){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(100.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(customWhite3)
                    ) {
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box {
                        Text(
                            text = "Teacher Name",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = customBlack5)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}


@Preview
@Composable
private fun HomeScreen_preview() {
    HomeScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color_0)
    )
}