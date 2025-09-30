package com.example.projecttars.Admin.Achievements

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.Achievement
import com.example.projecttars.Members.Projects.Completed.AchievementScreen

@Composable
fun AdminAchievementsScreen(
    achievements: List<Achievement>,
    onViewDetail: (Achievement) -> Unit,
    onBack: () -> Unit,
    onAddAchievement: () -> Unit = {}
){
    AchievementScreen(
        achievements = achievements,
        onViewDetail = onViewDetail,
        onBack = onBack,
        isAdmin = true,
        onAddAchievement = onAddAchievement
    )
}