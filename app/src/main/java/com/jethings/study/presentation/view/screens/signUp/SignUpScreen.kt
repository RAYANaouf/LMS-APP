package com.jetapptech.business.presentation.view.screens.signUp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.data.api.req_res_classes.SignUpFailureResponse
import com.jethings.study.data.api.req_res_classes.SignUpRequest
import com.jethings.study.data.api.req_res_classes.SignUpResponse
import com.jethings.study.data.api.req_res_classes.SignUpSuccessResponse
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.homeScreen
import com.jethings.study.presentation.view.screens.signUp.responsiveLayout.SignUpPhoneLayout
import com.jethings.study.presentation.view.screens.signUp.signUpEvents.SignUpEvents


@Composable
fun SignUpScreen(
    modifier   : Modifier = Modifier,
    onEvent    : (SignUpEvents, onSuccess:(SignUpSuccessResponse)->Unit, onFailure:(SignUpFailureResponse)->Unit, onException: (Exception)->Unit )->Unit = { e, s, f, ex -> },
    //windowInfo : WindowInfo = rememberWindowInfo(),
    onNavigation : (AppScreen)->Unit = {}
) {

    /************************** vars  *****************************/
    //logic var
    var firstName by rememberSaveable {
        mutableStateOf("")
    }
    var lastName by rememberSaveable {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var phone by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current

    SignUpPhoneLayout(
        firstName = firstName,
        lastName  = lastName ,
        email     = email,
        phone     = phone,
        password  = password,
        onChange = { variable , value ->
            when(variable){
                "firstName"->{
                    firstName = value
                }
                "lastName"->{
                    lastName = value
                }
                "email"->{
                    email = value
                }
                "phone"->{
                    phone = value
                }
                "password"->{
                    password = value
                }
            }
        },
        onSignUp = {
            onEvent(
                SignUpEvents.SignUpBtnClicked(firstName=firstName,lastName = lastName,email=email, password=password,phone=phone),{
                    Toast.makeText(context , "Sign Up seccessfully" , Toast.LENGTH_LONG).show()
                    onNavigation(homeScreen             )
                },{
                    Toast.makeText(context , "Failure : ${it}" , Toast.LENGTH_LONG).show()
                },{
                    Toast.makeText(context , "Oops Exception." , Toast.LENGTH_LONG).show()
                }
            )
        },
        modifier = modifier
    )
    /*

    if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact || windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium ){
        SignUpPhoneLayout(
            firstName = firstName,
            lastName  = lastName ,
            email     = email,
            phone     = phone,
            password  = password,
            onChange = { variable , value ->
                when(variable){
                    "firstName"->{
                        firstName = value
                    }
                    "lastName"->{
                        lastName = value
                    }
                    "email"->{
                        email = value
                    }
                    "phone"->{
                        phone = value
                    }
                    "password"->{
                        password = value
                    }
                }
            },
            onSignUp = {
                onEvent(
                    SignUpEvents.SignUpBtnClicked(name=firstName,email=email, password=password,phone=phone),
                    {
                        onNavigation(homeScreen)
                    },
                    {
                        println("Failure : ${it}")
                    },
                    {

                    }
                )
            },
            modifier = modifier
        )
    }
    else{
        SignUpTabletLayout(
            firstName = firstName,
            lastName  = lastName ,
            email     = email,
            phone     = phone,
            password  = password,
            onChange = { variable , value ->
                when(variable){
                    "firstName"->{
                        firstName = value
                    }
                    "lastName"->{
                        lastName = value
                    }
                    "email"->{
                        email = value
                    }
                    "phone"->{
                        phone = value
                    }
                    "password"->{
                        password = value
                    }
                }
            },
            modifier = modifier
        )
    }*/
}

