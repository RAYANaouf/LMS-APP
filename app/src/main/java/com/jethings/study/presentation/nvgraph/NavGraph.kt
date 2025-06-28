package com.jethings.study.presentation.nvgraph

import android.app.Activity
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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
import com.jethings.study.data.db.entities.Account
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.data.db.entities.entities.TrainingProgram
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.view.screens.AcademyHome.AcademyHome
import com.jethings.study.presentation.view.screens.TrainingProgram.trainingProgramScreen
import com.jethings.study.presentation.view.screens.TrainingProgram.viewModel.TrainingProgramViewModel
import com.jethings.study.presentation.view.screens.academy.AcademyScreen
import com.jethings.study.presentation.view.screens.academy.viewModel.AcademyViewModel
import com.jethings.study.presentation.view.screens.academyOwnerList.AcademyOwnerList
import com.jethings.study.presentation.view.screens.academyOwners.viewModel.AcademyOwnersViewModel
import com.jethings.study.presentation.view.screens.addOwner.AcademyOwnerScreen
import com.jethings.study.presentation.view.screens.createAcademy.CreateAcademyScreen
import com.jethings.study.presentation.view.screens.createAcademy.viewModel.CreateAcademyViewModel
import com.jethings.study.presentation.view.screens.createPost.CreatePost
import com.jethings.study.presentation.view.screens.createPost.viewmodel.CreatePostViewModel
import com.jethings.study.presentation.view.screens.createSuperAdmin.CreateSuperAdminScreen
import com.jethings.study.presentation.view.screens.createSuperAdmin.viewModel.CreateSuperAdminViewModel
import com.jethings.study.presentation.view.screens.createTrainingProgram.CreateTrainingPrograms
import com.jethings.study.presentation.view.screens.createTrainingProgram.viewModel.CreateTrainingProgramViewModel
import com.jethings.study.presentation.view.screens.home.HomeScreen
import com.jethings.study.presentation.view.screens.home.viewModel.HomeViewModel
import com.jethings.study.presentation.view.screens.logIn.LogInScreen
import com.jethings.study.presentation.view.screens.logIn.viewModel.LogInViewModel
import com.jethings.study.presentation.view.screens.manageAcademy.ManageAcademyScreen
import com.jethings.study.presentation.view.screens.profile.ProfileScreen
import com.jethings.study.presentation.view.screens.profile.viewModel.ProfileViewModel
import com.jethings.study.presentation.view.screens.signUp.signUpViewModel.SignUpViewModel
import com.jethings.study.presentation.view.screens.superAdmin.SuperAdminScreen
import com.jethings.study.presentation.view.screens.superAdmin.viewModel.SuperAdminViewModel
import com.jethings.study.util.responsiveScreenTools.WindowInfo
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(
    navController    : NavHostController = rememberNavController(),
    selectedAcademy  : Academy? = null,
    startDestination : AppScreen,
    currentPage      : (AppScreen)->Unit = {},
    currentScene     : (String   )->Unit = {},
    account          : Account? ,
    windowInfo       : WindowInfo,
    paddingValues    : PaddingValues
) {

    set_system_bars_color(
        statusBarColor     =  Color(0xffFFFFFF),
        navigationBarColor =  Color(0xffFFFFFF)
    )


    SharedTransitionLayout{

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
                    account             = account,
                    animatedVisibilityScope = this@composable,
                    superAdminList      = viewModel.superAdminList,
                    academyList         = viewModel.academyList,
                    trainingProgramList = viewModel.trainingProgramList,
                    postList            = viewModel.postList,
                    onEvent             = viewModel::onEvent,
                    onNavigate          = {
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
                val viewModel : AcademyViewModel = koinViewModel(
                    parameters = { parametersOf(args.academy_id)}
                )

                SideEffect {
                    currentPage(academyScreen(args.academy_id))
                }


                set_system_bars_color(customWhite0 , background_color_0)


                AcademyScreen(
                    animatedVisibilityScope = this,
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
                    animatedVisibilityScope = this,
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

                val viewModel = koinViewModel<ProfileViewModel>()

                ProfileScreen(
                    account = viewModel.user,
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

            composable<academyOwnerScreen> {

                val args = it.toRoute<academyOwnerScreen>()

                SideEffect {
                    currentPage(academyOwnerScreen(args.academy_id))
                }


                set_system_bars_color(customWhite0 , background_color_0)


                val viewModel = koinViewModel<AcademyOwnersViewModel>()


                AcademyOwnerScreen(
                    academyId = args.academy_id,
                    user      = viewModel.user,
                    owners    = viewModel.academyOwners,
                    onEvent   = viewModel::onEvent,
                    modifier  = Modifier
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



            /*****************************  academy home ****************************/

            composable<academyHome> {

                val args = it.toRoute<academyOwnerScreen>()

                SideEffect {
                    currentPage(academyHome(args.academy_id))
                }


                set_system_bars_color(customWhite0 , background_color_0)


                AcademyHome(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                            start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                            end = paddingValues.calculateEndPadding(LayoutDirection.Rtl)
                        )
                )
            }


            /*****************************  Create training program ****************************/

            composable<createTrainingProgram> {



                SideEffect {
                    currentPage(createTrainingProgram)
                }


                set_system_bars_color(customWhite0 , background_color_0)

                val viewModel = koinViewModel<CreateTrainingProgramViewModel>()

                CreateTrainingPrograms(
                    onEvent = viewModel::onEvent,
                    selectedAcademy = selectedAcademy,
                    onDone = {
                        navController.popBackStack()
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


            /*****************************   training program ****************************/

            composable<TrainingProgramScreen> {


                val args = it.toRoute<TrainingProgramScreen>()

                SideEffect {
                    currentPage(args)
                }


                set_system_bars_color(customWhite0 , background_color_0)

                val viewModel = koinViewModel<TrainingProgramViewModel>()

                trainingProgramScreen(
                    trainingProgram = TrainingProgram(
                        id = args.trainingProgram_id.toLong(),
                        name = args.title,
                        description = args.desc,
                        coverPhoto = args.coverPhoto
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                            start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                            end = paddingValues.calculateEndPadding(LayoutDirection.Rtl)
                        )
                )
            }


            /*****************************   create post    ****************************/

            composable<createPost> {

                SideEffect {
                    currentPage(createPost)
                }

                set_system_bars_color(customWhite0 , background_color_0)

                val viewModel = koinViewModel<CreatePostViewModel>()

                CreatePost(
                    onEvent = viewModel::onEvent,
                    selectedAcademy = selectedAcademy,
                    onDone = {
                        navController.navigate(homeScreen){
                            popUpTo<homeScreen> {
                                inclusive = true
                            }
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


            /*****************************   manage Academy ****************************/

            composable<manageAcademy> {

                SideEffect {
                    currentPage(manageAcademy)
                }


                set_system_bars_color(customWhite0 , background_color_0)

                //val viewModel = koinViewModel<TrainingProgramViewModel>()

                ManageAcademyScreen(
                    onNavigate = {
                        navController.navigate(it){
                            popUpTo(manageAcademy){
                                inclusive = true
                            }
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