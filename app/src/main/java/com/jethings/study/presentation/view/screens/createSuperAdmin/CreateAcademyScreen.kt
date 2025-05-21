package com.jethings.study.presentation.view.screens.createSuperAdmin

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jethings.study.R
import com.jethings.study.data.api.req_res_classes.createSuperAdmin.CreateSuperAdminRequest
import com.jethings.study.presentation.nvgraph.profileScreen
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack2
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customBlack5
import com.jethings.study.presentation.ui.theme.customBlack6
import com.jethings.study.presentation.ui.theme.customBlack8
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite1
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color1_dark
import com.jethings.study.presentation.view.material.AlphaTextFields.AlphaTextField
import com.jethings.study.presentation.view.screens.createSuperAdmin.events.CreateSuperAdminEvents
import com.jethings.study.util.objects.TextStyles
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


@Composable
fun CreateSuperAdminScreen(
    onEvent : (CreateSuperAdminEvents , ()->Unit , ()->Unit )->Unit = {_,_,_->},
    modifier: Modifier = Modifier
) {

    /*** vars ***/
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var profilePhoto by remember {
        mutableStateOf<Uri?>(null)
    }


    val context = LocalContext.current


    /****** launchers *******/

    var launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            profilePhoto = uri
        }

    }

    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Spacer(modifier = Modifier.height(55.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(customWhite0)
                .clickable {
                    launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
        ) {

            if ( profilePhoto == null ){
                Icon(
                    painter = painterResource(id = R.drawable.admin),
                    contentDescription = null,
                    tint = p_color1,
                    modifier = Modifier.size(80.dp)
                )
            }

            AsyncImage(
                model = profilePhoto,
                contentDescription = null ,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Create Super Admin",
            style = TextStyles.Monospace_TextStyles.TextStyleSZ6.copy(color = p_color1)
        )

        Spacer(modifier = Modifier.height(65.dp))


        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value         = firstName,
                    onValueChange = {
                        firstName = it
                    },
                    textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack5),
                    label         = {
                        Text(
                            text = "First name",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = p_color1 ),
                            maxLines = 1
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors().copy(focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0 , focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , cursorColor = p_color1 ),
                    modifier       = Modifier
                        .heightIn(min = 45.dp)
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                )

                OutlinedTextField(
                    value         = lastName,
                    onValueChange = {
                        lastName = it
                    },
                    textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack5),
                    label         = {
                        Text(
                            text = "Last name",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = p_color1),
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors().copy(focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0 , focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , cursorColor = p_color1 ),
                    modifier       = Modifier
                        .heightIn(min = 45.dp)
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedTextField(
                value         = email,
                onValueChange = {
                    email = it
                },
                textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack5),
                label         = {
                    Text(
                        text = "Super Admin email",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = p_color1),
                    )
                },
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.admin), contentDescription = null , tint = p_color1 , modifier = Modifier.size(26.dp))
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors().copy(focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0 , focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , cursorColor = p_color1 ),
                modifier       = Modifier
                    .heightIn(min = 45.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value         = password,
                onValueChange = {
                    password = it
                },
                textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack5),
                label         = {
                    Text(
                        text = "Password",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = p_color1),
                    )
                },
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.password), contentDescription = null , tint = p_color1 , modifier = Modifier.size(26.dp))
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors().copy(focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0 , focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , cursorColor = p_color1 ),
                modifier       = Modifier
                    .heightIn(min = 45.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.weight(2f))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .border(
                    width = 3.dp,
                    color = p_color1_dark,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .background(p_color1)
                .clickable {
                    val file = profilePhoto?.let { uriToFile(context, it)   }
                    onEvent(
                        CreateSuperAdminEvents.CreateSuperAdmin(
                            CreateSuperAdminRequest(
                                email = email,
                                password = password,
                                firstName = firstName,
                                lastName = lastName
                            ),
                            file
                        ),{
                            Toast
                                .makeText(
                                    context,
                                    "Super Admin created successfully",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        },{
                            Toast
                                .makeText(
                                    context,
                                    "Error while creating Super Admin ...",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                    )
                }
        ) {
            Text(
                text = "Create",
                style = TextStyle(color = customWhite0 , fontSize = 24.sp , fontWeight = FontWeight(700))
            )
        }

        Spacer(modifier = Modifier.height(65.dp))

    }
}



fun uriToFile(context: Context, uri: Uri): File? {
    val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
    val file = File(context.cacheDir, "academy_logo.jpg") // You can customize the name
    inputStream?.use { input ->
        FileOutputStream(file).use { output ->
            input.copyTo(output)
        }
    }
    return file
}


@Preview
@Composable
private fun CreateAcademyScreen_preview() {
    CreateSuperAdminScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color_0)
    )
}