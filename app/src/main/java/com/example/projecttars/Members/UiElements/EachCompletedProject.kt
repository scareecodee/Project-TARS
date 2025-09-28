package com.example.projecttars.Members.UiElements
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.R


@Composable
fun CompletedProjectCard(
    imageResId: Int,
    title: String,
    shortDescription: String,
    onViewDetail: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp) // stronger shadow
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF2A2D3E).copy(alpha = 0.9f),
                            Color(0xFF1E2029).copy(alpha = 0.95f)
                        )
                    )
                )
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.1f), // glassy border
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(16.dp)
        ) {
            Column {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Project Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.poppinsbold))
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = shortDescription,
                    fontSize = 14.sp,
                    color = Color.LightGray.copy(alpha = 0.9f),
                    maxLines = 3,
                    fontFamily = FontFamily(Font(R.font.poppinsregular))
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onViewDetail,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C566A)), // sleek bluish-gray
                    modifier = Modifier.align(Alignment.End),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "View Detail",
                        fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                        color = Color.White
                    )
                }
            }
        }
    }
}
