package com.jethings.study.presentation.view.screens.createAcademy

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jethings.study.R
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.homeScreen
import com.jethings.study.presentation.ui.theme.background_color_0
import com.jethings.study.presentation.ui.theme.customBlack5
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.ui.theme.p_color1_dark
import com.jethings.study.presentation.ui.theme.p_color4
import com.jethings.study.presentation.view.screens.createAcademy.events.CreateAcademyEvents
import com.jethings.study.util.objects.TextStyles
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


@Composable
fun CreateAcademyScreen(
    modifier: Modifier = Modifier,
    onNavigate : (AppScreen) -> Unit = {},
    onEvent : (event : CreateAcademyEvents , onSuccess : () -> Unit , onFailure : () -> Unit) -> Unit = {_,_,_->}
) {

    /******* vars *******/

    val context = LocalContext.current

    var name by remember {
        mutableStateOf("")
    }
    var logo by remember {
        mutableStateOf<Uri?>(null)
    }


    /****** launchers *******/
    
    var launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            logo = uri
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

            AsyncImage(
                model = logo,
                contentDescription = null ,
                modifier = Modifier
                    .size(95.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))


        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            OutlinedTextField(
                value         = name,
                onValueChange = {
                    name = it
                },
                textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack5),
                label         = {
                    Text(
                        text = "Academy name",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = p_color1),
                    )
                },
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.academy_icon), contentDescription = null , tint = p_color1 , modifier = Modifier.size(26.dp))
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors().copy(focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0 , focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , cursorColor = p_color1 ),
                modifier       = Modifier
                    .heightIn(min = 45.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

//            OutlinedTextField(
//                value         = "",
//                onValueChange = {
//
//                },
//                textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack5),
//                label         = {
//                    Text(
//                        text = "Academy Password",
//                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = p_color1),
//                    )
//                },
//                leadingIcon = {
//                    Icon(painter = painterResource(id = R.drawable.password), contentDescription = null , tint = p_color1 , modifier = Modifier.size(26.dp))
//                },
//                shape = RoundedCornerShape(12.dp),
//                colors = TextFieldDefaults.colors().copy(focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0 , focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , cursorColor = p_color1 ),
//                modifier       = Modifier
//                    .heightIn(min = 45.dp)
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//            )
        }

        Spacer(modifier = Modifier.weight(1f))

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
                    val file = logo?.let { uriToFile(context, it)   }
                    onEvent(
                        CreateAcademyEvents.CreateAcademy(name = name , logo = file), {
                            Toast
                                .makeText(
                                    context,
                                    "Academy Created Successfully",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                            onNavigate(homeScreen)
                        }, {
                            Toast
                                .makeText(context, "Failed To Create Academy", Toast.LENGTH_SHORT)
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
    CreateAcademyScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color_0)
    )
}