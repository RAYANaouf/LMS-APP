package com.jethings.study.presentation.view.material.bottomBar.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.jethings.study.R
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.createAcademyScreen
import com.jethings.study.presentation.nvgraph.createSuperAdminScreen
import com.jethings.study.presentation.ui.theme.customWhite7
import com.jethings.study.presentation.ui.theme.p_color2


@Composable
fun FabGroup(
    animationProgress: Float = 0f,
    renderEffect: androidx.compose.ui.graphics.RenderEffect? = null,
    toggleAnimation: () -> Unit = { },
    onNavigate : (AppScreen) -> Unit = {}
) {


    Box(
        Modifier
            .fillMaxSize()
            .graphicsLayer { this.renderEffect = renderEffect }
            .padding(bottom = 45.dp),
        contentAlignment = Alignment.BottomCenter
    ) {



        AnimatedFab(
            img = painterResource(id = R.drawable.academy_icon),
            modifier = Modifier
                .padding(
                    bottom = 72.dp * FastOutSlowInEasing.transform(animationProgress),
                    end = 200.dp * FastOutSlowInEasing.transform(animationProgress)
                )
                .clip(CircleShape),
            backgroundColor = p_color2,
            opacity = LinearEasing.transform(animationProgress),
            onClick = {
                onNavigate(createAcademyScreen)
            }

        )

        AnimatedFab(
            img = painterResource(id = R.drawable.admin),
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 88.dp * FastOutSlowInEasing.transform(animationProgress),
                    )
                )
                .clip(CircleShape),
            backgroundColor = p_color2,
            opacity = LinearEasing.transform( animationProgress),
            onClick = {
                onNavigate(createSuperAdminScreen)
            }
        )

        AnimatedFab(
            img = painterResource(id = R.drawable.compaign),
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 72.dp * FastOutSlowInEasing.transform(animationProgress),
                        start = 200.dp * FastOutSlowInEasing.transform(animationProgress)
                    )
                )
                .clip(CircleShape),
            backgroundColor = p_color2,
            opacity = LinearEasing.transform( animationProgress),
            onClick = {

            }
        )


        AnimatedFab(
            img = painterResource(id = R.drawable.close),
            modifier = Modifier
                .scale(LinearEasing.transform(animationProgress))
                .clip(CircleShape),
            onClick = toggleAnimation,
            backgroundColor = customWhite7
        )

        AnimatedFab(
            img = painterResource(id = R.drawable.add),
            modifier = Modifier
                .rotate(
                    225 * FastOutSlowInEasing.transform(animationProgress)
                )
                .scale(1f - LinearEasing.transform(animationProgress))
                .clip(CircleShape),
            onClick = toggleAnimation,
            backgroundColor = customWhite7
        )


    }
}
