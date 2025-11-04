package com.example.projecttars.Members.SocialMedia

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.draw.clip
import com.example.projecttars.ui.theme.*
import com.example.projecttars.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.projecttars.DataModels.SocialMediaHandle

@Composable
fun SocialMediaScreen(
    onBackClick: () -> Unit,
    isAdmin: Boolean = false,
    onEditClick: (() -> Unit)? = null
) {
    val uriHandler = LocalUriHandler.current

    val socialHandles = listOf(
        SocialMediaHandle(
            "LinkedIn",
            "https://www.linkedin.com/company/tars-society",
            Icons.Default.Link,
            AccentBlue
        ),
        SocialMediaHandle("Instagram", "https://www.instagram.com/tars.society", Icons.Default.CameraAlt, AccentPurple),
        SocialMediaHandle("YouTube", "https://www.youtube.com/@TARS", Icons.Default.PlayArrow, AccentOrange),
        SocialMediaHandle("Gmail", "mailto:tars.society@gmail.com", Icons.Default.Email, AccentBlue)
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
            .systemBarsPadding()
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        // Dynamic sizing
        val horizontalPadding = screenWidth * 0.04f
        val verticalPadding = screenHeight * 0.02f
        val headerFontSize = (screenWidth * 0.06f)
        val cardCornerRadius = screenWidth * 0.05f
        val cardElevation = screenWidth * 0.02f
        val iconBoxSize = screenWidth * 0.12f
        val iconSize = screenWidth * 0.06f
        val spacerWidth = screenWidth * 0.04f
        val nameFontSize = (screenWidth * 0.045f)
        val urlFontSize = (screenWidth * 0.035f)
        val cardPadding = screenWidth * 0.04f
        val rowPadding = screenWidth * 0.04f
        val fabSize = screenWidth * 0.14f
        val fabPadding = screenWidth * 0.05f

        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding, vertical = verticalPadding)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = AccentBlue)
                }
                Spacer(modifier = Modifier.width(horizontalPadding))
                Text(
                    text = "TARS Social Handles",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = AccentBlue,
                    fontSize = headerFontSize.value.sp,
                    fontFamily = FontFamily(Font(R.font.poppinsbold))
                )
            }

            Spacer(modifier = Modifier.height(verticalPadding / 2))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = screenHeight * 0.1f)
            ) {
                items(socialHandles.size) { index ->
                    val handle = socialHandles[index]

                    Card(
                        colors = CardDefaults.cardColors(containerColor = DarkSlate),
                        shape = RoundedCornerShape(cardCornerRadius),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = horizontalPadding, vertical = verticalPadding / 2)
                            .clip(RoundedCornerShape(cardCornerRadius))
                            .clickable { uriHandler.openUri(handle.url) },
                        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(rowPadding)
                        ) {
                            // Icon Box
                            Box(
                                modifier = Modifier
                                    .size(iconBoxSize)
                                    .background(
                                        handle.color.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(cardCornerRadius / 2)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    handle.icon,
                                    contentDescription = handle.name,
                                    tint = handle.color,
                                    modifier = Modifier.size(iconSize)
                                )
                            }

                            Spacer(modifier = Modifier.width(spacerWidth))

                            Column {
                                Text(
                                    text = handle.name,
                                    fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                                    color = TextPrimary,
                                    fontSize = nameFontSize.value.sp
                                )
                                Spacer(modifier = Modifier.height(verticalPadding / 4))
                                Text(
                                    text = handle.url,
                                    fontFamily = FontFamily(Font(R.font.poppinsregular)),
                                    color = TextSecondary,
                                    fontSize = urlFontSize.value.sp
                                )
                            }
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(verticalPadding)) }
            }
        }

        if (isAdmin) {
            FloatingActionButton(
                onClick = { onEditClick?.invoke() },
                containerColor = AccentBlue,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(fabPadding)
                    .size(fabSize)
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit", tint = TextPrimary)
            }
        }
    }
}
