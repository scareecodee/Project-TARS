package com.example.projecttars.Members.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*
import coil.compose.AsyncImage

@Composable
fun HomeScreen(
    onProjectCompletedClick: () -> Unit,
    onProjectOngoingClick: () -> Unit,
    onAchievementsClick: () -> Unit,
    onComponentsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
    ) {
        // ------------------- Header -------------------
        Text(
            text = "Home",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            fontFamily = FontFamily(Font(R.font.poppinsregular)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // ------------------- Cards -------------------
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // -------- Projects Card --------
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkSlate),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(DarkSlate, DarkGrayBlue)
                                )
                            )
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Assessment,
                                    contentDescription = "Projects",
                                    tint = AccentBlue,
                                    modifier = Modifier.size(36.dp)
                                )
                                Text(
                                    text = "Projects",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary,
                                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                )
                            }

                            Divider(modifier = Modifier.padding(horizontal = 5.dp).fillMaxWidth().height(0.2.dp), color =Color.LightGray)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    text = "Completed",
                                    modifier = Modifier
                                        .clickable { onProjectCompletedClick() }
                                        .padding(8.dp),
                                    fontSize = 16.sp,
                                    color = AccentBlue,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                )

                                Text(
                                    text = "Ongoing",
                                    modifier = Modifier
                                        .clickable { onProjectOngoingClick() }
                                        .padding(8.dp),
                                    fontSize = 16.sp,
                                    color = AccentOrange,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                )
                            }
                        }
                    }
                }
            }

            // -------- Achievements Card --------
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { onAchievementsClick() },
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkSlate),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(DarkSlate, DarkGrayBlue)
                                )
                            )
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.EmojiEvents,
                                contentDescription = "Achievements",
                                tint = AccentBlue,
                                modifier = Modifier.size(40.dp)
                            )
                            Text(
                                text = "Achievements",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                fontFamily = FontFamily(Font(R.font.poppinsmedium))
                            )
                        }
                    }
                }
            }

            // -------- Components / Equipment Card --------
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { onComponentsClick() },
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkSlate),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(DarkSlate, DarkGrayBlue)
                                )
                            )
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Groups,
                                contentDescription = "TARS Members",
                                tint = AccentBlue,
                                modifier = Modifier.size(40.dp)
                            )
                            Text(
                                text = "TARS Members",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                fontFamily = FontFamily(Font(R.font.poppinsmedium))
                            )
                        }
                    }
                }
            }
            item {
CloudinaryImage()
            }
        }
    }
}





@Composable
fun CloudinaryImage() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Image from Cloudinary:", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = "https://res.cloudinary.com/dnewwgoua/image/upload/v1758661185/STK-20230722-WA0284_ltx5ri.webp",
            contentDescription = "Cloudinary Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}
