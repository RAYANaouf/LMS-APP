package com.jethings.study.presentation.view.screens.profile.bottomSheet

import android.graphics.Paint.Align
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jethings.study.R
import com.jethings.study.data.db.entities.Account
import com.jethings.study.presentation.ui.theme.customBlack5
import com.jethings.study.presentation.ui.theme.customBlack6
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite5
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.util.objects.TextStyles


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfilePhotoBottomSheet(
    sheetState    : SheetState,
    onDismiss     : ()->Unit = {},
    profilePhoto  : Uri?,
    onUploadClick : (onSuccess : ()->Unit , onFailure : ()->Unit)->Unit ,
    modifier      : Modifier = Modifier
) {


    var searching by remember {
        mutableStateOf(false)
    }

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState,
        containerColor = customWhite0,
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxHeight()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(customWhite0)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 26.dp , bottom = 26.dp)
            ) {
                AsyncImage(
                    model = profilePhoto,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
            ) {

                if(
                    !searching
                ){
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 55.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                onUploadClick(
                                    {
                                        searching = false
                                    },{
                                        searching = false
                                    }
                                )
                                searching = true
                            }
                            .background(p_color1)
                    ) {
                        Text(
                            text = "Upload",
                            style = TextStyle(
                                color = customWhite0,
                            )
                        )
                    }
                }else{
                    val composition by rememberLottieComposition( LottieCompositionSpec.RawRes(R.raw.loading))
                    val progress = animateLottieCompositionAsState(composition = composition , iterations = LottieConstants.IterateForever)

                    LottieAnimation(
                        composition = composition ,
                        progress = progress.value ,
                        modifier = Modifier.fillMaxSize()
                    )
                }

            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun EditProfilePhotoBottomSheet_prev() {
    EditProfilePhotoBottomSheet(
        profilePhoto = null,
        onDismiss = {

        },
        onUploadClick = { s, f ->

        },
        sheetState = rememberModalBottomSheetState()
    )
}