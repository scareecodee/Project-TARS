package com.example.projecttars.Admin.Projects.Completed

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.Project
import com.example.projecttars.Members.Projects.Completed.CompletedProjectsScreen

@Composable
fun AdminCompletedProjectsScreen(
    projects: List<Project>,
    onViewDetail: (Project) -> Unit,
    onBack: () -> Unit,
    onAddProject: () -> Unit
) {
    CompletedProjectsScreen(
        projects = projects,
        onViewDetail = onViewDetail,
        onBack = onBack,
        isAdmin = true,
        onAddClick = onAddProject
    )
}
