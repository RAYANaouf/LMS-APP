package com.jethings.study.presentation.view.screens.home.components.teacherSection

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jethings.study.R
import com.jethings.study.presentation.ui.theme.customBlack5
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.customWhite3
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color5
import com.jethings.study.util.objects.TextStyles


@Composable
fun TeacherSection(
    modifier: Modifier = Modifier
) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
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