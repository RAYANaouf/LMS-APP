package com.jethings.study

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.NavGraph
import com.jethings.study.presentation.nvgraph.logInScreen
import com.jethings.study.presentation.ui.theme.StudyTheme
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.util.responsiveScreenTools.rememberWindowInfo
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyTheme {

                val viewModel = koinViewModel<MainViewModel>()

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


    Scaffold(
        topBar = {
        },
        bottomBar = {
        },
        containerColor = background_color_0,
        modifier = modifier
    ) {padding->

        NavGraph(
            navController = navController,
            startDestination = startDestination,
            currentPage = {
                //viewModel.setCurrentScreen(it)
                logInScreen
            },
            currentScene = {
                ""
            },
            windowInfo    = windowInfo ,
            paddingValues = padding
        )


    }

}


