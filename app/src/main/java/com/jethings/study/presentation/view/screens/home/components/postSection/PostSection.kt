package com.jethings.study.presentation.view.screens.home.components.postSection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCancellationBehavior
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jethings.study.R
import com.jethings.study.data.db.entities.entities.Post
import com.jethings.study.presentation.ui.theme.customBlack5
import com.jethings.study.presentation.ui.theme.customBlack7
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.util.objects.TextStyles
import kotlinx.coroutines.launch


@Composable
fun PostSection(
    postList : List<Post> = emptyList(),
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {
        postList.forEach { post ->

            val composition by rememberLottieComposition( LottieCompositionSpec.RawRes(R.raw.heart))
            val animatable = rememberLottieAnimatable()


            var liked by rememberSaveable {
                mutableStateOf(false)
            }
            val scope = rememberCoroutineScope()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp , vertical = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(customWhite0)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(45.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(30.dp)
                            .background(customWhite1)
                    ){
                        AsyncImage(
                            model = post.academy?.logo,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        post.academy?.name ?: ""
                    )
                }

                if(post.title != ""){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp , end = 12.dp , bottom = 8.dp)
                    ) {
                        Text(
                            text = post.title,
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = customBlack5)
                        )
                    }
                }

                AsyncImage(
                    model = post.photo,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(customWhite1)
                )
                if(post.content != ""){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp , end = 12.dp , top = 8.dp)
                    ) {
                        Text(
                            text = post.content,
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = customBlack5)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .height(45.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable {
                                if (!liked) {
                                    liked = true
                                    scope.launch {
                                        animatable.animate(
                                            composition = composition,
                                            speed = 3f,
                                            cancellationBehavior = LottieCancellationBehavior.OnIterationFinish
                                        )
                                    }
                                } else {
                                    liked = false

                                }
                            }
                    ){

                        LottieAnimation(
                            composition = composition ,
                            progress = if (liked) animatable.progress else 0f ,
                            modifier = Modifier
                                .size(45.dp)
                                .background(customWhite1)
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ){
                        Icon(
                            painter = painterResource(R.drawable.commenter),
                            contentDescription = null,
                            tint = customBlack7,
                            modifier = Modifier
                                .size(25.dp)
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                    ){

                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun PostSection_prev() {
    PostSection()
}