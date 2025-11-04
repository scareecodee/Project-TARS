package com.example.projecttars.Admin.Profile

import androidx.compose.runtime.Composable
import com.example.projecttars.Members.Profile.NotificationScreen
import com.example.projecttars.ViewModels.Firebase.NotificationVM

@Composable
fun AdminNotificationScreen(
    notificationVM: NotificationVM,
    onBackClick: () -> Unit,
    onSendNotificationClick: (() -> Unit)? = null
){
    NotificationScreen(
        onBackClick = onBackClick,
        isAdmin = true,
        onSendNotificationClick = onSendNotificationClick,
        notificationVM = notificationVM
    )
}