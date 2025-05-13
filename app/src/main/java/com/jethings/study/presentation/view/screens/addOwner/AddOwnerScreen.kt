package com.jethings.study.presentation.view.screens.addOwner

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.R
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack2
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customBlack8
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite3
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.util.objects.TextStyles


@Composable
fun AddOwnerScreen(
    modifier: Modifier = Modifier
) {


    /*** vars ***/

    var search by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {


        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value         = search,
            onValueChange = {
                search = it
            },
            textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = p_color1),
            label         = {
                Text(
                    text = "Search for user",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack8),
                )
            },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.user), contentDescription = null , tint = p_color1 , modifier = Modifier.size(26.dp))
            },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors().copy(focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0 , focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , cursorColor = p_color1 ),
            modifier       = Modifier
                .heightIn(min = 45.dp)
                .fillMaxWidth()
                .padding(horizontal = 35.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))


        for ( i in 0.. 15){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .height(65.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                        .background(customWhite3)
                ) {

                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(vertical = 8.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "User name",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "User email",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = customBlack2)
                        )
                    }
                }
            }

        }

    }
}


@Preview
@Composable
private fun AddOwnerScreen_prev() {

    AddOwnerScreen(
        modifier = Modifier
            .background(background_color_0)
    )
}