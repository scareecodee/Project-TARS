package com.example.projecttars.Members.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import com.example.projecttars.ui.theme.*
import com.example.projecttars.R

@Composable
fun MessageAdminScreen(onBackClick: () -> Unit, onSendClick: (title: String, description: String) -> Unit) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
            .systemBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        // Header
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
                text = "Message Admin",
                color = TextPrimary,
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.poppinsregular))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title input
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            placeholder = { Text("Enter title", color = TextSecondary,fontFamily = FontFamily(Font(R.font.poppinsitalic))) },
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkSlate, RoundedCornerShape(12.dp)),
            textStyle = LocalTextStyle.current.copy(color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsmedium))),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = DarkSlate,
                unfocusedContainerColor = DarkSlate,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = AccentBlue,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Description input
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            placeholder = { Text("Enter description", color = TextSecondary,fontFamily = FontFamily(Font(R.font.poppinsitalic))) },
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(DarkSlate, RoundedCornerShape(12.dp)),
            textStyle = LocalTextStyle.current.copy(color = TextPrimary,fontFamily = FontFamily(Font(R.font.poppinsmedium))),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = DarkSlate,
                unfocusedContainerColor = DarkSlate,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = AccentBlue,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            singleLine = false,
            maxLines = 10,
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Send button
        Button(
            onClick = { onSendClick(title.text, description.text) },
            colors = ButtonDefaults.buttonColors(containerColor = AccentBlue),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Send Message",
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                color = TextPrimary,
                fontSize = 16.sp
            )
        }
    }
}
