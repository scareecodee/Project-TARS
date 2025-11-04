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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import coil.compose.AsyncImage
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*

@Composable
fun AdminProfileScreen(
    imageUrl: String,
    username: String ,
    email: String,
    onLogoutClick: () -> Unit = {},
    onNotificationsViewClick: () -> Unit = {},
    onAboutSocietyClick: () -> Unit = {},
    onManageAdminsClick: () -> Unit = {}
) {
    val gradientColorsHeader = listOf(DarkGrayBlue, AccentBlue.copy(alpha = 0.5f), DarkSlate)
    val gradientColorsCard = listOf(DarkGrayBlue, DarkSlate)

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkCharcoal)
    ) {
        val screenWidth = maxWidth.value


        val headerHeight = (screenWidth * 0.6f).dp
        val profileImageSize = (screenWidth * 0.38f).dp
        val spacerSmall = (screenWidth * 0.02f).dp
        val spacerMedium = (screenWidth * 0.04f).dp
        val usernameFontSize = (screenWidth * 0.07f).sp
        val emailFontSize = (screenWidth * 0.04f).sp
        val actionCardHeight = (screenWidth * 0.15f).dp
        val actionCardCorner = (screenWidth * 0.04f).dp
        val iconSize = (screenWidth * 0.08f).dp
        val textSizeAction = (screenWidth * 0.045f).sp
        val actionCardPadding = (screenWidth * 0.04f).dp
        val iconTextSpacing = (screenWidth * 0.03f).dp

        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight)
                    .background(Brush.verticalGradient(colors = gradientColorsHeader)),
                contentAlignment = Alignment.Center
            ) {
                val imageModifier = Modifier
                    .size(profileImageSize)
                    .align(Alignment.Center)

                if (imageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Profile Image",
                        modifier = imageModifier,
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.tarsapplogo_foreground),
                        contentDescription = "Profile Image",
                        modifier = imageModifier,
                        contentScale = ContentScale.Crop
                    )
                }
            }


            Spacer(modifier = Modifier.height(spacerMedium))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = username,
                    fontSize = usernameFontSize,
                    fontWeight = FontWeight.Bold,
                    color = AccentBlue,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                )
                Spacer(modifier = Modifier.height(spacerSmall))
                Text(
                    text = email,
                    fontSize = emailFontSize,
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
                        onNotificationsViewClick
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
                        "Manage Admins",
                        Icons.Default.ManageAccounts,
                        listOf(DarkSlate, DarkGrayBlue),
                        actionCardHeight,
                        actionCardCorner,
                        iconSize,
                        textSizeAction,
                        iconTextSpacing,
                        onManageAdminsClick
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
    height: Dp,
    cornerRadius: Dp,
    iconSize: Dp,
    textSize: TextUnit,
    iconTextSpacing: Dp,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(cornerRadius))
            .background(Brush.horizontalGradient(gradientColors))
            .clickable { onClick() }
            .padding(horizontal = iconTextSpacing),
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
            Spacer(modifier = Modifier.width(iconTextSpacing))
            Text(
                text = text,
                fontSize = textSize,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                color = Color.White
            )
        }
    }
}
