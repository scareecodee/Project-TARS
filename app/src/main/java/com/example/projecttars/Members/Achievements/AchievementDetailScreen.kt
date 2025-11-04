package com.example.projecttars.Members.Achievements

import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
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
        var showDeleteDialog by remember { mutableStateOf(false) }
        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value


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
                            tint =AccentBlue,
                            modifier = Modifier.size(iconSize.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width((screenWidth * 0.02f).dp))

                    Text(
                        text = "Achievement Details",
                        color =AccentBlue,
                        fontSize = titleFontSize,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = FontFamily(Font(R.font.poppinsbold))
                    )
                }

                if (isAdmin) {
                    IconButton(
                        onClick ={
                            showDeleteDialog=true
                        },
                        modifier = Modifier.size(iconSize.dp)
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint =Color.Red,
                            modifier = Modifier.size(iconSize.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height((screenHeight * 0.01f).dp))


            Card(
                shape = RoundedCornerShape(cardCorner.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight.dp)
                    .padding(horizontal = horizontalPadding.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = (screenWidth * 0.02f).dp)
            ) {
                AsyncImage(
                    model = if (achievement.imageUrl.isEmpty()) R.drawable.tarsapplogo_foreground else achievement.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(spacerHeight.dp))


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
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text("Delete ${achievement.title}", color = TextPrimary,fontSize = 17.sp,
                    fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium))) },
                text = { Text("Are you sure you want to delete ?", color = TextSecondary,       fontSize = 12.sp,
                    fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium))) },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onDeleteClick?.invoke()
                            showDeleteDialog = false
                        }
                    ) {
                        Text("Delete", color =Color.White,fontSize = 12.sp,
                            fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium)))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) {
                        Text("Cancel", color = TextPrimary, fontSize = 12.sp,
                            fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium)))
                    }
                },
                containerColor = DarkGrayBlue
            )
        }
    }
}
