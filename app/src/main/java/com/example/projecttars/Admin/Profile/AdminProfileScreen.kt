package com.example.projecttars.Admin.Profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*

@Composable
fun AdminProfileScreen(
    imageUrl: String,
    username: String,
    email: String,
    onLogoutClick: () -> Unit = {},
    onNotificationsViewClick: () -> Unit = {},
    onAboutSocietyClick: () -> Unit = {},
    onManageAdminsClick: () -> Unit = {}
) {
    val bgGradient = Brush.verticalGradient(
        listOf(DarkGrayBlue, DarkSlate, Color.Black)
    )
    var showDeleteDialog by remember { mutableStateOf(false) }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(bgGradient)
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight


        val profileSize = (screenWidth * 0.3f).coerceIn(100.dp, 150.dp)
        val horizontalPadding = (screenWidth * 0.06f).coerceIn(16.dp, 24.dp)
        val nameFontSize = if (screenWidth < 380.dp) 18.sp else 22.sp
        val emailFontSize = if (screenWidth < 380.dp) 12.sp else 14.sp
        val titleFontSize = if (screenWidth < 400.dp) 16.sp else 18.sp
        val cardSpacing = if (screenHeight < 650.dp) 10.dp else 14.dp

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 40.dp)
        ) {
            // Profile Section
            item {
                Spacer(modifier = Modifier.height(screenHeight * 0.08f))
                Box(
                    modifier = Modifier
                        .size(profileSize)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUrl.isNotEmpty()) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Profile Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.tarsapplogo_foreground),
                            contentDescription = "Profile Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = username,
                    fontSize = nameFontSize,
                    color = AccentBlue,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = email,
                    fontSize = emailFontSize,
                    color = Color.LightGray,
                    fontFamily = FontFamily(Font(R.font.poppinsregular))
                )
                Spacer(modifier = Modifier.height(28.dp))
            }


            item {
                Text(
                    text = "Admin Controls",
                    fontSize = titleFontSize,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.poppinssemibold)),
                    color = AccentOrange,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(bottom = 16.dp)
                )
            }


            item {
                ModernActionCard(
                    title = "View Notifications",
                    icon = Icons.Default.Notifications,
                    accentColor = AccentBlue,
                    onClick = onNotificationsViewClick
                )
                Spacer(modifier = Modifier.height(cardSpacing))

                ModernActionCard(
                    title = "About Society",
                    icon = Icons.Default.Info,
                    accentColor = Color(0xFF64B5F6),
                    onClick = onAboutSocietyClick
                )
                Spacer(modifier = Modifier.height(cardSpacing))

                ModernActionCard(
                    title = "Manage Admins",
                    icon = Icons.Default.ManageAccounts,
                    accentColor = Color(0xFFFFD54F),
                    onClick = onManageAdminsClick
                )
                Spacer(modifier = Modifier.height(cardSpacing))

                ModernActionCard(
                    title = "Logout",
                    icon = Icons.Default.ExitToApp,
                    accentColor = Color(0xFFFF5252),
                    onClick = {
                        showDeleteDialog=true
                    }
                )
            }
        }
    }
    if (showDeleteDialog) {
        AlertDialog(
            icon = {
                Icon(
                    Icons.Default.Logout,
                    contentDescription = "Logout",
                    tint = Color.Red,
                )
            },
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Logout", color = TextPrimary,fontSize = 17.sp,
                fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium))) },
            text = { Text("Are you sure you want to Logout ?", color = TextSecondary,       fontSize = 12.sp,
                fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium))) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onLogoutClick.invoke()
                        showDeleteDialog = false
                    }
                ) {
                    Text("Confirm", color =Color.White,fontSize = 12.sp,
                        fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium)))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel", color = TextPrimary, fontSize = 12.sp,
                        fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium)))
                }
            },
            containerColor = DarkGrayBlue
        )
    }
}

@Composable
fun ModernActionCard(
    title: String,
    icon: ImageVector,
    accentColor: Color,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip( RoundedCornerShape(16.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2B303A)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(accentColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = accentColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .width(40.dp)
                        .background(accentColor.copy(alpha = 0.7f))
                )
            }

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Go",
                tint = Color.LightGray,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}
