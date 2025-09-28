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
    var selectedRole by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Choose Your Role",
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            color = AccentOrange,
            fontFamily = FontFamily(Font(R.font.poppinsregular))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Select your role to get a tailored experience.",
            fontSize = 14.sp,
            color = Color.LightGray,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.poppinsmedium))
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                RoleCard(
                    icon = Icons.Default.Security,
                    role = "Admin",
                    isSelected = selectedRole == "Admin",
                    onClick = { selectedRole = "Admin" }
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                RoleCard(
                    icon = Icons.Default.Groups,
                    role = "Members",
                    isSelected = selectedRole == "Members",
                    onClick = { selectedRole = "Members" }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { selectedRole?.let { onRoleSelected(it) } },
            enabled = selectedRole != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkSlate,
                disabledContentColor = Color.LightGray,
                contentColor = Color.White
            )
        ) {
            Text(text = "Continue",
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
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) AccentBlue else Color.Transparent
    val iconTint = if (isSelected) AccentBlue else Color.White

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .clip( RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .border(2.dp, borderColor, RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(4.dp),
         colors = CardDefaults.cardColors(containerColor = DarkSlate)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = role,
                tint = iconTint,
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = role, fontWeight = FontWeight.Medium,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppinsmedium))
            )
        }
    }
}