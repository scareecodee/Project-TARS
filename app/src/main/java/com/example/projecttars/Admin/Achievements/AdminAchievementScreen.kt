package com.example.projecttars.Admin.Achievements

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.Achievement
import com.example.projecttars.Members.Projects.Completed.AchievementScreen
import com.example.projecttars.ViewModels.Firebase.AchievementsVM

@Composable
fun AdminAchievementsScreen(
    onViewDetail: (Achievement) -> Unit,
    onBack: () -> Unit,
    onAddAchievement: () -> Unit = {},
    achievementsVM: AchievementsVM
){
    AchievementScreen(
        onViewDetail = onViewDetail,
        onBack = onBack,
        isAdmin = true,
        onAddAchievement = onAddAchievement,
        achievementsVM = achievementsVM
    )
}