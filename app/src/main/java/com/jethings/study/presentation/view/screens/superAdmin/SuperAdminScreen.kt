package com.jethings.study.presentation.view.screens.superAdmin

import android.graphics.Paint.Align
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jethings.study.R
import com.jethings.study.data.db.entities.entities.SuperAdmin
import com.jethings.study.presentation.nvgraph.academyOwnerList
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack2
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.customWhite2
import com.jethings.study.presentation.ui.theme.customWhite3
import com.jethings.study.presentation.ui.theme.customWhite5
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color4
import com.jethings.study.presentation.ui.theme.p_color5
import com.jethings.study.presentation.view.screens.academy.events.AcademyEvent
import com.jethings.study.presentation.view.screens.superAdmin.events.SuperAdminEvent
import com.jethings.study.util.objects.TextStyles


@Composable
fun SuperAdminScreen(
    superAdmin_id : Int = 0,
    superAdmin: SuperAdmin? = SuperAdmin(firstName = "Rayan" , lastName = "Aouf"),
    onEvent : (event : SuperAdminEvent , onSuccess : ()->Unit , onFailure : ()->Unit) -> Unit ={_,_,_->},
    modifier: Modifier = Modifier
) {

    /*** vars ***/
    val context = LocalContext.current


    /*** effect  ***/
    LaunchedEffect(key1 = true) {
        onEvent(
            SuperAdminEvent.GetSuperAdminDetails(
                superAdmin_id = superAdmin_id
            ),{
                Toast.makeText(context , "success" , Toast.LENGTH_SHORT).show()
            },{
                Toast.makeText(context , "success" , Toast.LENGTH_SHORT).show()
            }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(26.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .background(customWhite2)
            ) {
                if (superAdmin?.profilePhoto != null && superAdmin.profilePhoto != ""){
                    AsyncImage(
                        model = superAdmin.profilePhoto,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }else{
                    Icon(
                        painter = painterResource(id = R.drawable.admin) ,
                        contentDescription = null,
                        tint = p_color1,
                        modifier = Modifier
                            .size(85.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(26.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 12.dp, bottom = 12.dp)
            ) {
                Text(
                    text = (superAdmin?.firstName ?: "") + " " +  (superAdmin?.lastName ?: ""),
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ7.copy(color = customBlack2)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Rank : 0",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack2)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.admin) ,
                contentDescription = null,
                tint = p_color1,
                modifier = Modifier
                    .size(26.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }

        Spacer(modifier = Modifier.height(36.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(40.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(customWhite2)
        ) {
            Spacer(modifier = Modifier.width(6.dp))
            Surface(
                color = customWhite0,
                shape = RoundedCornerShape(6.dp),
                shadowElevation = 4.dp,
                onClick = {

                },
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Info",
                        style = TextStyle(
                            color = p_color1,
                            fontSize = 17.sp,
                            fontWeight = FontWeight(600)
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.width(6.dp))
            Surface(
                color = Color.Transparent,
                shape = RoundedCornerShape(6.dp),
                shadowElevation = 0.dp,
                onClick = {

                },
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Edit",
                        style = TextStyle(
                            color = customBlack3,
                            fontSize = 15.sp,
                            fontWeight = FontWeight(500)
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.width(6.dp))
            Surface(
                color = Color.Transparent,
                shape = RoundedCornerShape(6.dp),
                shadowElevation = 0.dp,
                onClick = {

                },
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Delete",
                        style = TextStyle(
                            color = p_color5,
                            fontSize = 15.sp,
                            fontWeight = FontWeight(500)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.width(6.dp))
        }


        Spacer(modifier = Modifier.height(155.dp))

    }



}


@Preview
@Composable
private fun SuperAdminScreen_prev() {
    SuperAdminScreen(
        modifier = Modifier
            .background(background_color_0)
    )
}