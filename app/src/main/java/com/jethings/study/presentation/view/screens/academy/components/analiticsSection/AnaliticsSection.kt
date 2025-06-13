package com.jethings.study.presentation.view.screens.academy.components.analiticsSection

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.academyOwnerScreen
import com.jethings.study.presentation.ui.theme.customBlack4
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite3
import com.jethings.study.util.objects.TextStyles


@Composable
fun AnaliticsSection(
    academyId : Int,
    academy : Academy?,
    onNavigate : (AppScreen)->Unit ={},
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(horizontal = 12.dp)
        ) {
            Surface(
                shadowElevation = 2.dp,
                onClick = {
                    onNavigate(academyOwnerScreen(academyId))
                },
                border = BorderStroke(
                    width = 0.dp,
                    color = customWhite3
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)

                    ){
                        Text(
                            text = "Owner",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                    ){
                        Text(
                            text = if(academy?.owners?.size != null ) academy.owners.size.toString() else  "0",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Surface(
                shape = RoundedCornerShape(12.dp),
                shadowElevation = 2.dp,
                onClick = {
                    onNavigate(academyOwnerScreen(academyId))
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                    ){
                        Text(
                            text = "Employee",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                    ){
                        Text(
                            text = "21",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Surface(
                shadowElevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    onNavigate(academyOwnerScreen(academyId))
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                    ){
                        Text(
                            text = "Teacher",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                    ){
                        Text(
                            text = "13",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(horizontal = 12.dp)
        ) {
            Surface(
                shadowElevation = 2.dp,
                onClick = {
                    academyOwnerScreen(academyId)
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(customWhite0)

                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)

                    ){
                        Text(
                            text = "Student",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                    ){
                        Text(
                            text = "126",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ8.copy(color = customBlack4)
                        )
                    }
                }
            }
        }
    }

}


@Preview
@Composable
private fun AnaliticsSection_prev() {

}