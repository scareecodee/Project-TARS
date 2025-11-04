package com.example.projecttars.Members.Profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.DataModels.NotificationItem
import com.example.projecttars.R
import com.example.projecttars.ViewModels.Firebase.NotificationVM
import com.example.projecttars.ui.theme.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun NotificationScreen(
    onBackClick: () -> Unit,
    isAdmin: Boolean = false,
    onSendNotificationClick: (() -> Unit)? = null,
    notificationVM: NotificationVM
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    val paddingHorizontal = (screenWidth * 0.04).dp
    val paddingVertical = (screenHeight * 0.01).dp
    val spacerMedium = (screenHeight * 0.02).dp
    val fabPadding = (screenWidth * 0.05).dp
    val textSizeTitle = (screenWidth * 0.055).sp
    val notifications by  notificationVM.notifications.collectAsState()

    LaunchedEffect(Unit) {
        notificationVM.observeNotifications()
    }
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
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = AccentBlue)
                }
                Spacer(modifier = Modifier.width(paddingHorizontal))
                Text(
                    text = "Notifications",
                    color = AccentBlue,
                    fontSize = (screenWidth * 0.07).sp,
                    fontFamily = FontFamily(Font(R.font.poppinsbold))
                )
            }


            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = fabPadding)
            ) {
                if(notifications.isEmpty()){
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "No Notifications Found",
                                fontSize =textSizeTitle,
                                color = Color.White,
                                fontFamily = FontFamily(Font(R.font.poppinsmedium))
                            )
                        }
                    }
                }
                else{
                    items(notifications.size) { index ->
                        val item = notifications[index]

                        NotificationCard(
                            notificationItem = item,
                            notificationVM = notificationVM,
                            isAdmin=isAdmin
                        )
                    }
                    item { Spacer(modifier = Modifier.height(spacerMedium)) }
                }
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

@Composable
fun NotificationCard(
    notificationItem: NotificationItem,
    notificationVM: NotificationVM,
    isAdmin: Boolean = false
){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    val paddingHorizontal = (screenWidth * 0.04).dp
    val iconSize = (screenWidth * 0.1).dp
    val spacerSmall = (screenHeight * 0.01).dp
    val textSizeTitle = (screenWidth * 0.042).sp
    val textSizeDescription = (screenWidth * 0.035).sp
    val textSizeDate = (screenWidth * 0.03).sp
    val cardCorner = (screenWidth * 0.04).dp
    var showDeleteDialog by remember { mutableStateOf(false) }
    val context= LocalContext.current
    Card(
        colors = CardDefaults.cardColors(containerColor = DarkSlate),
        shape = RoundedCornerShape(cardCorner),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = paddingHorizontal, vertical = spacerSmall),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(paddingHorizontal)
        ) {

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

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = notificationItem.title,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                    color = TextPrimary,
                    fontSize = textSizeTitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(spacerSmall))
                Text(
                    text = notificationItem.description,
                    fontFamily = FontFamily(Font(R.font.poppinsregular)),
                    color = TextSecondary,
                    fontSize = textSizeDescription
                )
                Spacer(modifier = Modifier.height(spacerSmall))
                Text(
                    text = formatTimestampToDateTime(notificationItem.timestamp),
                    fontFamily = FontFamily(Font(R.font.poppinsitalic)),
                    color = TextSecondary,
                    fontSize = textSizeDate
                )
            }
            Spacer(modifier = Modifier.weight(0.20f))

            if(isAdmin){
                IconButton(onClick = { showDeleteDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Admin",
                        tint = AccentOrange
                    )
                }
            }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete ${notificationItem.title}", color = TextPrimary,fontSize = 17.sp,
                fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium))) },
            text = { Text("Are you sure you want to delete ${notificationItem.title}?", color = TextSecondary,       fontSize = 12.sp,
                fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium))) },
            confirmButton = {
                TextButton(
                    onClick = {
                       notificationVM.deleteNotification(
                           notificationItem.id,
                           onResult = {result->
                               if(result){
                                   Toast.makeText(context,"Notification deleted successfully",Toast.LENGTH_SHORT).show()
                               }else{
                                   Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
                               }

                           }
                       )
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


fun formatTimestampToDateTime(timestamp: Long): String {
    val date = java.util.Date(timestamp)
    val formatter = java.text.SimpleDateFormat("dd MMM yyyy, hh:mm a", java.util.Locale.getDefault())
    return formatter.format(date)
}
