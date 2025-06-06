package com.jethings.study.presentation.view.screens.logIn

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jethings.study.R
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.signUpScreen
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack8
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.view.material.AlphaButton
import com.jethings.study.util.objects.TextStyles

@Composable
fun PhoneLayout(
    gmail            : String,
    onGmailChange    : (String)->Unit = {},
    password         : String,
    onPasswordChange : (String)->Unit = {},
    onNavigate       : (AppScreen)->Unit = {},
    onLogIn          : (onFailure : ()-> Unit)->Unit = {},
    modifier         : Modifier = Modifier
) {


    val context = LocalContext.current

    var search by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(background_color_0)
    ) {


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {

                Image(
                    painter = painterResource(id = R.drawable.app_store_logo_none_background),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(120.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = p_color1,
                                fontSize = 55.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        ){
                            append("E ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = p_color2,
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ){
                            append("Learning")
                        }
                    }
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 22.dp, end = 22.dp)
        ) {


            OutlinedTextField(
                value         = gmail,
                onValueChange = {
                    onGmailChange(it)
                },
                textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = p_color1),
                label         = {
                    Text(
                        text = "Email",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack8),
                    )
                },
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.mail), contentDescription = null , tint = p_color1 , modifier = Modifier.size(26.dp))
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors().copy(focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0 , focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , cursorColor = p_color1 ),
                modifier       = Modifier
                    .heightIn(min = 45.dp)
                    .fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value         = password,
                onValueChange = {
                    onPasswordChange(it)
                },
                textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = p_color1),
                label         = {
                    Text(
                        text = "Password",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack8),
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
            )

            Spacer(modifier = Modifier.height(6.dp))

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .clickable {

                    }
                    .padding(top = 5.dp, end = 5.dp)
            ) {
                Text(
                    text = "Forgot the password",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = p_color1)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                if (!search){
                    AlphaButton(
                        "Log In",
                        backgroundColor = p_color1,
                        onClick = {
                            search = true
                            onLogIn {
                                search = false
                                Toast.makeText(context , "Log In Failed" , Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }else{

                    val composition by rememberLottieComposition( LottieCompositionSpec.RawRes(R.raw.loading))
                    val progress = animateLottieCompositionAsState(composition = composition , iterations = LottieConstants.IterateForever)

                    LottieAnimation(
                        composition = composition ,
                        progress = progress.value ,
                        modifier = Modifier.fillMaxSize()
                    )

                }
            }


            Spacer(modifier = Modifier.height(20.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        onNavigate(signUpScreen)
                    }
            ) {
                Text(
                    text = "Sign Up",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = p_color1)
                )
            }



        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
        ) {
            Text(
                text = "Powered by Jethings",
                style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = p_color1),
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )
        }


    }

}