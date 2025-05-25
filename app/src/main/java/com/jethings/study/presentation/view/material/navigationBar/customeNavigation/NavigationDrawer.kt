package com.jethings.study.presentation.view.material.navigationBar.customeNavigation

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jethings.study.R
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color1_dark
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.ui.theme.p_color4
import com.jethings.study.util.objects.TextStyles
import kotlinx.coroutines.delay


@Composable
fun NavigationDrawer(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {} // Optional callback if needed
) {
    val menuItems = listOf("Profile", "Academies", "Log Out")

    Surface(
        color = background_color_0,
        tonalElevation = 4.dp,
        shadowElevation = 0.dp,
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(0.75f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {

            // Logo
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_d2),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = p_color2 , fontSize = 35.sp , fontWeight = FontWeight(900))){
                                append("J")
                            }
                            withStyle(style = SpanStyle(color = p_color1_dark , fontSize = 32.sp , fontWeight = FontWeight(600))){
                                append("-")
                            }
                            withStyle(style = SpanStyle(color = p_color1 , fontSize = 32.sp , fontWeight = FontWeight(600))){
                                append("Learn")
                            }
                        }
                    )
                }
            }


            Spacer(modifier = Modifier.height(55.dp))


            // Animated menu items
            AnimatedDrawerItem(
                icon = R.drawable.academy_icon,
                text = "Academy",
                delayMillis = 1 * 500L, // delay between items
                onClick = {
                    // handle navigation or logout here
                    onClose()
                }
            )
            AnimatedDrawerItem(
                icon = R.drawable.admin,
                text = "Super Admin",
                delayMillis = 2 * 500L, // delay between items
                onClick = {
                    // handle navigation or logout here
                    onClose()
                }
            )
            AnimatedDrawerItem(
                icon = R.drawable.teacher,
                text = "Teacher",
                delayMillis = 3 * 500L, // delay between items
                onClick = {
                    // handle navigation or logout here
                    onClose()
                }
            )
            AnimatedDrawerItem(
                icon = R.drawable.student,
                text = "Student",
                delayMillis = 4 * 500L, // delay between items
                onClick = {
                    // handle navigation or logout here
                    onClose()
                }
            )
        }
    }
}

@Composable
fun AnimatedDrawerItem(
    text : String,
    @DrawableRes icon : Int,
    delayMillis: Long,
    onClick: () -> Unit
) {
    val visible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(delayMillis)
        visible.value = true
    }

    AnimatedVisibility(
        visible = visible.value,
        enter = fadeIn(animationSpec = tween(durationMillis = 800)) + slideInHorizontally(
            initialOffsetX = { -it / 2 },
            animationSpec = tween(durationMillis = 800)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .clickable { onClick() }
                .padding(start = 8.dp)
        ) {

            Icon(
                painter = painterResource(id = icon),
                contentDescription =null,
                tint = p_color1,
                modifier = Modifier
                    .size(26.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
            )
        }
    }
}


@Preview
@Composable
private fun NavigationDrawer_prev() {
    NavigationDrawer(
        modifier = Modifier
            .fillMaxWidth()
    )
}