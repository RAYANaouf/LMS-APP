package com.jethings.study.presentation.view.material

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jethings.study.MainEvent
import com.jethings.study.R
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.homeScreen
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color5
import com.jethings.study.util.objects.Constants.topBarMenu
import com.jethings.study.util.objects.TextStyles


@Composable
fun JethingsTopBar(
    title            : String = "",
    @DrawableRes img : Int? = null,
    elevation        : Dp = 1.dp,
    onClick          : (AppScreen) -> Unit = {},
    onEvent          : (MainEvent) -> Unit = {},
    modifier         : Modifier = Modifier,
) {


    var showMenu by rememberSaveable {
        mutableStateOf(false)
    }



    Surface(
        shadowElevation = elevation,
        color    = customWhite0 ,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.width(24.dp))

            if(img != null){
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .size(35.dp)
                ){
                    Image(
                        painter = painterResource(id = img) ,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            if(title != ""){
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxHeight()
                ){
                    Text(
                        text = title,
                        style    = TextStyles.Monospace_TextStyles.TextStyleSZ1.copy(color = if(title == "0 DA") p_color5 else p_color1  , fontFamily = FontFamily(Font(
                            R.raw.concert_one_regular)) ,  fontWeight = FontWeight(500),),
                        modifier = Modifier
                    )
                }
            }


            Spacer(modifier = Modifier
                .weight(1f)
            )


            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable {
                        showMenu = !showMenu
                    }
                    .padding(start = 16.dp , end = 16.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.menu) ,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                )
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = {
                        showMenu = false
                    },
                    modifier = Modifier
                ) {
                    topBarMenu.forEach { name ->
                        DropdownMenuItem(
                            text = {
                                Box(
                                    contentAlignment = Alignment.CenterStart,
                                    modifier = Modifier
                                        .height(45.dp)
                                        .fillMaxWidth()
                                ) {
                                    Text(text = name)
                                }
                            },
                            onClick = {
                                if(name == "Log Out"){
                                    onEvent(MainEvent.LogOutEvent())
                                }else{
                                    //onClick(homeScreen)
                                }

                            }
                        )
                    }
                }
            }


        }
    }

}


@Preview
@Composable
fun InAlphaTopBar_preview() {
//    InAlphaTopBar()
}