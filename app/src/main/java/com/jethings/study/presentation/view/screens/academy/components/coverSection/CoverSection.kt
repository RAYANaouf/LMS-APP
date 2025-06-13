package com.jethings.study.presentation.view.screens.academy.components.coverSection

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customWhite2
import com.jethings.study.presentation.ui.theme.customWhite5
import com.jethings.study.util.objects.TextStyles


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CoverSection(
    academyId : Int,
    animatedVisibilityScope : AnimatedVisibilityScope,
    academy   : Academy? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(customWhite2)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
            //.offset(y = 120.dp)
        ) {
            Box(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "Academy-${academyId}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(customWhite5)
            ) {
                AsyncImage(
                    model = academy?.logo,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(40.dp)
                    .widthIn(max = 150.dp)
            ) {
                Text(
                    text = academy?.name  ?: "Loading..."     ,
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                )
            }
        }
    }
}


@Preview
@Composable
private fun CoverSerction_prev() {

}