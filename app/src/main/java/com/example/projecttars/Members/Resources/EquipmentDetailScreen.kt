package com.example.projecttars.Members.Resources

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Description
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.Font
import com.example.projecttars.DataModels.EquipmentDetail
import com.example.projecttars.ui.theme.AccentBlue
import com.example.projecttars.ui.theme.AccentOrange
import com.example.projecttars.ui.theme.DarkGrayBlue
import com.example.projecttars.ui.theme.DarkSlate
import com.example.projecttars.ui.theme.TextPrimary
import com.example.projecttars.ui.theme.TextSecondary
import com.example.projecttars.R



@Composable
fun EquipmentDetailScreen(
    equipment: EquipmentDetail,
    onBackClick: () -> Unit
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
            .systemBarsPadding()
            .padding(bottom = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = TextPrimary,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Equipment Details",
                color = TextPrimary,
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.poppinsregular))
            )
        }

        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(horizontal = 16.dp),
            elevation = CardDefaults.cardElevation(12.dp)
        ) {
            Image(
                painter = painterResource(id = equipment.imageResId),
                contentDescription = equipment.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            equipment.youtubeUrl?.let { url ->
                Button(
                    onClick = { uriHandler.openUri(url) },
                    colors = ButtonDefaults.buttonColors(containerColor = AccentOrange),
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "YouTube", tint = TextPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Play Lecture",
                        color = TextPrimary,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }
            }

            equipment.documentationUrl?.let { url ->
                Button(
                    onClick = { uriHandler.openUri(url) },
                    colors = ButtonDefaults.buttonColors(containerColor = AccentBlue),
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.Description, contentDescription = "Documentation", tint = TextPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Read About",
                        color = TextPrimary,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = equipment.name,
            color = TextPrimary,
            fontSize = 24.sp,
            modifier = Modifier.padding(horizontal = 16.dp),
            fontFamily = FontFamily(Font(R.font.poppinsbold))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (equipment.isAvailable) "Available" else "Not Available",
            color = if (equipment.isAvailable) AccentBlue else AccentOrange,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 16.dp),
            fontFamily = FontFamily(Font(R.font.poppinsregular))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = DarkSlate),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = equipment.description,
                color = TextSecondary,
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp),
                fontFamily = FontFamily(Font(R.font.poppinsitalic))
            )
        }
    }
}
