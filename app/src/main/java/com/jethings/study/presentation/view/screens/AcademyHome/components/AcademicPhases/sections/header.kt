package com.jethings.study.presentation.view.screens.AcademyHome.components.AcademicPhases.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.presentation.ui.theme.customBlack8
import com.jethings.study.util.objects.TextStyles

@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(customBlack8)
            .weight(1f))


        Spacer(modifier = Modifier.width(2.dp))

        Spacer(modifier = Modifier
            .height(6.dp)
            .width(2.dp)
            .background(customBlack8)
        )

        Spacer(modifier = Modifier.width(2.dp))

        Spacer(modifier = Modifier
            .height(12.dp)
            .width(2.dp)
            .background(customBlack8)
        )

        Spacer(modifier = Modifier.width(2.dp))

        Spacer(modifier = Modifier
            .height(18.dp)
            .width(2.dp)
            .background(customBlack8)
        )

        Spacer(modifier = Modifier.width(2.dp))

        Spacer(modifier = Modifier
            .height(30.dp)
            .width(2.dp)
            .background(customBlack8)
        )

        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Academic",
            style = TextStyles.Monospace_TextStyles.TextStyleSZ6.copy(color = customBlack3 )
        )
        Spacer(modifier = Modifier.width(16.dp))


        Spacer(modifier = Modifier
            .height(30.dp)
            .width(2.dp)
            .background(customBlack8)
        )

        Spacer(modifier = Modifier.width(2.dp))

        Spacer(modifier = Modifier
            .height(18.dp)
            .width(2.dp)
            .background(customBlack8)
        )

        Spacer(modifier = Modifier.width(2.dp))

        Spacer(modifier = Modifier
            .height(12.dp)
            .width(2.dp)
            .background(customBlack8)
        )

        Spacer(modifier = Modifier.width(2.dp))

        Spacer(modifier = Modifier
            .height(6.dp)
            .width(2.dp)
            .background(customBlack8)
        )


        Spacer(modifier = Modifier.width(2.dp))

        Spacer(modifier = Modifier
            .height(1.dp)
            .background(customBlack8)
            .weight(1f))

    }
}