package com.jethings.study

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.NavGraph
import com.jethings.study.presentation.nvgraph.academyHome
import com.jethings.study.presentation.nvgraph.academyScreen
import com.jethings.study.presentation.nvgraph.homeScreen
import com.jethings.study.presentation.nvgraph.logInScreen
import com.jethings.study.presentation.nvgraph.profileScreen
import com.jethings.study.presentation.nvgraph.signUpScreen
import com.jethings.study.presentation.ui.theme.StudyTheme
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.background_color_1
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color4
import com.jethings.study.presentation.view.material.AddFAB
import com.jethings.study.presentation.view.material.BottomAppBar
import com.jethings.study.presentation.view.material.JethingsTopBar
import com.jethings.study.presentation.view.material.navigationBar.NavigationBar
import com.jethings.study.presentation.view.material.navigationBar.customeNavigation.NavigationDrawer
import com.jethings.study.util.responsiveScreenTools.WindowInfo
import com.jethings.study.util.responsiveScreenTools.rememberWindowInfo
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyTheme {

                val viewModel = koinViewModel<MainViewModel>()

                val context = LocalContext.current


                Surface {
                    mainScreen (
                        startDestination = viewModel.startDestination,
                        viewModel = viewModel,
                        modifier = Modifier
                    )
                }

            }
        }
    }

}


@Composable
fun mainScreen(
    startDestination : AppScreen,
    viewModel        : MainViewModel,
    modifier         : Modifier = Modifier
) {

    //   set nav host controller with main view model
    val navController = rememberNavController()

    val windowInfo = rememberWindowInfo()

    var drawerState by remember{
        mutableStateOf(false)
    }

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density
    val screenWidthDp = remember{
        derivedStateOf { (configuration.screenWidthDp * density).roundToInt() }
    }

    val offsetValue by remember { derivedStateOf { (screenWidthDp.value / 6.0).dp }}
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState) offsetValue else 0.dp,
        label = "animatedOffset",
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (drawerState) 0.60f else 1f,
        label = "animatedOffset",
    )

    BackHandler(enabled = drawerState) {
        drawerState = false
    }

    Box(
        modifier = modifier
            .background(background_color_0)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .safeDrawingPadding()
                .align(Alignment.TopEnd)
                .size(85.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.close) ,
                contentDescription = null ,
                tint = p_color1,
                modifier = Modifier
                    .size(26.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null // This disables the ripple effect
                    ) {
                        drawerState = !drawerState
                    }
            )
        }

        val context = LocalContext.current

        Row {
            AnimatedVisibility(
                visible = drawerState
            ) {
                NavigationDrawer(
                    myAcademies = viewModel.myAcademies,
                    selectAcademy = viewModel.selectedAcademy,
                    account = viewModel.account,
                    onEvent = {event,onSuccess,onFailure->
                        viewModel.onEvent(
                            event,
                            onSuccess,
                            onFailure
                        )
                    },
                    onSelectAcademy = {
                        viewModel.onEvent(
                            MainEvent.SelectAcademy(it),{
                                navController.navigate(academyScreen(it.id)){
                                    popUpTo(homeScreen){
                                        inclusive = true
                                    }
                                }
                                drawerState = false
                                Toast.makeText(context , "select academy" , Toast.LENGTH_SHORT).show()
                            },{
                                Toast.makeText(context , "failed to select academy" , Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    onClick = {
                        navController.navigate(academyScreen(it))
                    },
                    onClose = {
                        drawerState = false
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                )
            }
        }

        Surface(
            shape = RoundedCornerShape( if(drawerState) 14.dp else 0.dp ),
            shadowElevation = 12.dp,
            color = background_color_0,
            modifier = Modifier
                .offset(animatedOffset)
                .scale(animatedScale)
                .fillMaxSize()
        ) {
            Scaffold(
                topBar = {
                    Column(
                        modifier = Modifier
                            .windowInsetsPadding(WindowInsets.statusBars)
                    ) {
                        AnimatedVisibility(visible = viewModel.show_topbar && windowInfo.screenWidthInfo !is WindowInfo.WindowType.Expanded) {

                            Log.d("test : " , viewModel.selectedAcademy.toString())

                            JethingsTopBar(
                                drawableImg = viewModel.topBarImg,
                                img         = viewModel.selectedAcademy?.logo ?: "",
                                drawableRes = viewModel.selectedAcademy == null,
                                title       = if(viewModel.selectedAcademy != null) viewModel.selectedAcademy?.name ?: "" else viewModel.topBarTxt,
                                elevation   = viewModel.topbar_shadow,
                                onEvent = {
                                    if (it is MainEvent.LogOutEvent){
                                        viewModel.onEvent(
                                            it,{
                                                navController.navigate(logInScreen){
                                                    popUpTo(logInScreen){
                                                        inclusive = true
                                                    }
                                                }
                                            },{

                                            }
                                        )
                                    }else{
                                        viewModel.onEvent(it)
                                    }
                                },
                                onClick = {
                                    when(it){
                                        "Img" ->{
                                            drawerState = !drawerState
                                        }
                                    }
                                },
                                modifier  = Modifier
                                    .height(55.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                },
                bottomBar = {
                    Column {
                        AnimatedVisibility(visible = viewModel.show_bottombar && windowInfo.screenWidthInfo !is WindowInfo.WindowType.Expanded , modifier = Modifier.height(350.dp + 45.dp)) {
                            AddFAB(
                                showFAB = viewModel.current_screen != profileScreen,
                                onNavigate = {
                                    navController.navigate(it)
                                }
                            )
                        }
                    }
                },
                containerColor = background_color_0,
                modifier = Modifier
                    .fillMaxSize()
            ) {padding->

                NavGraph(
                    selectedAcademy = viewModel.selectedAcademy,
                    navController = navController,
                    startDestination = startDestination,
                    currentPage = {
                        viewModel.onEvent(MainEvent.ScreenChangeEvent(it))
                    },
                    currentScene = {
                        ""
                    },
                    account       = viewModel.account,
                    windowInfo    = windowInfo ,
                    paddingValues = padding
                )


            }
        }


        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            AnimatedVisibility(visible = drawerState) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(bottom = 45.dp)
                        .fillMaxWidth()
                ){
                    Text(
                        text = "Powered by Jethings",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }



}


