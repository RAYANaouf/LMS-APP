package com.jethings.study.presentation.view.material.navigationBar.customeNavigation

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jethings.study.MainEvent
import com.jethings.study.R
import com.jethings.study.data.db.entities.Account
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.data.db.entities.entities.TrainingProgram
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.homeScreen
import com.jethings.study.presentation.nvgraph.profileScreen
import com.jethings.study.presentation.nvgraph.settingScreen
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color1_dark
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.ui.theme.p_color3
import com.jethings.study.presentation.ui.theme.p_color4
import com.jethings.study.presentation.ui.theme.p_color5
import com.jethings.study.util.objects.TextStyles
import kotlinx.coroutines.delay


@Composable
fun NavigationDrawer(
    onEvent   : (MainEvent , onSuccess : ()->Unit , onFailure : ()->Unit , )->Unit = {_,_,_->},
    account   : Account?,
    myAcademies : List<Academy> = listOf(Academy(id = -1)),
    modifier  : Modifier = Modifier,
    onSelectAcademy : (Academy)->Unit = {},
    selectAcademy   : Academy? = null,
    onNavigate   : (AppScreen) -> Unit = {},
    onClose      : () -> Unit = {} // Optional callback if needed
) {


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



            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 65.dp)
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                val items = remember {
                    mutableStateListOf<navItem>()
                }

                LaunchedEffect(account) {
                    if (account != null && account.isSuperAdmin){
                        items.add(navItem(name = "Academy" , icon = R.drawable.academy_icon))
                        items.add(navItem(name = "Super Admin" , icon = R.drawable.admin))
                        items.add(navItem(name = "Teacher" , icon = R.drawable.teacher))
                        items.add(navItem(name = "Student" , icon = R.drawable.student))
                    }

                    if(account != null){
                        items.add(navItem(name = "Home"     , icon = R.drawable.home))
                        items.add(navItem(name = "Profile"  , icon = R.drawable.user))
                    }
                    if (account != null && account.ownedAcademies > 0){
                        if (myAcademies.size == 1 && myAcademies[0].id == -1){
                            onEvent(
                                MainEvent.GetMyAcademiesEvent(account.userId ),{

                                },{

                                }
                            )
                        }
                        items.add(navItem(name = "My Academy" , icon = R.drawable.academy_icon))
                        items.add(navItem(name = "Statistics" , icon = R.drawable.statistics))
                    }
                    if(account != null){
                        items.add(navItem(name = "Settings" , icon = R.drawable.settings))
                    }
                }

                items.forEachIndexed { index, navItem ->
                    if (navItem.name == "My Academy"){
                        if (account == null)
                            return@forEachIndexed
                        // Animated menu items
                        ExpendedDrawerItem(
                            icon = R.drawable.academy_icon,
                            text = "My Academy",
                            notification = account.ownedAcademies,
                            myAcademies = myAcademies,
                            selectedAcademy = selectAcademy,
                            delayMillis = index *  500L, // delay between items
                            onClick = {
                                onSelectAcademy(it)
                            }
                        )
                    }
                    else{
                        DrawerItem(
                            icon = navItem.icon,
                            text = navItem.name,
                            delayMillis = index * 500L, // delay between items
                            onClick = {
                                // handle navigation or logout here
                                if (navItem.name == "Profile"){
                                    onNavigate(profileScreen)
                                }else if(navItem.name == "Home"){
                                    onNavigate(homeScreen)
                                }else if(navItem.name == "Settings"){
                                    onNavigate(settingScreen)
                                }
                                onClose()
                            }
                        )
                    }

                }

            }

        }
    }
}

@Composable
fun DrawerItem(
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


@Composable
fun ExpendedDrawerItem(
    text : String,
    @DrawableRes icon : Int,
    notification      : Int = -1,
    selectedAcademy   : Academy? = null,
    myAcademies       : List<Academy> = listOf(Academy(id = -1)),
    delayMillis       : Long,
    onClick           : (Academy) -> Unit
) {
    val visible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(delayMillis)
        visible.value = true
    }

    var expended by remember{
        mutableStateOf(false)
    }

    AnimatedVisibility(
        visible = visible.value,
        enter = fadeIn(animationSpec = tween(durationMillis = 800)) + slideInHorizontally(
            initialOffsetX = { -it / 2 },
            animationSpec = tween(durationMillis = 800)
        )
    ) {
        Column(
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    expended = !expended
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(55.dp)
                    .padding(start = 8.dp)
            ) {

                Icon(
                    painter = painterResource(id = icon),
                    contentDescription =null,
                    tint = p_color1,
                    modifier = Modifier
                        .size(26.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = text,
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                )
                Spacer(modifier = Modifier.width(12.dp))
                if (notification > 0){
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(19.dp)
                            .clip(CircleShape)
                            .background(p_color5)
                    ){
                        Text(
                            text = "${notification}",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ11.copy(color = customWhite0)
                        )
                    }
                }
            }

            AnimatedVisibility(visible = expended) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {



                    if (myAcademies.size == 1 && myAcademies[0].id == -1){
                        val composition by rememberLottieComposition( LottieCompositionSpec.RawRes(R.raw.loading))
                        val progress = animateLottieCompositionAsState(composition = composition , iterations = LottieConstants.IterateForever)

                        LottieAnimation(
                            composition = composition ,
                            progress = progress.value ,
                            modifier = Modifier
                                .heightIn(max = 50.dp)
                        )
                    }
                    else{
                        myAcademies.forEachIndexed { index, academy ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .clickable {
                                        onClick(academy)
                                    }
                                    .padding(horizontal = 8.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(26.dp)
                                ) {
                                    Box(
                                        contentAlignment = Alignment.TopCenter,
                                        modifier = Modifier
                                            .fillMaxHeight(if (index == myAcademies.size - 1) 0.5f else 1f)
                                    ){
                                        Spacer(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .width(2.dp)
                                                .background(p_color1)
                                        )
                                        if(selectedAcademy!= null && selectedAcademy.id == academy.id){
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .offset(y = 5.dp)
                                                    .clip(CircleShape)
                                                    .background(p_color1)
                                                    .align(if (index == myAcademies.size - 1) Alignment.BottomCenter else Alignment.Center)
                                            )
                                        }
                                    }


                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                AsyncImage(
                                    model = academy.logo,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(32.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = academy.name,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}




private data class navItem(
    val name : String ,
    val icon : Int
)


@Preview
@Composable
private fun NavigationDrawer_prev() {
    NavigationDrawer(
        account = Account(),
        modifier = Modifier
            .fillMaxWidth()
    )
}
