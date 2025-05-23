package com.jethings.study.presentation.view.dialog.colorPicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.jethings.study.R
import com.jethings.study.presentation.ui.theme.customBlack8
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.util.objects.TextStyles


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAcademyOwnerDialog(
    showDialog : Boolean = false,
    onDismissRequest: () -> Unit ={},
    onPick: (Color) -> Unit ={},
    modifier: Modifier = Modifier
) {

    var controller = rememberColorPickerController()
    var search by remember{
        mutableStateOf("")
    }

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

                OutlinedTextField(
                    value         = search,
                    onValueChange = {
                        search = it
                    },
                    textStyle     = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = p_color1),
                    label         = {
                        Text(
                            text = "Search for user",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack8),
                        )
                    },
                    leadingIcon = {
                        Icon(painter = painterResource(id = R.drawable.user), contentDescription = null , tint = p_color1 , modifier = Modifier.size(26.dp))
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors().copy(focusedContainerColor = customWhite0 , unfocusedContainerColor = customWhite0 , focusedLabelColor = p_color1 , focusedIndicatorColor = p_color1 , cursorColor = p_color1 ),
                    modifier       = Modifier
                        .heightIn(min = 45.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 35.dp)
                )

                Spacer(modifier = Modifier.height(26.dp))

                
            }
        }

    }

}


@Preview
@Composable
private fun AddAcademyOwnerDialog_prev() {
    AddAcademyOwnerDialog(showDialog = true)
}
