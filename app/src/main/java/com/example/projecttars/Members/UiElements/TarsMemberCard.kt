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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*


@Composable
fun TarsMemberCard(
    name: String,
    designation: String,
    gender: String,
    id: String,
    domain:String,
    onViewDetail: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .height(145.dp),
        shape = RoundedCornerShape(16.dp),
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
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(AccentBlue, AccentOrange)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    val icon = when (gender) {
                       "m"-> Icons.Default.Boy
                        "f" -> Icons.Default.Girl
                        else -> {
                            Icons.Default.Error
                        }
                    }
                    Icon(
                        icon,
                        contentDescription = "",
                        tint = DarkCharcoal,
                        modifier = Modifier.size(45.dp)
                    )
                }


                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        maxLines = 1,
                        fontFamily = FontFamily(Font(R.font.poppinsmedium))
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = designation,
                        fontSize = 14.sp,
                        color = TextSecondary,
                        maxLines = 2,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text =domain,
                        fontSize = 14.sp,
                        color = AccentOrange.copy(alpha = 0.8f),
                        maxLines = 2,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = id,
                        fontSize = 14.sp,
                        color = TextSecondary,
                        maxLines = 2,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }


                Button(
                    onClick = onViewDetail,
                    colors = ButtonDefaults.buttonColors(containerColor = AccentBlue.copy(alpha = 0.85f)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(
                        text = "View",
                        color = DarkCharcoal,
                        fontFamily = FontFamily(Font(R.font.poppinsmedium))
                    )
                }
            }
        }
    }
}
