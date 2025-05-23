package com.jetapptech.business.presentation.view.dialog.colorPicker

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.ImageColorPicker
import com.github.skydoves.colorpicker.compose.PaletteContentScale
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.util.objects.TextStyles


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageColorPickerDialog(
    uri: Uri?,
    showDialog : Boolean = false,
    onDismissRequest: () -> Unit ={},
    onPick: (Color) -> Unit ={},
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current


    var controller = rememberColorPickerController()

    // Load the bitmap from the URI
    val bitmap = remember(uri) {
        uri?.let {
            val inputStream = context.contentResolver.openInputStream(it)
            BitmapFactory.decodeStream(inputStream)
        }

    }





    if (bitmap != null && showDialog) {
        BasicAlertDialog(
            onDismissRequest = {
                onDismissRequest()
            },
            modifier = modifier
                .fillMaxWidth(0.9f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Image Color Picker
                ImageColorPicker(
                    controller = controller,
                    paletteImageBitmap = bitmap.asImageBitmap(),
                    onColorChanged = { colorEnvelope: ColorEnvelope ->
                        controller.wheelColor = colorEnvelope.color
                    },
                    paletteContentScale = PaletteContentScale.CROP,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            width = 2.dp,
                            color = p_color1,
                            shape = RoundedCornerShape(16.dp)
                        )
                )

                Spacer(modifier = Modifier.height(26.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(p_color1)
                        .clickable {
                            onPick(controller.selectedColor.value)
                            onDismissRequest()
                        }
                ){
                    Text(
                        text = "Pick",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ3.copy(color = Color.White)
                    )
                }


            }






        }
    }

}