package com.jethings.study.presentation.view.screens.setting

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jethings.study.R
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack1
import com.jethings.study.presentation.ui.theme.customBlack5
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.customWhite2
import com.jethings.study.presentation.ui.theme.customWhite3
import com.jethings.study.presentation.ui.theme.customWhite7
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.util.objects.TextStyles


@Composable
fun SettingScreen(
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {


        Spacer(Modifier.fillMaxWidth().height(55.dp))

        //Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(customWhite0)
                .padding(horizontal = 16.dp, vertical = 6.dp , )
        ) {
            Text(
                text = "Your Accounts",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500),
                    color = customWhite7
                )
            )
        }


        Column(
            modifier = Modifier
                .background(customWhite0)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .clickable {

                    }
                    .padding(start = 16.dp , end = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(customWhite2)
                ){
                    Image(
                        painter = painterResource(R.drawable.user),
                        contentDescription = null ,
                        modifier = Modifier
                            .padding(6.dp)
                    )
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        text = "All Accounts",
                        style = TextStyle(
                            fontSize = 18.sp
                        )
                    )
                    Text(
                        text = "From here you can see all your accounts and its related information, and you can edit it also.",
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = customWhite7
                        )
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(28.dp))


        //Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(customWhite0)
                .padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text(
                text = "General",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500),
                    color = customWhite7
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(customWhite0)
                .padding(start = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Personal Info",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack1)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        tint = customBlack1,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Accessibility",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack1)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        tint = customBlack1,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Notification",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack1)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        tint = customBlack1,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        //Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(customWhite0)
                .padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text(
                text = "Privacy",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500),
                    color = customWhite7
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(customWhite0)
                .padding(start = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Settings",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack1)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        tint = customBlack1,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Change Password",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack1)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        tint = customBlack1,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Log Out",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack1)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        tint = customBlack1,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(150.dp))

    }
}


@Preview
@Composable
private fun SettingScreen_prev() {
    SettingScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color_0)
    )
}