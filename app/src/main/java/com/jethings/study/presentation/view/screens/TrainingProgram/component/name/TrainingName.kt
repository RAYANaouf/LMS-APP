package com.jethings.study.presentation.view.screens.TrainingProgram.component.name

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.data.db.entities.entities.TrainingProgram
import com.jethings.study.presentation.ui.theme.customBlack3
import com.jethings.study.util.objects.TextStyles


@Composable
fun TrainingName (
    trainingProgram : TrainingProgram,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = trainingProgram.name,
            style = TextStyles.Monospace_TextStyles.TextStyleSZ7.copy(color = customBlack3)
        )
    }
}


@Preview
@Composable
private fun TrainingName_prev() {
    TrainingName(
        trainingProgram = TrainingProgram()
    )
}