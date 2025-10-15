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
import androidx.compose.material.icons.filled.PermMedia
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*

@Composable
fun AdminHome(
    onProjectCompletedClick: () -> Unit,
    onProjectOngoingClick: () -> Unit,
    onAchievementsClick: () -> Unit,
    onMembersClick: () -> Unit,
    onSocialHandleClick: () -> Unit
) {
    BoxWithConstraints {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        // Responsive scaling factors
        val titleFontSize = screenWidth.value * 0.075f         // ~30.sp
        val paddingLarge = screenWidth * 0.04f                 // ~16.dp
        val cardHeightLarge = screenHeight * 0.19f             // ~150.dp on ~800dp screen
        val cardHeightMedium = screenHeight * 0.18f            // ~140.dp
        val cardCorner = screenWidth * 0.05f                   // ~20.dp
        val iconSizeLarge = screenWidth * 0.1f                 // ~40.dp
        val iconSizeMedium = screenWidth * 0.09f               // ~36.dp
        val textFontLarge = screenWidth.value * 0.055f         // ~22.sp
        val textFontMedium = screenWidth.value * 0.05f         // ~20.sp
        val textFontSmall = screenWidth.value * 0.04f          // ~16.sp
        val verticalSpacing = screenWidth * 0.04f              // ~16.dp
        val dividerThickness = screenWidth * 0.0005f           // ~0.2.dp
        val contentPaddingBottom = screenHeight * 0.11f        // ~90.dp
        val smallPadding = screenWidth * 0.02f                 // ~8.dp

        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize()
                .background(DarkGrayBlue)
        ) {
            // Title
            Text(
                text = "Admin Home",
                fontSize = titleFontSize.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                fontFamily = FontFamily(Font(R.font.poppinsregular)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingLarge)
            )

            // List content
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = paddingLarge),
                verticalArrangement = Arrangement.spacedBy(verticalSpacing),
                contentPadding = PaddingValues(
                    top = smallPadding,
                    bottom = contentPaddingBottom
                )
            ) {
                // Manage Projects
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(cardHeightLarge),
                        shape = RoundedCornerShape(cardCorner),
                        colors = CardDefaults.cardColors(containerColor = DarkSlate),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color(0xFF1E2A47), Color(0xFF10141E))
                                    )
                                )
                                .padding(paddingLarge)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(smallPadding)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Assessment,
                                        contentDescription = "Projects",
                                        tint = AccentBlue,
                                        modifier = Modifier.size(iconSizeMedium)
                                    )
                                    Text(
                                        text = "Manage Projects",
                                        fontSize = textFontLarge.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary,
                                        fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                    )
                                }

                                Divider(
                                    modifier = Modifier
                                        .padding(horizontal = smallPadding)
                                        .fillMaxWidth()
                                        .height(dividerThickness),
                                    color = Color.LightGray
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        text = "Completed",
                                        modifier = Modifier
                                            .clickable { onProjectCompletedClick() }
                                            .padding(smallPadding),
                                        fontSize = textFontSmall.sp,
                                        color = AccentBlue,
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                    )

                                    Text(
                                        text = "Ongoing",
                                        modifier = Modifier
                                            .clickable { onProjectOngoingClick() }
                                            .padding(smallPadding),
                                        fontSize = textFontSmall.sp,
                                        color = AccentOrange,
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                    )
                                }
                            }
                        }
                    }
                }

                // Manage Achievements
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(cardHeightMedium)
                            .clip(RoundedCornerShape(cardCorner))
                            .clickable { onAchievementsClick() },
                        shape = RoundedCornerShape(cardCorner),
                        colors = CardDefaults.cardColors(containerColor = DarkSlate),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(Color(0xFF2B1B14), Color(0xFF1A1210))
                                    )
                                )
                                .padding(paddingLarge),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(smallPadding * 1.5f)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.EmojiEvents,
                                    contentDescription = "Achievements",
                                    tint = AccentOrange,
                                    modifier = Modifier.size(iconSizeLarge)
                                )
                                Text(
                                    text = "Manage Achievements",
                                    fontSize = textFontMedium.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary,
                                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                )
                            }
                        }
                    }
                }

                // Manage Members
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(cardHeightMedium)
                            .clip(RoundedCornerShape(cardCorner))
                            .clickable { onMembersClick() },
                        shape = RoundedCornerShape(cardCorner),
                        colors = CardDefaults.cardColors(containerColor = DarkSlate),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(Color(0xFF15222E), Color(0xFF0D141A))
                                    )
                                )
                                .padding(paddingLarge),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(smallPadding * 1.5f)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Groups,
                                    contentDescription = "TARS Members",
                                    tint = AccentBlue,
                                    modifier = Modifier.size(iconSizeLarge)
                                )
                                Text(
                                    text = "Manage TARS Members",
                                    fontSize = textFontMedium.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary,
                                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                )
                            }
                        }
                    }
                }

                // Manage Social Handles
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(cardHeightMedium)
                            .clip(RoundedCornerShape(cardCorner))
                            .clickable { onSocialHandleClick() },
                        shape = RoundedCornerShape(cardCorner),
                        colors = CardDefaults.cardColors(containerColor = DarkSlate),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(Color(0xFF1E1B2B), Color(0xFF0E0D14))
                                    )
                                )
                                .padding(paddingLarge),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(smallPadding * 1.5f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PermMedia,
                                    contentDescription = "Social Handles",
                                    tint = AccentPurple,
                                    modifier = Modifier.size(iconSizeLarge)
                                )
                                Text(
                                    text = "Manage TARS Social Handles",
                                    fontSize = textFontMedium.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary,
                                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
