package com.example.projecttars.Admin.Profile

import androidx.compose.ui.graphics.Color


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
                .padding(horizontal = 16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Send Notification",
                    color = TextPrimary,
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.poppinsregular))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title", color = TextSecondary, fontFamily = FontFamily(Font(R.font.poppinsitalic))) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = DarkSlate,
                    unfocusedContainerColor = DarkSlate,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = AccentBlue,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                textStyle = LocalTextStyle.current.copy(color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsmedium))),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description", color = TextSecondary,fontFamily = FontFamily(Font(R.font.poppinsitalic))) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = DarkSlate,
                    unfocusedContainerColor = DarkSlate,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = AccentBlue,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                textStyle = LocalTextStyle.current.copy(color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsmedium))),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
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
                .padding(20.dp)
        ) {
            Icon(Icons.Default.Send, contentDescription = "Send Notification", tint = TextPrimary)
        }
    }
}
