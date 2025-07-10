package com.jethings.study.presentation.view.screens.TrainingProgram.component.name

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TrainingName (
    animatedVisibilityScope : AnimatedVisibilityScope,
    trainingProgram : TrainingProgram,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .sharedElement(
                state = rememberSharedContentState(key = "course-title-${trainingProgram.id}"),
                animatedVisibilityScope = animatedVisibilityScope
            )
            .padding(horizontal = 16.dp)

    ) {
        Text(
            text = trainingProgram.name,
            style = TextStyles.Itim_TextStyles.TextStyleSZ4.copy(color = customBlack3),
            modifier = Modifier
        )
    }
}


@Preview
@Composable
private fun TrainingName_prev() {

}