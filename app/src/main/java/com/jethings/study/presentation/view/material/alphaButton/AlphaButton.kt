package com.jetapptech.business.presentation.view.material

import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetapptech.business.util.TextStyles

@Composable
fun AlphaButton(
    txt             : String,
    textStyle       : TextStyle = TextStyles.Monospace_TextStyles.TextStyleSZ6.copy(color = Color.White ),
    shape           : Shape     = RoundedCornerShape(12.dp),
    backgroundColor : Color     = Color(parseColor("#1D71A0")),
    onClick         : ()->Unit  = {},
    modifier        : Modifier  = Modifier
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .clickable {
                onClick()
            }
            .padding(top = 8.dp , bottom = 8.dp , start = 14.dp , end = 14.dp )
    ) {
        Text(
            text = txt,
            style = textStyle
        )
    }

}

@Preview
@Composable
fun AlphaButton_preview() {

}