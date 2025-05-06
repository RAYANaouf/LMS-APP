package com.jethings.study.presentation.view.material

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.R
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.homeScreen
import com.jethings.study.presentation.ui.theme.StudyTheme
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite7
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.view.material.bottomBar.components.Circle

@Composable
fun BottomAppBar(
    onNavigate    : (AppScreen)->Unit = {}
) {

    val isMenuExtended = remember { mutableStateOf(false) }

    val clickAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing
        )
    )


    BottomAppBar(
        onNavigate             = onNavigate,
        clickAnimationProgress = clickAnimationProgress
    ) {
        isMenuExtended.value = isMenuExtended.value.not()
    }
}

@Composable
fun BottomAppBar(
    clickAnimationProgress : Float = 0f,
    onNavigate             : (AppScreen)->Unit = {},
    toggleAnimation        : () -> Unit = { }
) {
    Box(
        Modifier
            .padding(start = 25.dp , end = 25.dp, bottom = 45.dp),
        contentAlignment = Alignment.BottomCenter
    ) {

        BottomNavigationBody(
            onNavigate = onNavigate
        )


        Circle(
            color = p_color1.copy(alpha = 0.5f),
            animationProgress = 0.5f
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(bottom = 45.dp)
                .size(54.dp)
                .clip(CircleShape)
                .background(customWhite7)
                .clickable {
                    //onNavigate(addCarScreen)
                    toggleAnimation()
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.car_icon),
                contentDescription = null,
                tint = customWhite0,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(30.dp)
            )
        }

        Circle(
            color = customWhite7,
            animationProgress = clickAnimationProgress
        )
    }
}



@Composable
fun BottomNavigationBody(
    onNavigate : (AppScreen)->Unit = {}
) {

    Box {
        Image(
            painter = painterResource(R.drawable.bottom_navigation),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            listOf(Icons.Filled.Home, Icons.Filled.Person).map { image ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable {
                            when (image) {
                                Icons.Filled.Home -> {
                                    onNavigate(homeScreen)
                                }

                                Icons.Filled.Person -> {
                                    //onNavigate(profileScreen)
                                }
                            }
                        }
                ) {
                    Icon(
                        imageVector = image,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        }
    }

}




@Composable
@Preview(device = "id:pixel_4a", showBackground = true, backgroundColor = 0xFF3A2F6E)
private fun MainScreenPreview() {
    StudyTheme {
        BottomAppBar()
    }
}