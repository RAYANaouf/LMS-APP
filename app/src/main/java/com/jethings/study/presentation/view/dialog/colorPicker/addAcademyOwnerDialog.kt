package com.jethings.study.presentation.view.dialog.colorPicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.util.objects.TextStyles


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPicker(
    showDialog : Boolean = false,
    onDismissRequest: () -> Unit ={},
    onPick: (Color) -> Unit ={},
    modifier: Modifier = Modifier
) {

    var controller = rememberColorPickerController()

    if (showDialog) {
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
                HsvColorPicker(
                    controller = controller,
                    onColorChanged = { colorEnvelope: ColorEnvelope ->
                        controller.wheelColor = colorEnvelope.color
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .clip(RoundedCornerShape(16.dp))
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
