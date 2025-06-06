package com.jethings.study.presentation.view.screens.logIn

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.homeScreen
import com.jethings.study.presentation.view.screens.logIn.events.LogInEvents
import com.jethings.study.presentation.view.screens.logIn.responsiveLayout.phone.TabletLayout
import com.jethings.study.util.responsiveScreenTools.WindowInfo
import com.jethings.study.util.responsiveScreenTools.rememberWindowInfo


@Composable
fun LogInScreen(
    modifier   : Modifier = Modifier,
    onEvent    : (event: LogInEvents, onSucces : () -> Unit, onFailure : ()-> Unit)->Unit = {_,_,_->},
    onNavigate : (AppScreen)->Unit = {},
    windowInfo : WindowInfo = rememberWindowInfo(),
) {


    var gmail by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }


    if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact || windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium){

        PhoneLayout(
            gmail = gmail,
            onGmailChange = {
                gmail = it
            },
            password = password,
            onPasswordChange = {
                password = it
            },
            onNavigate = {screen->
                onNavigate(screen)
            },
            onLogIn = { onFailure ->
                onEvent(
                    LogInEvents.LogIn(
                        email = gmail,
                        password = password
                    ),{
                        onNavigate(homeScreen)
                    },{
                        onFailure()
                    }
                )
            },
            modifier = modifier
        )
    }
    else{
        TabletLayout(
            gmail = gmail,
            onGmailChange = {
                gmail = it
            },
            password = password,
            onPasswordChange = {
                password = it
            },
            onNavigate = {screen->
                onNavigate(screen)
            },
            onLogIn = {
                onEvent(
                    LogInEvents.LogIn(
                        email = gmail,
                        password = password
                    ),{

                    },{

                    }
                )
            },
            modifier = modifier
        )
    }

}


@Preview(
    name = "360×800 (8.56%)",
    device = "spec:width=360dp,height=800dp",
    showSystemUi = true
)
@Composable
private fun LogInScreen_preview1() {
    LogInScreen()
}

@Preview(
    name = "414×896 (6.95%)",
    device = "spec:width=414dp,height=896dp",
    showSystemUi = true
)
@Composable
private fun LogInScreen_preview2() {
    LogInScreen()
}




@Preview(
    name = "Tab 10",
    device = "spec:width=800dp,height=1280dp",
    showSystemUi = true
)
@Composable
private fun LogInScreen_preview3() {
    LogInScreen()
}


@Preview(
    name = "Nexus_9",
    device = "spec:width=768dp,height=1024dp",
    showSystemUi = true
)
@Composable
private fun LogInScreen_preview5() {
    LogInScreen()
}

@Preview(
    name = "Landscape Tab 10",
    device = "spec:width=1280dp,height=800dp",
    showSystemUi = true
)
@Composable
private fun LogInScreen_preview4() {
    LogInScreen()
}

@Preview(
    name = "Landscape Nexus_9",
    device = "spec:width=1024dp,height=768dp",
    showSystemUi = true
)
@Composable
private fun LogInScreen_preview6() {
    LogInScreen()
}







//tablet


