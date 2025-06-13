package com.jethings.study.presentation.view.screens.TrainingProgram.component.budget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jethings.study.R
import com.jethings.study.data.db.entities.entities.TrainingProgram


@Composable
fun BudgetSection(
    trainingProgram: TrainingProgram,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.certificate),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Certified",
                style = TextStyle(fontWeight = FontWeight(500), fontSize = 14.sp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.money),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Price : ${trainingProgram.price ?: "0"} DA",
                style = TextStyle(fontWeight = FontWeight(500), fontSize = 14.sp)
            )
        }
    }
}

@Preview
@Composable
private fun BudgetSection_prev() {
    BudgetSection(
        trainingProgram = TrainingProgram()
    )
}