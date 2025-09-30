package com.example.projecttars.Admin.Profile

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.NotificationItem
import com.example.projecttars.Members.Profile.NotificationScreen

@Composable
fun AdminNotificationScreen(
    notifications: List<NotificationItem>,
    onBackClick: () -> Unit,
    onSendNotificationClick: (() -> Unit)? = null
){
    NotificationScreen(
        notifications = notifications,
        onBackClick = onBackClick,
        isAdmin = true,
        onSendNotificationClick = onSendNotificationClick
    )
}