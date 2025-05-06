package com.jethings.study.presentation.view.material

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.R
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.homeScreen
import com.jethings.study.presentation.ui.theme.StudyTheme
import com.jethings.study.presentation.ui.theme.customWhite7
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.view.material.bottomBar.components.Circle
import com.jethings.study.presentation.view.material.bottomBar.components.FabGroup


@RequiresApi(Build.VERSION_CODES.S)
private fun getRenderEffect(): RenderEffect {
    val blurEffect = RenderEffect
        .createBlurEffect(80f, 80f, Shader.TileMode.MIRROR)

    val alphaMatrix = RenderEffect.createColorFilterEffect(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    1f, 0f, 0f, 0f, 0f,
                    0f, 1f, 0f, 0f, 0f,
                    0f, 0f, 1f, 0f, 0f,
                    0f, 0f, 0f, 50f, -5000f
                )
            )
        )
    )

    return RenderEffect
        .createChainEffect(alphaMatrix, blurEffect)
}

@Composable
fun AddFAB(
    bottomBarType : Int = 0,
    addIndicator  : Boolean = false,
    onNavigate    : (AppScreen)->Unit = {}
) {

    val isMenuExtended = remember { mutableStateOf(false) }

    val fabAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 650,
            easing = LinearEasing
        )
    )

    val clickAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing
        )
    )

    val renderEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        getRenderEffect().asComposeRenderEffect()
    } else {
        null
    }

    AddFAB(
        renderEffect = renderEffect,
        fabAnimationProgress = fabAnimationProgress,
        addIndicator         = addIndicator,
        onNavigate             = onNavigate,
        clickAnimationProgress = clickAnimationProgress
    ) {
        isMenuExtended.value = isMenuExtended.value.not()
    }
}

@Composable
fun AddFAB(
    renderEffect           : androidx.compose.ui.graphics.RenderEffect?,
    fabAnimationProgress   : Float = 0f,
    clickAnimationProgress : Float = 0f,
    addIndicator          : Boolean    ,
    onNavigate             : (AppScreen)->Unit = {},
    toggleAnimation        : () -> Unit = { }
) {
    Box(
        Modifier
            .padding(start = 25.dp , end = 25.dp, bottom = 45.dp),
        contentAlignment = Alignment.BottomCenter
    ) {

        AddFABBody(
            onNavigate = onNavigate
        )

        if(addIndicator && fabAnimationProgress == 0f){
            Image(
                painter = painterResource(id = R.drawable.add_effect),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding(bottom = 55.dp)
                    .width(250.dp)
                    .align(Alignment.BottomCenter)
            )
        }


        Circle(
            color = p_color2.copy(alpha = 0.5f),
            animationProgress = 0.5f
        )

        FabGroup(
            renderEffect = renderEffect,
            animationProgress = fabAnimationProgress
        )
        FabGroup(
            renderEffect = null,
            animationProgress = fabAnimationProgress,
            toggleAnimation = toggleAnimation
        )
        Circle(
            color = p_color2,
            animationProgress = clickAnimationProgress
        )
    }
}



@Composable
fun AddFABBody(
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