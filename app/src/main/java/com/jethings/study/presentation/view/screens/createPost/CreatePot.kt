package com.jethings.study.presentation.view.screens.createPost

import android.content.Context
import android.graphics.Color.parseColor
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jethings.study.R
import com.jethings.study.data.api.req_res_classes.PostModule.createPost.CreatePostRequest
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramRequest
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.presentation.ui.theme.customBlack0
import com.jethings.study.presentation.ui.theme.customBlack6
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.presentation.view.screens.createPost.event.CreatePostEvent
import com.jethings.study.util.objects.TextStyles
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@Composable
fun CreatePost(
    onEvent: (CreatePostEvent, onSucess : () -> Unit, onFailure : () -> Unit) -> Unit = { _, _, _ ->},
    selectedAcademy : Academy? = null,
    onDone : ()->Unit = {},
    modifier : Modifier = Modifier
) {

    var title by rememberSaveable {
        mutableStateOf("")
    }
    var content by rememberSaveable {
        mutableStateOf("")
    }

    var coroutineScope = rememberCoroutineScope()

    var coverPhoto by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current

    var done by rememberSaveable {
        mutableStateOf(false)
    }
    var search by rememberSaveable {
        mutableStateOf(false)
    }

    /****** launchers *******/

    var launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            coverPhoto = uri
        }

    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {


        if (done){
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie_done))
            val progress    by animateLottieCompositionAsState(
                composition = composition,
            )

            LaunchedEffect(key1 = progress) {
                if(progress == 1f){
                    onDone()
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(parseColor("#55000000")))
                    .zIndex(10f)
            ) {
                LottieAnimation(
                    composition = composition,
                    progress = {
                        progress
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                shadowElevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(customBlack0.copy(alpha = 0.08f))
                            .zIndex(2f)
                    )
                    if ( coverPhoto == null){

                        Image(
                            painter = painterResource(id = R.drawable.training_program),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .blur(
                                    2.5.dp,
                                    2.5.dp
                                )
                        )

                        Text(
                            text = "Add Photo",
                            style = TextStyle(
                                fontSize = 28.sp,
                                fontWeight = FontWeight(600),
                                color = p_color1
                            )
                        )
                    }else{
                        AsyncImage(
                            model = coverPhoto,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value         = title,
                onValueChange = {
                    title = it
                },
                textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = p_color1),
                label         = {
                    Text(
                        text = "title",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack6),
                    )
                },
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.post), contentDescription = null , tint = p_color1 ,  modifier = Modifier.size(26.dp))
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors().copy(focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0 , focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , cursorColor = p_color1 ),
                modifier       = Modifier
                    .heightIn(min = 45.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(26.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Content",
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = customBlack6)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value         = content,
                onValueChange = {
                    content = it
                },
                textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = customBlack6),
                placeholder   = {
                    Text(
                        text = "content of the post ...",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = customBlack6),
                    )
                },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors().copy(focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0 , focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , cursorColor = p_color1 ),
                modifier       = Modifier
                    .heightIn(min = 150.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(Modifier.weight(1f))


            if (!search){
                Box(
                    modifier = Modifier
                        .animateContentSize()
                        .height(55.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(p_color1)
                        .clickable {
                            if (done)
                                return@clickable
                            coroutineScope.launch {
                                search = true
                                if (selectedAcademy == null) {
                                    search = false
                                    Toast
                                        .makeText(
                                            context,
                                            "No selected academy",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                    return@launch
                                }
                                selectedAcademy.let {
                                    val file = coverPhoto?.let {
                                        uriToFile(context, it)
                                    }
                                    Toast.makeText(context , "file : ${file?.name}" , Toast.LENGTH_SHORT).show()
                                    onEvent(
                                        CreatePostEvent.CreatePost(
                                            createPostRequest = CreatePostRequest(
                                                academyId = it.id,
                                                title = title,
                                                content = content
                                            ),
                                            file
                                        ), {
                                            Toast.makeText(context , "we just success" , Toast.LENGTH_SHORT).show()
                                            search = false
                                            done = true
                                        }, {
                                            search = false
                                            Toast
                                                .makeText(
                                                    context,
                                                    "Failure !!!",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        }
                                    )
                                }
                            }
                        }
                ) {
                    Text(
                        text = "Publish",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customWhite0),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            else{

                val composition by rememberLottieComposition( LottieCompositionSpec.RawRes(R.raw.loading))
                val progress = animateLottieCompositionAsState(composition = composition , iterations = LottieConstants.IterateForever)

                LottieAnimation(
                    composition = composition ,
                    progress = progress.value ,
                    modifier = Modifier
                        .animateContentSize()
                        .height(55.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

            }

            Spacer(Modifier.height(35.dp))


        }


    }
}


fun uriToFile(context: Context, uri: Uri): File? {
    val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
    val fileName = "post_photo_${System.currentTimeMillis()}.jpg"
    val file = File(context.cacheDir, fileName)
    inputStream?.use { input ->
        FileOutputStream(file).use { output ->
            input.copyTo(output)
        }
    }
    return file
}


@Preview
@Composable
private fun CreatePot_prev() {
    CreatePost()
}