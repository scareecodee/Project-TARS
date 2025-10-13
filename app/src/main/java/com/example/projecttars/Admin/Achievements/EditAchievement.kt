package com.example.projecttars.Admin.Achievements

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.Achievement
import com.example.projecttars.ViewModels.NavigationData.AchievementsNavVM

@Composable
fun EditAchievement(
    onBackClick: () -> Unit,
    onSaveClick: (Achievement) -> Unit,
    achievementsNavVM: AchievementsNavVM
){
    AddAchievementScreen(
        heading = "Edit Achievement",
        onBackClick = onBackClick,
        onSaveClick = onSaveClick,
        achievementsNavVM = achievementsNavVM
    )
}