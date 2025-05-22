package com.jethings.study.presentation.nvgraph

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
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
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jetapptech.business.presentation.view.screens.signUp.SignUpScreen
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.view.screens.academy.AcademyScreen
import com.jethings.study.presentation.view.screens.academy.viewModel.AcademyViewModel
import com.jethings.study.presentation.view.screens.academyOwnerList.AcademyOwnerList
import com.jethings.study.presentation.view.screens.addOwner.AddOwnerScreen
import com.jethings.study.presentation.view.screens.createAcademy.CreateAcademyScreen
import com.jethings.study.presentation.view.screens.createAcademy.viewModel.CreateAcademyViewModel
import com.jethings.study.presentation.view.screens.createSuperAdmin.CreateSuperAdminScreen
import com.jethings.study.presentation.view.screens.createSuperAdmin.viewModel.CreateSuperAdminViewModel
import com.jethings.study.presentation.view.screens.home.HomeScreen
import com.jethings.study.presentation.view.screens.home.viewModel.HomeViewModel
import com.jethings.study.presentation.view.screens.logIn.LogInScreen
import com.jethings.study.presentation.view.screens.logIn.viewModel.LogInViewModel
import com.jethings.study.presentation.view.screens.profile.ProfileScreen
import com.jethings.study.presentation.view.screens.signUp.signUpViewModel.SignUpViewModel
import com.jethings.study.presentation.view.screens.superAdmin.SuperAdminScreen
import com.jethings.study.presentation.view.screens.superAdmin.viewModel.SuperAdminViewModel
import com.jethings.study.util.responsiveScreenTools.WindowInfo
import org.koin.androidx.compose.koinViewModel
import kotlin.math.sign


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


            set_system_bars_color(customWhite0 , background_color_0)

            val viewModel : HomeViewModel = koinViewModel()

            HomeScreen(
                superAdminList = viewModel.superAdminList,
                academyList = viewModel.academyList,
                onEvent     = viewModel::onEvent,
                onNavigate = {
                    navController.navigate( it )
                },
                modifier    = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Rtl)
                    )
            )
        }

        /***************************** academy ****************************/

        composable<academyScreen> {

            val args = it.toRoute<academyScreen>()

            SideEffect {
                currentPage(academyScreen(args.academy_id))
            }


            set_system_bars_color(customWhite0 , background_color_0)

            val viewModel : AcademyViewModel = koinViewModel()

            AcademyScreen(
                academy = viewModel.academy,

                academyId = args.academy_id,
                onEvent = viewModel::onEvent,
                onNavigate = {
                    navController.navigate(it){

                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Rtl)
                    )
            )

        }

        /***************************** superAdmin ****************************/

        composable<superAdminScreen> {

            val args = it.toRoute<superAdminScreen>()

            SideEffect {
                currentPage(superAdminScreen(args.superAdmin_id))
            }


            set_system_bars_color(customWhite0 , background_color_0)

            val viewModel  = koinViewModel<SuperAdminViewModel>()

            SuperAdminScreen(
                superAdmin_id = args.superAdmin_id,
                superAdmin = viewModel.suprAdmin,
                onEvent = viewModel::onEvent,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Rtl)
                    )
            )

        }

        /***************************** profile ****************************/

        composable<profileScreen> {

            SideEffect {
                currentPage(profileScreen)
            }


            set_system_bars_color(customWhite0 , background_color_0)

            ProfileScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Rtl)
                    )
            )
        }

        /***************************** create academy ****************************/

        composable<createAcademyScreen> {

            SideEffect {
                currentPage(createAcademyScreen)
            }


            set_system_bars_color(customWhite0 , background_color_0)

            val viewModel : CreateAcademyViewModel = koinViewModel()

            CreateAcademyScreen(
                onEvent = viewModel::onEvent,
                onNavigate = {
                    when(it){
                        homeScreen -> {
                            navController.navigate(homeScreen) {
                                popUpTo(createAcademyScreen) {
                                    inclusive = true
                                }
                            }
                        }
                        else ->{


                        }                        }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Rtl)
                    )
            )
        }



        /***************************** create Super Admin ****************************/

        composable<createSuperAdminScreen> {

            SideEffect {
                currentPage(createSuperAdminScreen)
            }


            set_system_bars_color(customWhite0 , background_color_0)

            val viewModel = koinViewModel<CreateSuperAdminViewModel>()

            CreateSuperAdminScreen(
                onEvent = viewModel::onEvent,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Rtl)
                    )
            )
        }




        /***************************** add academy owner ****************************/

        composable<addOwnerScreen> {

            SideEffect {
                currentPage(addOwnerScreen)
            }


            set_system_bars_color(customWhite0 , background_color_0)


            AddOwnerScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Rtl)
                    )
            )
        }


        /*****************************  academy owner List ****************************/

        composable<academyOwnerList> {

            SideEffect {
                currentPage(academyOwnerList)
            }


            set_system_bars_color(customWhite0 , background_color_0)


            AcademyOwnerList(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Rtl)
                    )
            )
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
                            navController.navigate(homeScreen){
                                popUpTo(logInScreen){
                                    inclusive = true
                                }
                            }
                        }
                        signUpScreen ->{
                            navController.navigate(signUpScreen){
                                popUpTo(logInScreen){
                                    inclusive = true
                                }
                            }
                        }

                    }
                },
                windowInfo = windowInfo
            )
        }



        /***************************** sign up ****************************/

        composable<signUpScreen> {


            SideEffect {
                currentPage(signUpScreen)
            }

            val context = LocalContext.current


            set_system_bars_color(background_color_0 , background_color_0)

            val viewModel = koinViewModel<SignUpViewModel>()

            SignUpScreen(
                onEvent = viewModel::onEvent,
                onNavigation = {
                    navController.navigate(it){
                        popUpTo(signUpScreen){
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .padding(paddingValues),
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