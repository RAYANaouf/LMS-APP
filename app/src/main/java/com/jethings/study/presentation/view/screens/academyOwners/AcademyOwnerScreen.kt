package com.jethings.study.presentation.view.screens.addOwner

import android.widget.Toast
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jethings.study.R
import com.jethings.study.data.db.entities.Account
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack2
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customBlack5
import com.jethings.study.presentation.ui.theme.customBlack6
import com.jethings.study.presentation.ui.theme.customBlack8
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite3
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color4
import com.jethings.study.presentation.view.screens.academy.events.AcademyEvent
import com.jethings.study.presentation.view.screens.academyOwners.event.AcademyOwnersEvents
import com.jethings.study.util.objects.TextStyles


@Composable
fun AcademyOwnerScreen(
    academyId : Int ,
    owners : List<Account> = emptyList(),
    onEvent : (AcademyOwnersEvents , ()->Unit , ()->Unit)->Unit = {_,_,_->},
    onNavigate : () -> Unit = {},
    modifier: Modifier = Modifier
) {


    /*** vars ***/
    val context = LocalContext.current
    var loading by rememberSaveable {
        mutableStateOf(false)
    }


    /*** launch ****/
    LaunchedEffect(key1 = true) {

        loading = true

        onEvent(
            AcademyOwnersEvents.GetAcademyOwnersEvent(academyId),
            {
                Toast.makeText(context , "Success" , Toast.LENGTH_SHORT).show()
                loading = false
            },{
                Toast.makeText(context , "Failed" , Toast.LENGTH_SHORT).show()
                loading = false
            }

        )
    }


    var search by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {


            Spacer(modifier = Modifier.height(30.dp))

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

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 35.dp)
            ) {
                Text(
                    text = "Owners",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ7.copy(color = customBlack6)
                )
            }

            Spacer(modifier = Modifier.height(26.dp))


            if(loading){
                val composition by rememberLottieComposition( LottieCompositionSpec.RawRes(R.raw.loading))
                val progress = animateLottieCompositionAsState(composition = composition , iterations = LottieConstants.IterateForever)

                LottieAnimation(
                    composition = composition ,
                    progress = progress.value ,
                    modifier = Modifier.fillMaxSize()
                )
            }
            else if(owners.isEmpty()){
                val composition by rememberLottieComposition( LottieCompositionSpec.RawRes(R.raw.empty))
                val progress = animateLottieCompositionAsState(composition = composition , iterations = LottieConstants.IterateForever)

                LottieAnimation(
                    composition = composition ,
                    progress = progress.value ,
                    modifier = Modifier.fillMaxSize()
                )
            }else{
                for ( owner in owners){
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

        Surface(
            shadowElevation = 6.dp,
            shape = CircleShape,
            color = p_color1,
            onClick = {

            },
            modifier = Modifier
                .offset(x = -36.dp, y = -45.dp)
                .size(55.dp)
                .align(Alignment.BottomEnd)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = null,
                    tint = customWhite0,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }
    }
}


@Preview
@Composable
private fun AddOwnerScreen_prev() {

    AcademyOwnerScreen(
        academyId = 0,
        modifier = Modifier
            .background(background_color_0)
    )
}