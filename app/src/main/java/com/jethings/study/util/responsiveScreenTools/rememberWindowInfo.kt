package com.jethings.study.util.responsiveScreenTools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberWindowInfo() : WindowInfo {
    val configuration = LocalConfiguration.current
    return remember(configuration){
        WindowInfo(
            screenWidthInfo = when{
                configuration.screenWidthDp < 600 -> WindowInfo.WindowType.Compact
                configuration.screenWidthDp < 840 -> WindowInfo.WindowType.Medium
                else -> WindowInfo.WindowType.Expanded
            },
            screenHeightInfo = when{
                configuration.screenHeightDp < 480 -> WindowInfo.WindowType.Compact
                configuration.screenHeightDp < 900 -> WindowInfo.WindowType.Medium
                else -> WindowInfo.WindowType.Expanded
            },
            screenWidth  = configuration.screenWidthDp.dp,
            screenHeight = configuration.screenHeightDp.dp
        )
    }
}


data class WindowInfo(
    val screenWidthInfo  : WindowType,
    val screenHeightInfo : WindowType,
    val screenWidth      : Dp,
    val screenHeight     : Dp
){
    sealed class WindowType{
        object Compact  : WindowType()
        object Medium   : WindowType()
        object Expanded : WindowType()
    }

}