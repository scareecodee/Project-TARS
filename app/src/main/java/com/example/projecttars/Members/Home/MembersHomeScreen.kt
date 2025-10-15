package com.example.projecttars.Members.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.unit.times
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*

@Composable
fun MembersHome(
    onProjectCompletedClick: () -> Unit,
    onProjectOngoingClick: () -> Unit,
    onAchievementsClick: () -> Unit,
    onMembersClick: () -> Unit,
    onSocialHandleClick: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(DarkGrayBlue)
    ) {
        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value

        // Dynamic sizing
        val horizontalPadding = (screenWidth * 0.04f).dp
        val verticalPadding = (screenHeight * 0.02f).dp
        val cardHeightLarge = (screenHeight * 0.2f).dp
        val cardHeightMedium = (screenHeight * 0.18f).dp
        val cardCornerRadius = (screenWidth * 0.05f).dp
        val headingFontSize = (screenWidth * 0.08f).sp
        val cardTitleFont = (screenWidth * 0.06f).sp
        val cardIconSize = (screenWidth * 0.09f).dp
        val rowSpacing = (screenWidth * 0.03f).dp
        val textClickPadding = (screenWidth * 0.02f).dp
        val dividerHeight = (screenHeight * 0.002f).dp
        val sectionSpacing = (screenHeight * 0.015f).dp

        Column(modifier = Modifier.fillMaxSize()) {

            Text(
                text = "Home",
                fontSize = headingFontSize,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                fontFamily = FontFamily(Font(R.font.poppinsregular)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = horizontalPadding,end=horizontalPadding, bottom = verticalPadding*1.5f),
                verticalArrangement = Arrangement.spacedBy(sectionSpacing),
                contentPadding = PaddingValues(top = verticalPadding, bottom = screenHeight * 0.07f.dp)
            ) {

                // Projects Card
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(cardHeightLarge),
                        shape = RoundedCornerShape(cardCornerRadius),
                        colors = CardDefaults.cardColors(containerColor = DarkSlate),
                        elevation = CardDefaults.cardElevation(defaultElevation = screenHeight * 0.008f.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color(0xFF1E2A47), Color(0xFF10141E))
                                    )
                                )
                                .padding(horizontal = horizontalPadding, vertical = verticalPadding)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(rowSpacing)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Assessment,
                                        contentDescription = "Projects",
                                        tint = AccentBlue,
                                        modifier = Modifier.size(cardIconSize)
                                    )
                                    Text(
                                        text = "Projects",
                                        fontSize = cardTitleFont,
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary,
                                        fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                    )
                                }

                                Divider(
                                    modifier = Modifier
                                        .padding(horizontal = screenWidth * 0.01f.dp)
                                        .fillMaxWidth()
                                        .height(dividerHeight),
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
                                            .padding(textClickPadding),
                                        fontSize = (screenWidth * 0.045f).sp,
                                        color = AccentBlue,
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                    )

                                    Text(
                                        text = "Ongoing",
                                        modifier = Modifier
                                            .clickable { onProjectOngoingClick() }
                                            .padding(textClickPadding),
                                        fontSize = (screenWidth * 0.045f).sp,
                                        color = AccentOrange,
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                    )
                                }
                            }
                        }
                    }
                }

                // Achievements Card
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(cardHeightMedium)
                            .clip(RoundedCornerShape(cardCornerRadius))
                            .clickable { onAchievementsClick() },
                        shape = RoundedCornerShape(cardCornerRadius),
                        colors = CardDefaults.cardColors(containerColor = DarkSlate),
                        elevation = CardDefaults.cardElevation(defaultElevation = screenHeight * 0.008f.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(Color(0xFF2B1B14), Color(0xFF1A1210))
                                    )
                                )
                                .padding(horizontal = horizontalPadding),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(rowSpacing)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.EmojiEvents,
                                    contentDescription = "Achievements",
                                    tint = AccentOrange,
                                    modifier = Modifier.size(cardIconSize)
                                )
                                Text(
                                    text = "Achievements",
                                    fontSize = cardTitleFont,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary,
                                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                )
                            }
                        }
                    }
                }

                // TARS Members Card
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(cardHeightMedium)
                            .clip(RoundedCornerShape(cardCornerRadius))
                            .clickable { onMembersClick() },
                        shape = RoundedCornerShape(cardCornerRadius),
                        colors = CardDefaults.cardColors(containerColor = DarkSlate),
                        elevation = CardDefaults.cardElevation(defaultElevation = screenHeight * 0.008f.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(Color(0xFF15222E), Color(0xFF0D141A))
                                    )
                                )
                                .padding(horizontal = horizontalPadding),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(rowSpacing)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Groups,
                                    contentDescription = "TARS Members",
                                    tint = AccentBlue,
                                    modifier = Modifier.size(cardIconSize)
                                )
                                Text(
                                    text = "TARS Members",
                                    fontSize = cardTitleFont,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary,
                                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                )
                            }
                        }
                    }
                }

                // Social Handles Card
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(cardHeightMedium)
                            .clip(RoundedCornerShape(cardCornerRadius))
                            .clickable { onSocialHandleClick() },
                        shape = RoundedCornerShape(cardCornerRadius),
                        colors = CardDefaults.cardColors(containerColor = DarkSlate),
                        elevation = CardDefaults.cardElevation(defaultElevation = screenHeight * 0.008f.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(Color(0xFF1E1B2B), Color(0xFF0E0D14))
                                    )
                                )
                                .padding(horizontal = horizontalPadding),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(rowSpacing)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PermMedia,
                                    contentDescription = "Social Handles",
                                    tint = AccentPurple,
                                    modifier = Modifier.size(cardIconSize)
                                )
                                Text(
                                    text = "TARS Social Handles",
                                    fontSize = cardTitleFont,
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
