package com.jethings.study.presentation.view.screens.setting.scene.bottomSheetScene.ManageAccountsBottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jethings.study.data.db.entities.Account
import com.jethings.study.presentation.ui.theme.customBlack5
import com.jethings.study.presentation.ui.theme.customBlack6
import com.jethings.study.presentation.ui.theme.customWhite0
import com.jethings.study.presentation.ui.theme.customWhite5
import com.jethings.study.util.objects.TextStyles


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageAccountBottomSheet(
    sheetState : SheetState,
    onDismiss  : ()->Unit = {},
    account    : Account?,
    modifier   : Modifier = Modifier
) {

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState,
        containerColor = customWhite0,
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxHeight()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(customWhite0)
        ) {
            Text(
                text = "Accounts",
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight(600)
                ),
                modifier = Modifier
                    .padding(start = 16.dp , end = 8.dp)
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Manage your accounts info, edit or event disactivate them if you have to. you can from here see all the academies owned by you.",
                style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                    color = customBlack6
                ),
                modifier = Modifier
                    .padding(start = 16.dp , end = 8.dp)
            )

            Spacer(Modifier.height(26.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .border(
                        width = 1.dp,
                        color = customWhite5,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                        .clickable {

                        }
                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp , end = 16.dp)
                            .size(45.dp)
                            .clip(CircleShape)
                    ){
                        AsyncImage(
                            model = account?.profilePhoto,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    Column {
                        Text(
                            text = (account?.firstName ?: "") + " " + (account?.lastName ?: ""),
                            style = TextStyle(
                                fontSize = 20.sp
                            )
                        )
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun ManageAccountBottomSheet_prev() {
    ManageAccountBottomSheet(
        account = Account(firstName = "rayan" , lastName = "aouf"),
        onDismiss = {

        },
        sheetState = rememberModalBottomSheetState()
    )
}