package com.jethings.study.presentation.view.material.navigationBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jethings.study.R
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.util.objects.TextStyles

@Composable
fun NavigationBar(
    modifier       : Modifier = Modifier ,
    gesturesEnable : Boolean  = true     ,
    content        : @Composable () -> Unit
) {
    DismissibleNavigationDrawer(
        gesturesEnabled = gesturesEnable,
        drawerContent = {
            Surface(
                color = background_color_0,
                shadowElevation = 6.dp,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .blur(2.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.app_store_logo_none_background),
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "Profile",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ4.copy(color = customBlack4)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "Academies",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ4.copy(color = customBlack4)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "Log Out",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ4.copy(color = customBlack4)
                        )
                    }
                }
            }
        },
        modifier = modifier
    ){
        content()
    }
}