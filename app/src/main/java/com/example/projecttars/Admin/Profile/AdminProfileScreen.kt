package com.example.projecttars.Admin.Profile
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*

@Composable
fun AdminProfileScreen(
    username: String = "John Doe",
    email: String = "johndoe@example.com",
    onEditProfileClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onNotificationsViewClick: () -> Unit = {},
    onViewMessageClick: () -> Unit = {},
    onAboutSocietyClick: () -> Unit = {}
) {
    val gradientColorsHeader = listOf(DarkGrayBlue,AccentBlue.copy(alpha = 0.5f), DarkSlate)
    val gradientColorsCard = listOf(DarkGrayBlue, DarkSlate)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkCharcoal)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    Brush.verticalGradient(colors = gradientColorsHeader)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.tarslogo),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(170.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = username,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = AccentBlue,
                fontFamily = FontFamily(Font(R.font.poppinsmedium))
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = email,
                fontSize = 14.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppinsregular))
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ActionCardMinimal("Edit Profile", Icons.Default.Edit, gradientColorsCard, onEditProfileClick)
            ActionCardMinimal("View Notifications", Icons.Default.Notifications, gradientColorsCard, onNotificationsViewClick)
            ActionCardMinimal("View Messages", Icons.Default.Email, gradientColorsCard, onViewMessageClick)
            ActionCardMinimal("About Society", Icons.Default.Info, listOf(DarkSlate, DarkGrayBlue), onAboutSocietyClick)
            ActionCardMinimal("Logout", Icons.Default.ExitToApp, listOf(DarkSlate, DarkGrayBlue), onLogoutClick)
        }
    }
}

@Composable
fun ActionCardMinimal(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    gradientColors: List<Color>,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.horizontalGradient(gradientColors))
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
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
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                color = Color.White
            )
        }
    }
}
