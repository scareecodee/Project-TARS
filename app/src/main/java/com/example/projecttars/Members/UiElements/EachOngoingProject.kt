// OngoingProjectCard.kt
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
import com.example.projecttars.ui.theme.DarkCharcoal
import com.example.projecttars.ui.theme.TextPrimary
import com.example.projecttars.ui.theme.TextSecondary

@Composable
fun OngoingProjectCard(
    imageUrl: String,
    title: String,
    shortDescription: String,
    onViewDetail: () -> Unit,
    screenWidth: Float,
    screenHeight: Float
) {
    // Dynamic sizes
    val cardPadding = (screenWidth * 0.03f).dp
    val cornerRadius = (screenWidth * 0.04f).dp
    val imageHeight = (screenHeight * 0.27f).dp
    val spacerSmall = (screenHeight * 0.01f).dp
    val spacerMedium = (screenHeight * 0.015f).dp
    val titleFont = (screenWidth * 0.045f).sp
    val descFont = (screenWidth * 0.035f).sp
    val buttonPadding = (screenWidth * 0.02f).dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(cardPadding)
            .clip(RoundedCornerShape(cornerRadius)),
        colors = CardDefaults.cardColors(containerColor = DarkCharcoal),
        elevation = CardDefaults.cardElevation(defaultElevation = (screenWidth * 0.01f).dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(DarkCharcoal, DarkSlate)
                    )
                )
                .padding(cardPadding)
        ) {
            Column {
                if (imageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Ongoing Project Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageHeight)
                            .clip(RoundedCornerShape(cornerRadius))
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.tarslogo),
                        contentDescription = "Ongoing Project Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageHeight)
                            .clip(RoundedCornerShape(cornerRadius))
                    )
                }

                Spacer(modifier = Modifier.height(spacerSmall))

                Text(
                    text = title,
                    fontSize = titleFont,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    fontFamily = FontFamily(Font(R.font.poppinsbold))
                )

                Spacer(modifier = Modifier.height(spacerSmall))

                Text(
                    text = shortDescription,
                    fontSize = descFont,
                    color = TextSecondary,
                    maxLines = 3,
                    fontFamily = FontFamily(Font(R.font.poppinsitalic))
                )

                Spacer(modifier = Modifier.height(spacerMedium))

                Button(
                    onClick = onViewDetail,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C566A)),
                    modifier = Modifier.align(Alignment.End),
                    shape = RoundedCornerShape(cornerRadius)
                ) {
                    Text(
                        text = "View Progress",
                        fontFamily = FontFamily(Font(R.font.poppinsregular)),
                        color = TextPrimary,
                        fontSize = descFont
                    )
                }
            }
        }
    }
}
