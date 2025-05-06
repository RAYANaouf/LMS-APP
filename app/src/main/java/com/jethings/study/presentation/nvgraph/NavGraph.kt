package com.jethings.study.presentation.nvgraph

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.view.screens.logIn.LogInScreen
import com.jethings.study.presentation.view.screens.logIn.viewModel.LogInViewModel
import com.jethings.study.util.responsiveScreenTools.WindowInfo
import org.koin.androidx.compose.koinViewModel


@Composable
fun NavGraph(
    navController    : NavHostController = rememberNavController(),
    startDestination : AppScreen,
    currentPage      : (AppScreen)->Unit = {},
    currentScene     : (String   )->Unit = {},
    windowInfo       : WindowInfo,
    paddingValues    : PaddingValues
) {

    set_system_bars_color(
        statusBarColor     =  Color(0xffFFFFFF),
        navigationBarColor =  Color(0xffFFFFFF)
    )


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier         = Modifier
    ) {



        /***************************** home ****************************/

        composable<homeScreen> {

            SideEffect {
                currentPage(homeScreen)
            }


            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(text = "Home")
            }
        }



        /***************************** log in ****************************/

        composable<logInScreen> {


            SideEffect {
                currentPage(logInScreen)
            }

            val context = LocalContext.current


            set_system_bars_color(background_color_0 , background_color_0)

            val viewModel : LogInViewModel = koinViewModel()

            LogInScreen(
                modifier = Modifier
                    .padding(paddingValues),
                onEvent = viewModel::onEvent,
                onNavigate = {
                    when(it){
                        homeScreen -> {
                            navController.navigate(homeScreen)
                        }
                    }
                },
                windowInfo = windowInfo
            )
        }


        /***************************** on boarding ****************************/

        composable<onBoardingScreen> {


            SideEffect {
                currentPage(onBoardingScreen)
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(text = "on Boarding...")
            }

        }


    }






}


@Composable
fun set_system_bars_color(
    statusBarColor     : Color,
    navigationBarColor : Color
) {
    val window1 = LocalView.current.context as Activity

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            window1.window.statusBarColor     = statusBarColor.toArgb()
            window1.window.navigationBarColor = navigationBarColor.toArgb()

        }
    }

}