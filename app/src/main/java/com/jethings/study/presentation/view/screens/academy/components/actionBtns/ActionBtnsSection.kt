package com.jethings.study.presentation.view.screens.academy.components.actionBtns

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite3
import com.jethings.study.presentation.ui.theme.p_color1
import com.jethings.study.util.objects.TextStyles


@Composable
fun ActionBtnsSection(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.width(16.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(40.dp)
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(p_color1)
                .clickable {

                }
        ) {
            Text(
                text = "Edit",
                style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customWhite0)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(40.dp)
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(customWhite3)
                .clickable {

                }
        ) {
            Text(
                text = "Analitics",
                style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

    }
}


@Preview
@Composable
private fun ActionBtnsSection_prev() {
    ActionBtnsSection(

    )
}