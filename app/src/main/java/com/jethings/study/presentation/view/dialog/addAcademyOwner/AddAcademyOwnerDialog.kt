package com.jethings.study.presentation.view.dialog.addAcademyOwner

import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.jethings.study.R
import com.jethings.study.data.db.entities.Account
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.presentation.ui.theme.customBlack8
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.customWhite2
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color1_dark
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.ui.theme.p_color3
import com.jethings.study.util.objects.TextStyles


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAcademyOwnerDialog(
    showDialog : Boolean = false,
    user : Account? = null,
    onDismissRequest: () -> Unit ={},
    onAddOwner: (Int , ()->Unit , ()->Unit) -> Unit ={_,_,_->},
    onSearch: (String , ()->Unit , ()->Unit) -> Unit ={_,_,_->},
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    var search by remember{
        mutableStateOf("")
    }
    var loading by rememberSaveable {
        mutableStateOf(false)
    }
    var userSelected by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = user){
        if (user == null){
            userSelected = false
        }
    }

    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = {
                onDismissRequest()
            },
            modifier = modifier
                .fillMaxWidth()
                ,
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(background_color_0)
                        .border(
                            width = 2.dp,
                            color = p_color1,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {

                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value         = search,
                        onValueChange = {
                            search = it
                        },
                        textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = p_color1),
                        placeholder = {
                            Text(
                                text = "Search for user by email",
                                style = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = customBlack8),
                            )
                        },
                        leadingIcon = {
                            Icon(painter = painterResource(id = R.drawable.user), contentDescription = null , tint = p_color1 , modifier = Modifier.size(26.dp))
                        },
                        maxLines = 1,
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        colors = TextFieldDefaults.colors().copy(focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0 , focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , cursorColor = p_color1 ),
                        modifier       = Modifier
                            .heightIn(min = 45.dp, max = 45.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )

                    Spacer(modifier = Modifier.height(26.dp))

                    if(loading){
                        val composition by rememberLottieComposition( LottieCompositionSpec.RawRes(R.raw.loading))
                        val progress = animateLottieCompositionAsState(composition = composition , iterations = LottieConstants.IterateForever)

                        LottieAnimation(
                            composition = composition ,
                            progress = progress.value ,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(65.dp)
                        )
                    }
                    if(user != null){
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(65.dp)
                                .padding(horizontal = 16.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (userSelected) p_color1.copy(alpha = 0.3f) else Color.Transparent)
                                .clickable {
                                    userSelected = true
                                }
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .clip(CircleShape)
                                    .background(customWhite2)
                                    .size(55.dp)
                            ){
                                AsyncImage(
                                    model = user.profilePhoto,
                                    contentDescription = null
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                Box(
                                    contentAlignment = Alignment.CenterStart,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = user.firstName + " " + user.lastName,
                                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(customBlack3)
                                    )
                                }
                                Box(
                                    contentAlignment = Alignment.CenterStart,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text =  user.email ,
                                        style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(customBlack3)
                                    )
                                }
                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(26.dp))


                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(p_color1.copy(alpha = 0.7f))
                        .border(
                            width = 3.dp,
                            color = p_color1_dark,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            loading = true
                            onSearch(
                                search,{
                                    loading = false
                                },{
                                    loading = false
                                }
                            )
                        }
                ){
                    Text(
                        text = "Search",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ7.copy(color = customWhite0)
                    )
                }

                if (userSelected){
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(p_color3.copy(alpha = 0.5f))
                            .border(
                                width = 3.dp,
                                color = p_color3,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable {
                                if (user != null){
                                    loading = true
                                    Toast.makeText(context , "${user.userId}",Toast.LENGTH_SHORT ).show()
                                    onAddOwner(
                                        user.userId.toInt(),{
                                            loading = false
                                        },{
                                            loading = false
                                        }
                                    )
                                }

                            }
                    ){
                        Text(
                            text = "Add Owner",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ7.copy(color = customWhite0)
                        )
                    }
                }
            }
        }

    }

}


@Preview
@Composable
private fun AddAcademyOwnerDialog_prev() {
    AddAcademyOwnerDialog(
        user = Account(firstName = "Rayan" , lastName = "Aouf" , email = "rayanaouf1512@gmail.com" , profilePhoto = "https://firebasestorage.googleapis.com/v0/b/daracademyfireproject.appspot.com/o/super_admin%2Fprofile_photo%2F167d8540-efe6-4595-a2eb-9a746d11d8d5.jpg?alt=media&token=272e1a2d-607b-4470-859e-8ad0f8e07194"),
        showDialog = true,
        modifier = Modifier
            .fillMaxWidth()
    )
}
