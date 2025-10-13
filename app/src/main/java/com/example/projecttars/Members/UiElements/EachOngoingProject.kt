package com.example.projecttars.Members.UiElements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import coil.compose.AsyncImage
import com.example.projecttars.R
import com.example.projecttars.ui.theme.DarkSlate
import com.example.projecttars.ui.theme.TextPrimary
import com.example.projecttars.ui.theme.TextSecondary
import com.example.projecttars.ui.theme.DarkCharcoal


@Composable
fun OngoingProjectCard(
    imageUrl:String,
    title: String,
    shortDescription: String,
    onViewDetail: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = DarkCharcoal),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(DarkCharcoal, DarkSlate)
                    )
                )
                .padding(12.dp)
        ) {
            Column {
                // Project image
                if(imageUrl.isNotEmpty()){
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Ongoing Project Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }else{
                    Image(
                        painter = painterResource(id = R.drawable.tarslogo),
                        contentDescription = "Ongoing Project Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    fontFamily = FontFamily(Font(R.font.poppinsbold))
                )

                Spacer(modifier = Modifier.height(4.dp))


                Text(
                    text = shortDescription,
                    fontSize = 14.sp,
                    color = TextSecondary,
                    maxLines = 3,
                    fontFamily = FontFamily(Font(R.font.poppinsitalic))
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onViewDetail,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C566A)),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        text = "View Progress",
                        fontFamily = FontFamily(Font(R.font.poppinsregular)),
                        color = TextPrimary
                    )
                }
            }
        }
    }
}
