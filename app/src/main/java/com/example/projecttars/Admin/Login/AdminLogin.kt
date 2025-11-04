package com.example.projecttars.Admin.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*

@Composable
fun AdminLogin(
    onLoginClick: (String, String) -> Unit,

) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue),
        contentAlignment = Alignment.Center
    ) {
        val screenWidth = maxWidth.value

        // Responsive values
        val outerPadding = (screenWidth * 0.04f).dp
        val cardCornerRadius = (screenWidth * 0.05f).dp
        val cardElevation = (screenWidth * 0.015f).dp
        val verticalPadding = (screenWidth * 0.04f).dp
        val iconSize = (screenWidth * 0.16f).dp
        val spacerSmall = (screenWidth * 0.035f).dp
        val spacerMedium = (screenWidth * 0.05f).dp
        val textSizeHeading = (screenWidth * 0.07f).sp
        val textSizeButton = (screenWidth * 0.045f).sp
        val buttonCornerRadius = (screenWidth * 0.03f).dp
        val textFieldFontSize = (screenWidth * 0.04f).sp

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(outerPadding),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(cardCornerRadius),
                colors = CardDefaults.cardColors(containerColor = DarkSlate),
                elevation = CardDefaults.cardElevation(defaultElevation = cardElevation)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(DarkSlate, DarkGrayBlue)
                            )
                        )
                        .padding(verticalPadding, verticalPadding, verticalPadding, verticalPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.AdminPanelSettings,
                        contentDescription = "Admin Icon",
                        tint = AccentOrange,
                        modifier = Modifier.size(iconSize)
                    )

                    Spacer(modifier = Modifier.height(spacerMedium))

                    Text(
                        text = "Admin Login",
                        fontSize = textSizeHeading,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        fontFamily = FontFamily(Font(R.font.poppinsmedium))
                    )

                    Spacer(modifier = Modifier.height(spacerMedium))

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Admin ID", fontFamily = FontFamily(Font(R.font.poppinsregular))) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = DarkSlate,
                            unfocusedContainerColor = DarkSlate,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White,
                            cursorColor = Color.White,
                            focusedLabelColor = AccentOrange,
                            unfocusedLabelColor = AccentOrange
                        ),
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppinsregular)),
                            fontSize = textFieldFontSize
                        )
                    )

                    Spacer(modifier = Modifier.height(spacerSmall))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password", fontFamily = FontFamily(Font(R.font.poppinsregular))) },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = DarkSlate,
                            unfocusedContainerColor = DarkSlate,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White,
                            cursorColor = Color.White,
                            focusedLabelColor = AccentOrange,
                            unfocusedLabelColor = AccentOrange
                        ),
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppinsregular)),
                            fontSize = textFieldFontSize
                        )
                    )

                    Spacer(modifier = Modifier.height(spacerMedium))

                    Button(
                        onClick = { onLoginClick(username, password) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((screenWidth * 0.125f).dp)
                            .clip(RoundedCornerShape(buttonCornerRadius)),
                        colors = ButtonDefaults.buttonColors(containerColor = AccentOrange)
                    ) {
                        Text(
                            text = "Login",
                            color = TextPrimary,
                            fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                            fontSize = textSizeButton
                        )
                    }
                }
            }
        }
    }
}
