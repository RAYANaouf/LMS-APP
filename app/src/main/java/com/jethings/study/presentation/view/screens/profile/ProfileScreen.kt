package com.jethings.study.presentation.view.screens.profile

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jethings.study.R
import com.jethings.study.data.db.entities.Account
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack1
import com.jethings.study.presentation.ui.theme.customBlack2
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customBlack5
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.customWhite2
import com.jethings.study.presentation.ui.theme.customWhite3
import com.jethings.study.presentation.ui.theme.customWhite5
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color2
import com.jethings.study.presentation.ui.theme.p_color3
import com.jethings.study.presentation.ui.theme.p_color4
import com.jethings.study.presentation.ui.theme.p_color5
import com.jethings.study.presentation.view.material.AlphaButton
import com.jethings.study.presentation.view.material.AlphaTextFields.AlphaTextField
import com.jethings.study.presentation.view.screens.profile.bottomSheet.EditProfilePhotoBottomSheet
import com.jethings.study.util.objects.TextStyles
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    account : Account? = Account(),
    modifier: Modifier = Modifier
) {



    var profilePhoto by remember {
        mutableStateOf<Uri?>(null)
    }

    var showBottomBar by remember {
        mutableStateOf(false)
    }


    /****** launchers *******/

    var launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            profilePhoto = uri
            showBottomBar = true
        }
    }

    val bottomSheetState = rememberModalBottomSheetState()



    if(showBottomBar){
        EditProfilePhotoBottomSheet(
            sheetState = bottomSheetState,
            url = profilePhoto,
            onDismiss = {
                showBottomBar = false
            }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(36.dp))

        Box(
            modifier = Modifier
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(customWhite3)
                    .clickable {
                        launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.admin),
                    contentDescription = null,
                    tint = customBlack1,
                    modifier  = Modifier
                        .size(100.dp)
                )
                AsyncImage(
                    model = account?.profilePhoto,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .offset(y = 16.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(p_color1)
                    .align(Alignment.BottomCenter)
                    .clickable {

                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = null,
                    tint = customWhite0,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = if (account != null )"${account.firstName} ${account.lastName}" else "Admin Name",
            style = TextStyle(
                fontSize = 26.sp,
                fontWeight = FontWeight(700)
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = account?.email ?: "Admin@gmail.com",
            style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack5)
        )

        Spacer(modifier = Modifier.height(60.dp))

        //Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp, vertical = 6.dp)
        ) {
            Text(
                text = "General",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight(600)
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(customWhite2)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Personal Info",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack1)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        tint = customBlack1,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Accessibility",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack1)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        tint = customBlack1,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Notification",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack1)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        tint = customBlack1,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        //Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp, vertical = 6.dp)
        ) {
            Text(
                text = "Privacy",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight(600)
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(customWhite2)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Settings",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack1)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        tint = customBlack1,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Change Password",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack1)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        tint = customBlack1,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Log Out",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack1)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        tint = customBlack1,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(150.dp))

    }
}



fun uriToFile(context: Context, uri: Uri): File? {
    val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
    val file = File(context.cacheDir, "user_profilePhoto.jpg") // You can customize the name
    inputStream?.use { input ->
        FileOutputStream(file).use { output ->
            input.copyTo(output)
        }
    }
    return file
}



@Preview
@Composable
private fun HomeScreen_preview() {
    ProfileScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color_0)
    )
}