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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.projecttars.R

@Composable
fun CompletedProjectCard(
    imageUrl: String,
    title: String,
    shortDescription: String,
    onViewDetail: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value

        // Dynamic sizes
        val cardPadding = (screenWidth * 0.03f).dp
        val cardCorner = (screenWidth * 0.05f).dp
        val cardElevation = (screenWidth * 0.01f).dp
        val imageHeight = (screenHeight * 0.17f).dp
        val spacerHeight1 = (screenHeight * 0.02f).dp
        val spacerHeight2 = (screenHeight * 0.009f).dp
        val spacerHeight3 = (screenHeight * 0.015f).dp
        val titleFontSize = (screenWidth * 0.05f).sp
        val descriptionFontSize = (screenWidth * 0.035f).sp
        val buttonCorner = (screenWidth * 0.03f).dp

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(cardPadding)
                .clip(RoundedCornerShape(cardCorner)),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = cardElevation)
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
                        width = (screenWidth * 0.003f).dp,
                        color = Color.White.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(cardCorner)
                    )
                    .padding(cardPadding)
            ) {
                Column {
                    if (imageUrl.isEmpty()) {
                        Image(
                            painter = painterResource(id = R.drawable.tarsapplogo_foreground),
                            contentDescription = "Project Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(imageHeight)
                                .clip(RoundedCornerShape(cardCorner))
                        )
                    } else {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(imageHeight)
                                .clip(RoundedCornerShape(cardCorner)),
                        )
                    }

                    Spacer(modifier = Modifier.height(spacerHeight1))

                    Text(
                        text = title,
                        fontSize = titleFontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.poppinsbold))
                    )

                    Spacer(modifier = Modifier.height(spacerHeight2))

                    Text(
                        text = shortDescription,
                        fontSize = descriptionFontSize,
                        color = Color.LightGray.copy(alpha = 0.9f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )

                    Spacer(modifier = Modifier.height(spacerHeight3))

                    Button(
                        onClick = onViewDetail,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C566A)),
                        modifier = Modifier.align(Alignment.End),
                        shape = RoundedCornerShape(buttonCorner)
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
}
