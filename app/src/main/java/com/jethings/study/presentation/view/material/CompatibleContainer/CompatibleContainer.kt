package com.jethings.study.presentation.view.material

import androidx.compose.runtime.Composable
import com.jethings.study.util.responsiveScreenTools.WindowInfo
import com.jethings.study.util.responsiveScreenTools.rememberWindowInfo

@Composable
fun CompatibleContainer(
    compactContent : @Composable ()->Unit = {},
    mediumContent : @Composable ()->Unit = {},
    expandedContent : @Composable ()->Unit = {},
) {
    val windowInfo = rememberWindowInfo()

    if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
        compactContent()
    }
    else if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium){
        mediumContent()
    }
    else{
        expandedContent()
    }

}