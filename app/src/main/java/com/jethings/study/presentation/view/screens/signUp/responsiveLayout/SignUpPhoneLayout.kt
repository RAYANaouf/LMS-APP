package com.jethings.study.presentation.view.screens.signUp.responsiveLayout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.R
import com.jethings.study.presentation.ui.theme.customBlack8
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.view.material.AlphaButton
import com.jethings.study.util.objects.TextStyles


@Composable
fun SignUpPhoneLayout(
    modifier: Modifier = Modifier,
    firstName : String,
    lastName  : String,
    email     : String,
    phone     : String,
    password  : String,
    onChange  : (String , String)->Unit,
    onSignUp  : ()->Unit = {}
) {

    var passwordSecond by remember{
        mutableStateOf("")
    }


    Column(
        modifier = modifier
            .fillMaxHeight()
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .clip(RoundedCornerShape(bottomStart = 65.dp, bottomEnd = 65.dp))
                .background(p_color1)
        ){
            Text(
                text = "Create Store",
                style = TextStyles.Itim_TextStyles.TextStyleSZ1.copy(color = customWhite0),
                modifier = Modifier
            )
        }



        Spacer(modifier = Modifier.height(45.dp))


        Column(
//            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 22.dp, end = 22.dp)
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = {
                        onChange("firstName" , it)
                    },
                    textStyle = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = p_color1),
                    label = {
                        Text(
                            text = "First Name",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = customBlack8),
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = null,
                            tint = p_color1,
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = customWhite0,
                        unfocusedContainerColor = customWhite0,
                        focusedLabelColor = p_color1,
                        focusedIndicatorColor = p_color1,
                        cursorColor = p_color1
                    ),
                    modifier = Modifier
                        .heightIn(min = 45.dp)
                        .weight(1f)
                        .padding(start = 20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                OutlinedTextField(
                    value = lastName,
                    onValueChange = {
                        onChange("LastName" , it)
                    },
                    textStyle = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = p_color1),
                    label = {
                        Text(
                            text = "last Name",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = customBlack8),
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = null,
                            tint = p_color1,
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = customWhite0,
                        unfocusedContainerColor = customWhite0,
                        focusedLabelColor = p_color1,
                        focusedIndicatorColor = p_color1,
                        cursorColor = p_color1
                    ),
                    modifier = Modifier
                        .heightIn(min = 45.dp)
                        .weight(1f)
                        .padding(end = 20.dp)
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    onChange("email" , it)
                },
                textStyle = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = p_color1),
                label = {
                    Text(
                        text = "Email",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack8),
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.mail),
                        contentDescription = null,
                        tint = p_color1,
                        modifier = Modifier.size(26.dp)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = customWhite0,
                    unfocusedContainerColor = customWhite0,
                    focusedLabelColor = p_color1,
                    focusedIndicatorColor = p_color1,
                    cursorColor = p_color1
                ),
                modifier = Modifier
                    .heightIn(min = 45.dp)
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )


            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = phone,
                onValueChange = {
                    onChange("phone" , it)
                },
                textStyle = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = p_color1),
                label = {
                    Text(
                        text = "Phone",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack8),
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.phone),
                        contentDescription = null,
                        tint = p_color1,
                        modifier = Modifier.size(26.dp)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = customWhite0,
                    unfocusedContainerColor = customWhite0,
                    focusedLabelColor = p_color1,
                    focusedIndicatorColor = p_color1,
                    cursorColor = p_color1
                ),
                modifier = Modifier
                    .heightIn(min = 45.dp)
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    onChange("password" , it)
                },
                textStyle = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = p_color1),
                label = {
                    Text(
                        text = "Password",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack8),
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.password),
                        contentDescription = null,
                        tint = p_color1,
                        modifier = Modifier.size(26.dp)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = customWhite0,
                    unfocusedContainerColor = customWhite0,
                    focusedLabelColor = p_color1,
                    focusedIndicatorColor = p_color1,
                    cursorColor = p_color1
                ),
                modifier = Modifier
                    .heightIn(min = 45.dp)
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )


            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = passwordSecond,
                onValueChange = {
                    passwordSecond = it
                },
                textStyle = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = p_color1),
                label = {
                    Text(
                        text = "Repeat Password",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack8),
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.password),
                        contentDescription = null,
                        tint = p_color1,
                        modifier = Modifier.size(26.dp)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = customWhite0,
                    unfocusedContainerColor = customWhite0,
                    focusedLabelColor = p_color1,
                    focusedIndicatorColor = p_color1,
                    cursorColor = p_color1
                ),
                modifier = Modifier
                    .heightIn(min = 45.dp)
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )


        }


        Column(
            modifier = Modifier
                .height(120.dp)
        ){
            AlphaButton(
                "Sign up",
                backgroundColor = p_color1,
                onClick = {
                    onSignUp()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(start = 20.dp, end = 20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
//                        onNavigate(signUpScreen)
                    }
            ) {
                Text(
                    text = "I have an account",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = p_color1)
                )
            }


            Spacer(modifier = Modifier.height(26.dp))


        }

        Spacer(modifier = Modifier.height(55.dp))


    }

}


@Preview
@Composable
private fun SignUpPhoneLayout_prev() {
    SignUpPhoneLayout(
        firstName = "firstName",
        lastName  = "lastName",
        email     = "email",
        phone     = "phone",
        password  = "password",
        onChange = { _,_->

        },
        onSignUp = {  },
    )
}