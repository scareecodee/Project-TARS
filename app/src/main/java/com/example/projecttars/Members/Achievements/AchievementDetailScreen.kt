package com.example.projecttars.Members.Achievements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.projecttars.DataModels.Achievement
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*

@Composable
fun AchievementDetailScreen(
    achievement: Achievement,
    onBackClick: () -> Unit,
    isAdmin: Boolean = false,
    onDeleteClick: (() -> Unit)? = null,
    onEditClick: (() -> Unit)? = null
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
            .systemBarsPadding()
    ) {
        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value

        // responsive coefficients
        val horizontalPadding = screenWidth * 0.04f
        val verticalPadding = screenHeight * 0.02f
        val iconSize = screenWidth * 0.07f
        val titleFontSize = (screenWidth * 0.055f).sp
        val subTextFontSize = (screenWidth * 0.045f).sp
        val cardCorner = screenWidth * 0.05f
        val imageHeight = screenHeight * 0.28f
        val spacerHeight = screenHeight * 0.02f
        val fabPadding = screenWidth * 0.05f
        val fabSize = screenWidth * 0.14f

        Column(modifier = Modifier.fillMaxSize()) {
            // Top Bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding.dp, vertical = verticalPadding.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.size(iconSize.dp)
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = TextPrimary,
                            modifier = Modifier.size(iconSize.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width((screenWidth * 0.02f).dp))

                    Text(
                        text = "Achievement Details",
                        color = TextPrimary,
                        fontSize = titleFontSize,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }

                if (isAdmin) {
                    IconButton(
                        onClick = { onDeleteClick?.invoke() },
                        modifier = Modifier.size(iconSize.dp)
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(iconSize.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height((screenHeight * 0.01f).dp))

            // Image Card
            Card(
                shape = RoundedCornerShape(cardCorner.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight.dp)
                    .padding(horizontal = horizontalPadding.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = (screenWidth * 0.02f).dp)
            ) {
                AsyncImage(
                    model = achievement.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(spacerHeight.dp))

            // Content
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = horizontalPadding.dp),
                contentPadding = PaddingValues(bottom = (screenHeight * 0.12f).dp)
            ) {
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = DarkSlate),
                        shape = RoundedCornerShape((screenWidth * 0.04f).dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding((screenWidth * 0.04f).dp)) {
                            Text(
                                text = achievement.title,
                                fontFamily = FontFamily(Font(R.font.poppinsbold)),
                                color = TextPrimary,
                                fontSize = titleFontSize
                            )
                            Spacer(modifier = Modifier.height((screenHeight * 0.01f).dp))
                            Text(
                                text = achievement.shortDescription,
                                fontFamily = FontFamily(Font(R.font.poppinsregular)),
                                color = TextSecondary,
                                fontSize = subTextFontSize
                            )
                        }
                    }
                }
            }
        }

        // Floating Action Button
        if (isAdmin) {
            FloatingActionButton(
                onClick = { onEditClick?.invoke() },
                containerColor = AccentBlue,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(fabPadding.dp)
                    .size(fabSize.dp)
            ) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = TextPrimary,
                    modifier = Modifier.size((screenWidth * 0.065f).dp)
                )
            }
        }
    }
}
