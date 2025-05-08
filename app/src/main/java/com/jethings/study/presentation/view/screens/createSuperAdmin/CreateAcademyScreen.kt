package com.jethings.study.presentation.view.screens.createAcademy

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import com.jethings.study.presentation.ui.theme.customBlack6
import com.jethings.study.presentation.ui.theme.customBlack8
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color1_dark
import com.jethings.study.presentation.view.material.AlphaTextFields.AlphaTextField
import com.jethings.study.util.objects.TextStyles


@Composable
fun CreateAcademyScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Spacer(modifier = Modifier.height(55.dp))

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(customWhite0)
        ) {

        }

        Spacer(modifier = Modifier.weight(1f))


        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            OutlinedTextField(
                value         = "",
                onValueChange = {

                },
                textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack5),
                label         = {
                    Text(
                        text = "Academy name",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = p_color1),
                    )
                },
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.academy_icon), contentDescription = null , tint = p_color1 , modifier = Modifier.size(26.dp))
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors().copy(focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0 , focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , cursorColor = p_color1 ),
                modifier       = Modifier
                    .heightIn(min = 45.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value         = "",
                onValueChange = {

                },
                textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack5),
                label         = {
                    Text(
                        text = "Academy Password",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = p_color1),
                    )
                },
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.password), contentDescription = null , tint = p_color1 , modifier = Modifier.size(26.dp))
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors().copy(focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0 , focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , cursorColor = p_color1 ),
                modifier       = Modifier
                    .heightIn(min = 45.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .border(
                    width = 3.dp,
                    color = p_color1_dark,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .background(p_color1)
        ) {
            Text(
                text = "Create",
                style = TextStyle(color = customWhite0 , fontSize = 24.sp , fontWeight = FontWeight(700))
            )
        }

        Spacer(modifier = Modifier.height(65.dp))

    }
}


@Preview
@Composable
private fun CreateAcademyScreen_preview() {
    CreateAcademyScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color_0)
    )
}