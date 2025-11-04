package com.example.projecttars.Admin.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*

@Composable
fun SendNotificationScreen(
    onBackClick: () -> Unit,
    onSendClick: (title: String, description: String) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    val paddingHorizontal = (screenWidth * 0.04).dp
    val paddingVertical = (screenHeight * 0.02).dp
    val spacerSmall = (screenHeight * 0.02).dp
    val textSizeHeading = (screenWidth * 0.065).sp
    val textFieldHeight = (screenHeight * 0.2).dp
    val fabPadding = (screenWidth * 0.05).dp
    val textFieldCorner = (screenWidth * 0.04).dp

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = paddingHorizontal)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = paddingVertical)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = AccentBlue)
                }
                Spacer(modifier = Modifier.width(paddingHorizontal))
                Text(
                    text = "Send Notification",
                    color = AccentBlue,
                    fontSize = textSizeHeading,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                )
            }

            Spacer(modifier = Modifier.height(spacerSmall))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title", color = TextSecondary, fontFamily = FontFamily(Font(R.font.poppinsitalic))) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = DarkSlate,
                    unfocusedContainerColor = DarkSlate,
                    focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    cursorColor = AccentBlue,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                ),
                textStyle = LocalTextStyle.current.copy(color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsmedium))),
                shape = RoundedCornerShape(textFieldCorner),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(spacerSmall))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description", color = TextSecondary,fontFamily = FontFamily(Font(R.font.poppinsitalic))) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = DarkSlate,
                    unfocusedContainerColor = DarkSlate,
                    focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    cursorColor = AccentBlue,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                ),
                textStyle = LocalTextStyle.current.copy(color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsmedium))),
                shape = RoundedCornerShape(textFieldCorner),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(textFieldHeight),
                singleLine = false,
                maxLines = 5
            )
        }

        FloatingActionButton(
            onClick = {
                if(title.isNotBlank() && description.isNotBlank()) {
                    onSendClick(title, description)
                }
            },
            containerColor = AccentBlue,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(fabPadding)
        ) {
            Icon(Icons.Default.Send, contentDescription = "Send Notification", tint = TextPrimary)
        }
    }
}
