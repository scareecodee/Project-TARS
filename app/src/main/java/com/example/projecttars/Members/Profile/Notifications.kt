package com.example.projecttars.Members.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.DataModels.NotificationItem
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*

@Composable
fun NotificationScreen(
    notifications: List<NotificationItem>,
    onBackClick: () -> Unit,
    isAdmin: Boolean = false,
    onSendNotificationClick: (() -> Unit)? = null
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    val density = LocalDensity.current

    // Responsive sizes
    val paddingHorizontal = (screenWidth * 0.04).dp
    val paddingVertical = (screenHeight * 0.02).dp
    val iconSize = (screenWidth * 0.1).dp
    val spacerSmall = (screenHeight * 0.01).dp
    val spacerMedium = (screenHeight * 0.02).dp
    val textSizeTitle = (screenWidth * 0.045).sp
    val textSizeDescription = (screenWidth * 0.035).sp
    val textSizeDate = (screenWidth * 0.03).sp
    val cardCorner = (screenWidth * 0.04).dp
    val fabPadding = (screenWidth * 0.05).dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                }
                Spacer(modifier = Modifier.width(paddingHorizontal))
                Text(
                    text = "Notifications",
                    color = TextPrimary,
                    fontSize = (screenWidth * 0.07).sp,
                    fontFamily = FontFamily(Font(R.font.poppinsregular))
                )
            }

            Spacer(modifier = Modifier.height(spacerSmall))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = fabPadding)
            ) {
                items(notifications.size) { index ->
                    val item = notifications[index]

                    Card(
                        colors = CardDefaults.cardColors(containerColor = DarkSlate),
                        shape = RoundedCornerShape(cardCorner),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = paddingHorizontal, vertical = spacerSmall),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(paddingHorizontal)
                        ) {
                            // Icon with background
                            Box(
                                modifier = Modifier
                                    .size(iconSize)
                                    .background(
                                        AccentBlue.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(cardCorner)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Notifications,
                                    contentDescription = "Notification",
                                    tint = AccentBlue
                                )
                            }

                            Spacer(modifier = Modifier.width(paddingHorizontal))

                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = item.title,
                                    fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                                    color = TextPrimary,
                                    fontSize = textSizeTitle
                                )
                                Spacer(modifier = Modifier.height(spacerSmall))
                                Text(
                                    text = item.description,
                                    fontFamily = FontFamily(Font(R.font.poppinsregular)),
                                    color = TextSecondary,
                                    fontSize = textSizeDescription
                                )
                                Spacer(modifier = Modifier.height(spacerSmall))
                                Text(
                                    text = item.date,
                                    fontFamily = FontFamily(Font(R.font.poppinsitalic)),
                                    color = TextSecondary,
                                    fontSize = textSizeDate
                                )
                            }
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(spacerMedium)) }
            }
        }

        if (isAdmin) {
            FloatingActionButton(
                onClick = { onSendNotificationClick?.invoke() },
                containerColor = AccentBlue,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(fabPadding)
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Send Notification", tint = TextPrimary)
            }
        }
    }
}
