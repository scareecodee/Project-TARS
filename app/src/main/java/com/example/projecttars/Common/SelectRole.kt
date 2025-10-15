package com.example.projecttars.Common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.R
import com.example.projecttars.ui.theme.AccentBlue
import com.example.projecttars.ui.theme.AccentOrange
import com.example.projecttars.ui.theme.DarkGrayBlue
import com.example.projecttars.ui.theme.DarkSlate

@Composable
fun RoleSelectionScreen(
    onRoleSelected: (String) -> Unit
) {
    // Screen dimensions
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    // Responsive coefficients
    val horizontalPadding = screenWidth * 0.06f     // ~24.dp on 400dp screen
    val titleFontSize = screenWidth.value * 0.065f  // ~26.sp
    val subtitleFontSize = screenWidth.value * 0.035f // ~14.sp
    val spacerSmall = screenHeight * 0.01f          // ~8.dp
    val spacerLarge = screenHeight * 0.04f          // ~32.dp
    val buttonHeight = screenHeight * 0.065f        // ~50.dp
    val iconSize = screenWidth * 0.09f              // ~36.dp
    val cardPadding = screenHeight * 0.03f          // ~24.dp
    val cardCorner = screenWidth * 0.03f            // ~12.dp
    val borderThickness = screenWidth * 0.005f      // ~2.dp

    var selectedRole by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
            .padding(horizontal = horizontalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "Choose Your Role",
            fontSize = titleFontSize.sp,
            textAlign = TextAlign.Center,
            color = AccentOrange,
            fontFamily = FontFamily(Font(R.font.poppinsregular))
        )

        Spacer(modifier = Modifier.height(spacerSmall))

        // Subtitle
        Text(
            text = "Select your role to get a tailored experience.",
            fontSize = subtitleFontSize.sp,
            color = Color.LightGray,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.poppinsmedium))
        )

        Spacer(modifier = Modifier.height(spacerLarge))

        // Role options
        Row(
            horizontalArrangement = Arrangement.spacedBy(screenWidth * 0.04f), // ~16.dp
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                RoleCard(
                    icon = Icons.Default.Security,
                    role = "Admin",
                    isSelected = selectedRole == "Admin",
                    onClick = { selectedRole = "Admin" },
                    iconSize = iconSize,
                    cardPadding = cardPadding,
                    cardCorner = cardCorner,
                    borderThickness = borderThickness
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                RoleCard(
                    icon = Icons.Default.Groups,
                    role = "Members",
                    isSelected = selectedRole == "Members",
                    onClick = { selectedRole = "Members" },
                    iconSize = iconSize,
                    cardPadding = cardPadding,
                    cardCorner = cardCorner,
                    borderThickness = borderThickness
                )
            }
        }

        Spacer(modifier = Modifier.height(spacerLarge))

        // Continue button
        Button(
            onClick = { selectedRole?.let { onRoleSelected(it) } },
            enabled = selectedRole != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight),
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkSlate,
                disabledContentColor = Color.LightGray,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Continue",
                fontFamily = FontFamily(Font(R.font.poppinsmedium))
            )
        }
    }
}

@Composable
fun RoleCard(
    icon: ImageVector,
    role: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    iconSize: androidx.compose.ui.unit.Dp,
    cardPadding: androidx.compose.ui.unit.Dp,
    cardCorner: androidx.compose.ui.unit.Dp,
    borderThickness: androidx.compose.ui.unit.Dp
) {
    val borderColor = if (isSelected) AccentBlue else Color.Transparent
    val iconTint = if (isSelected) AccentBlue else Color.White

    Card(
        shape = RoundedCornerShape(cardCorner),
        modifier = Modifier
            .clip(RoundedCornerShape(cardCorner))
            .clickable { onClick() }
            .border(borderThickness, borderColor, RoundedCornerShape(cardCorner)),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSlate)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = cardPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = role,
                tint = iconTint,
                modifier = Modifier.size(iconSize)
            )
            Spacer(modifier = Modifier.height(cardPadding * 0.5f))
            Text(
                text = role,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppinsmedium))
            )
        }
    }
}
