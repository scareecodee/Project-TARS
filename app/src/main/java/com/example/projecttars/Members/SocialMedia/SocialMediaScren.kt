package com.example.projecttars.Members.SocialMedia


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.projecttars.ui.theme.*
import com.example.projecttars.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.graphics.Color

data class SocialMediaHandle(
    val name: String,
    val url: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val color: Color
)

@Composable
fun SocialMediaScreen(onBackClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current

    val socialHandles = listOf(
        SocialMediaHandle("LinkedIn", "https://www.linkedin.com/company/tars-society", Icons.Default.Link, AccentBlue),
        SocialMediaHandle("Instagram", "https://www.instagram.com/tars.society", Icons.Default.CameraAlt, AccentPurple),
        SocialMediaHandle("YouTube", "https://www.youtube.com/@TARS", Icons.Default.PlayArrow, AccentOrange),
        SocialMediaHandle("Gmail", "mailto:tars.society@gmail.com", Icons.Default.Email, AccentBlue)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
            .systemBarsPadding()
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextPrimary)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "TARS Social Handles",
                color = TextPrimary,
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.poppinsregular))
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(socialHandles.size) { index ->
                val handle = socialHandles[index]

                // Gradient background for card
                Card(
                    colors = CardDefaults.cardColors(containerColor = DarkSlate),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable { uriHandler.openUri(handle.url) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        // Circular icon background
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(handle.color.copy(alpha = 0.2f), shape = RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(handle.icon, contentDescription = handle.name, tint = handle.color, modifier = Modifier.size(28.dp))
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = handle.name,
                                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                                color = TextPrimary,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = handle.url,
                                fontFamily = FontFamily(Font(R.font.poppinsregular)),
                                color = TextSecondary,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
