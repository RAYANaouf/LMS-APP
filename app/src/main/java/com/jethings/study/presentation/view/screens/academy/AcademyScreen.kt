package com.jethings.study.presentation.view.screens.academy

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.academyOwnerList
import com.jethings.study.presentation.nvgraph.addOwnerScreen
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customBlack5
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.customWhite2
import com.jethings.study.presentation.ui.theme.customWhite3
import com.jethings.study.presentation.ui.theme.customWhite5
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.ui.theme.p_color3
import com.jethings.study.presentation.ui.theme.p_color4
import com.jethings.study.presentation.view.screens.academy.events.AcademyEvent
import com.jethings.study.presentation.view.screens.addOwner.AddOwnerScreen
import com.jethings.study.util.objects.TextStyles

@Composable
fun AcademyScreen(
    academyId : Int,
    academy   : Academy? = null,
    onEvent : (AcademyEvent , ()->Unit , ()->Unit)->Unit = {_,_,_->},
    onNavigate : (AppScreen)->Unit = {},
    modifier: Modifier = Modifier
) {

    /*** vars ***/
    val context = LocalContext.current


    /*** effect  ***/
    LaunchedEffect(key1 = true) {
        onEvent(
            AcademyEvent.GetAcademyDetails(
                academy_id = academyId
            ),{
                Toast.makeText(context , "Academy details refreshed." , Toast.LENGTH_SHORT).show()
            },{
                Toast.makeText(context , "Error in getting Academy details." , Toast.LENGTH_SHORT).show()
            }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
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
                        text = academy?.name  ?: "Loading..."     ,
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(155.dp))

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
                    .clickable {
                        onNavigate(academyOwnerList)
                    }
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
                    .clickable {
                    }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                ){
                    Text(
                        text = "Employee",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                ){
                    Text(
                        text = "21",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable {
                    }
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
                    .clickable {
                    }
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

        Spacer(modifier = Modifier.height(55.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.weight(1f))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(35.dp)
                    .weight(2f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(customWhite3)
                    .clickable {

                    }
            ) {
                Text(
                    text = "Edit",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(35.dp)
                    .weight(2f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(customWhite3)
                    .clickable {

                    }
            ) {
                Text(
                    text = "Analitics",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

        }


        Spacer(modifier = Modifier.height(55.dp))


        Row(
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            Box(

                modifier = Modifier
                    .fillMaxHeight()
                    .clickable {

                    }
                    .drawBehind {
                        drawLine(
                            color = p_color1,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 2.dp.toPx()
                        )
                    }
                    .padding(vertical = 8.dp, horizontal = 26.dp)

            ) {
                Text(
                    text = "Details",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ6.copy(color = p_color1)
                )
            }

            Box(

                modifier = Modifier
                    .fillMaxHeight()
                    .clickable {

                    }
                    .padding(vertical = 8.dp, horizontal = 26.dp)

            ) {
                Text(
                    text = "Formation",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ6.copy(color = customBlack4)
                )
            }

            Box(

                modifier = Modifier
                    .fillMaxHeight()
                    .clickable {

                    }
                    .padding(vertical = 8.dp, horizontal = 26.dp)

            ) {
                Text(
                    text = "Courses",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ6.copy(color = customBlack4)
                )
            }
        }



        Spacer(modifier = Modifier.height(450.dp))

    }


}


@Preview
@Composable
private fun AcademyScreen_prev() {
    AcademyScreen(
        academyId = 1,
        modifier = Modifier
            .fillMaxSize()
            .background(background_color_0)
    )
}