package com.example.projecttars.Admin.Achievements

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.Achievement
import com.example.projecttars.Members.Achievements.AchievementDetailScreen

@Composable
fun AdminAchievementDetail(
    achievement: Achievement,
    onBackClick: () -> Unit,
    onDeleteClick: (() -> Unit)? = null,
    onEditClick: (() -> Unit)? = null
){
    AchievementDetailScreen(
        achievement = achievement,
        onBackClick = onBackClick,
        isAdmin = true,
        onDeleteClick = onDeleteClick,
        onEditClick = onEditClick
    )
}