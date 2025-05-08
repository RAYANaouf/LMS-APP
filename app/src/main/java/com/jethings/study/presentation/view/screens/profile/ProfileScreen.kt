package com.jethings.study.presentation.view.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jethings.study.R
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack2
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customBlack5
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.customWhite3
import com.jethings.study.presentation.ui.theme.customWhite5
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.ui.theme.p_color3
import com.jethings.study.presentation.ui.theme.p_color5
import com.jethings.study.presentation.view.material.AlphaButton
import com.jethings.study.presentation.view.material.AlphaTextFields.AlphaTextField
import com.jethings.study.util.objects.TextStyles

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 35.dp)
    ) {

        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(customWhite3)
        ) {

        }

        Spacer(modifier = Modifier.height(60.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            AlphaTextField(
                text = "rayan",
                onValueChange = {

                },
                textFieldStyle = TextStyles.Monospace_TextStyles.TextStyleSZ6,
                hint = "First Name",
                hintStyle = TextStyles.Monospace_TextStyles.TextStyleSZ6.copy(

                ),
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            AlphaTextField(
                text = "aouf",
                onValueChange = {

                },
                textFieldStyle = TextStyles.Monospace_TextStyles.TextStyleSZ6,
                hint = "last Name",
                hintStyle = TextStyles.Monospace_TextStyles.TextStyleSZ6.copy(

                ),
                modifier = Modifier
                    .weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        AlphaTextField(
            text = "rayanaouf1512@gmail.com",
            onValueChange = {

            },
            textFieldStyle = TextStyles.Monospace_TextStyles.TextStyleSZ6,
            hint = "Email",
            hintStyle = TextStyles.Monospace_TextStyles.TextStyleSZ6.copy(

            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        AlphaTextField(
            text = "0795502905",
            onValueChange = {

            },
            textFieldStyle = TextStyles.Monospace_TextStyles.TextStyleSZ6,
            hint = "Phone",
            hintStyle = TextStyles.Monospace_TextStyles.TextStyleSZ6.copy(

            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(55.dp)
                .border(
                    width = 3.dp,
                    color = p_color1,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .clickable {

                }
                .background(p_color2)
        ) {
            Text(
                text = "Save",
                style = TextStyle(fontWeight = FontWeight(700) , color = p_color1 , fontSize = 18.sp )
            )
        }

        

    }
}


@Preview
@Composable
private fun HomeScreen_preview() {
    ProfileScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color_0)
    )
}