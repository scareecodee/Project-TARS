package com.example.projecttars.Members.UiElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Boy
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Girl
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*

@Composable
fun TarsMemberCard(
    imageUrl: String,
    name: String,
    designation: String,
    gender: String,
    id: String,
    domain: String,
    onViewDetail: () -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value

        // Dynamic sizing based on screen width
        val cardPadding = (screenWidth * 0.03f).dp
        val cardHeight = (screenHeight * 0.18f).dp
        val avatarOuterSize = (screenWidth * 0.14f).dp
        val avatarInnerSize = (screenWidth * 0.11f).dp
        val iconSize = (screenWidth * 0.07f).dp
        val spacingBetween = (screenWidth * 0.03f).dp
        val buttonHeight = (screenHeight * 0.06f).dp
        val nameFontSize = (screenWidth * 0.04f).sp
        val textFontSize = (screenWidth * 0.035f).sp

        Card(
            modifier = Modifier
                .padding(horizontal = cardPadding, vertical = cardPadding / 2)
                .fillMaxWidth()
                .height(cardHeight),
            shape = RoundedCornerShape((screenWidth * 0.04f).dp),
            colors = CardDefaults.cardColors(containerColor = DarkSlate),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(DarkGrayBlue, DarkSlate, DarkCharcoal)
                        )
                    )
                    .padding(cardPadding)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(spacingBetween)
                ) {

                    // Avatar Box
                    Box(
                        modifier = Modifier
                            .size(avatarOuterSize)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(AccentBlue, AccentOrange)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        val imageModifier = Modifier
                            .size(avatarInnerSize)
                            .clip(CircleShape)

                        if (imageUrl.isEmpty()) {
                            val icon = when (gender.lowercase()) {
                                "m" -> Icons.Default.Boy
                                "f" -> Icons.Default.Girl
                                else -> Icons.Default.Error
                            }
                            Icon(
                                imageVector = icon,
                                contentDescription = "Default Avatar",
                                tint = DarkCharcoal,
                                modifier = imageModifier.size(iconSize)
                            )
                        } else {
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = "Profile Image",
                                contentScale = ContentScale.Crop,
                                modifier = imageModifier
                            )
                        }
                    }

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = name,
                            fontSize = nameFontSize,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            maxLines = 1,
                            fontFamily = FontFamily(Font(R.font.poppinsmedium))
                        )

                        Spacer(modifier = Modifier.height(cardPadding / 2))

                        Text(
                            text = designation,
                            fontSize = textFontSize,
                            color = TextSecondary,
                            maxLines = 2,
                            fontFamily = FontFamily(Font(R.font.poppinsregular))
                        )

                        Spacer(modifier = Modifier.height(cardPadding / 2))

                        Text(
                            text = domain,
                            fontSize = textFontSize,
                            color = AccentOrange.copy(alpha = 0.8f),
                            maxLines = 2,
                            fontFamily = FontFamily(Font(R.font.poppinsregular))
                        )

                        Spacer(modifier = Modifier.height(cardPadding / 2))

                        Text(
                            text = id,
                            fontSize = textFontSize,
                            color = TextSecondary,
                            maxLines = 2,
                            fontFamily = FontFamily(Font(R.font.poppinsregular))
                        )
                    }

                    Button(
                        onClick = onViewDetail,
                        colors = ButtonDefaults.buttonColors(containerColor = AccentBlue.copy(alpha = 0.85f)),
                        shape = RoundedCornerShape((screenWidth * 0.03f).dp),
                        modifier = Modifier.height(buttonHeight)
                    ) {
                        Text(
                            text = "View",
                            color = DarkCharcoal,
                            fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                            fontSize = textFontSize
                        )
                    }
                }
            }
        }
    }
}
