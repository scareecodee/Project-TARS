package com.example.projecttars.Members.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.example.projecttars.Admin.Profile.ActionCardMinimal
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*

@Composable
fun ProfileScreen(
    username: String = "John Doe",
    email: String = "johndoe@example.com",
    onLogoutClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onAboutSocietyClick: () -> Unit = {}
) {
    val gradientColorsHeader = listOf(DarkGrayBlue, AccentBlue.copy(alpha = 0.5f), DarkSlate)
    val gradientColorsCard = listOf(DarkGrayBlue, DarkSlate)

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val screenWidth = maxWidth.value


        // Dynamic sizes

        val fontUsername = (screenWidth * 0.06f).sp
        val fontEmail = (screenWidth * 0.035f).sp
        val headerHeight = (screenWidth * 0.6f).dp
        val profileImageSize = (screenWidth * 0.38f).dp
        val spacerSmall = (screenWidth * 0.02f).dp
        val spacerMedium = (screenWidth * 0.04f).dp
        val actionCardHeight = (screenWidth * 0.15f).dp
        val actionCardCorner = (screenWidth * 0.04f).dp
        val iconSize = (screenWidth * 0.08f).dp
        val textSizeAction = (screenWidth * 0.045f).sp
        val actionCardPadding = (screenWidth * 0.04f).dp
        val iconTextSpacing = (screenWidth * 0.03f).dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkCharcoal)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight)
                    .background(Brush.verticalGradient(colors = gradientColorsHeader)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tarslogo),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(profileImageSize)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(spacerSmall))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = username,
                    fontSize = fontUsername,
                    fontWeight = FontWeight.Bold,
                    color = AccentBlue,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                )
                Spacer(modifier = Modifier.height(spacerSmall / 2))
                Text(
                    text = email,
                    fontSize = fontEmail,
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.poppinsregular))
                )
            }

            Spacer(modifier = Modifier.height(spacerMedium))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = actionCardPadding),
                verticalArrangement = Arrangement.spacedBy(spacerMedium),
                contentPadding = PaddingValues(bottom = spacerMedium)
            ) {

                item {
                    ActionCardMinimal(
                        "View Notifications",
                        Icons.Default.Notifications,
                        gradientColorsCard,
                        actionCardHeight,
                        actionCardCorner,
                        iconSize,
                        textSizeAction,
                        iconTextSpacing,
                        onNotificationsClick
                    )
                }
                item {
                    ActionCardMinimal(
                        "About Society",
                        Icons.Default.Info,
                        listOf(DarkSlate, DarkGrayBlue),
                        actionCardHeight,
                        actionCardCorner,
                        iconSize,
                        textSizeAction,
                        iconTextSpacing,
                        onAboutSocietyClick
                    )
                }
                item {
                    ActionCardMinimal(
                        "Logout",
                        Icons.Default.ExitToApp,
                        listOf(DarkSlate, DarkGrayBlue),
                        actionCardHeight,
                        actionCardCorner,
                        iconSize,
                        textSizeAction,
                        iconTextSpacing,
                        onLogoutClick
                    )
                }
            }
        }
    }
}

@Composable
fun ActionCardMinimal(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    gradientColors: List<Color>,
    onClick: () -> Unit,
    cardHeight: Dp,
    iconSize: Dp,
    fontSize: TextUnit,
    spacerWidth: Dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .clip(RoundedCornerShape(cardHeight / 3))
            .background(Brush.horizontalGradient(gradientColors))
            .clickable { onClick() }
            .padding(horizontal = cardHeight / 4),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = AccentOrange,
                modifier = Modifier.size(iconSize)
            )
            Spacer(modifier = Modifier.width(spacerWidth))
            Text(
                text = text,
                fontSize = fontSize,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                color = Color.White
            )
        }
    }
}
