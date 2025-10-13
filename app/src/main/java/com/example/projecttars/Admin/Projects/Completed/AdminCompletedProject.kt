package com.example.projecttars.Admin.Projects.Completed

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.CompletedProjectDetail
import com.example.projecttars.Members.Projects.Completed.CompletedProjectsScreen
import com.example.projecttars.ViewModels.Firebase.CompletedProjectVM

@Composable
fun AdminCompletedProjectsScreen(
    onViewDetail: (CompletedProjectDetail) -> Unit,
    onBack: () -> Unit,
    onAddProject: () -> Unit,
    completedProjectVM: CompletedProjectVM
) {
    CompletedProjectsScreen(
        onViewDetail = onViewDetail,
        onBack = onBack,
        isAdmin = true,
        onAddClick = onAddProject,
        completedProjectsVM = completedProjectVM
    )
}
