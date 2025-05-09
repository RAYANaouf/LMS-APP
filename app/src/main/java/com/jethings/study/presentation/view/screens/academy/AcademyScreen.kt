package com.jethings.study.presentation.view.screens.academy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.customWhite2
import com.jethings.study.presentation.ui.theme.customWhite5
import com.jethings.study.presentation.ui.theme.p_color3
import com.jethings.study.presentation.ui.theme.p_color4
import com.jethings.study.util.objects.TextStyles

@Composable
fun AcademyScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(customWhite2)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = 120.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(customWhite5)
                ) {
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(40.dp)
                        .widthIn(max = 150.dp)
                ) {
                    Text(
                        text = "Academy name",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(130.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                ){
                    Text(
                        text = "Owner",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                ){
                    Text(
                        text = "1",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                ){
                    Text(
                        text = "Teacher",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                ){
                    Text(
                        text = "13",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                ){
                    Text(
                        text = "Student",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                ){
                    Text(
                        text = "197",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                    )
                }
            }
        }


    }
}


@Preview
@Composable
private fun AcademyScreen_prev() {
    AcademyScreen(modifier = Modifier
        .fillMaxSize()
        .background(background_color_0))
}