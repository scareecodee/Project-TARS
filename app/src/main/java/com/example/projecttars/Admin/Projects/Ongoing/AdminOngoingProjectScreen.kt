package com.example.projecttars.Admin.Projects.Ongoing

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.Project
import com.example.projecttars.Members.Projects.Ongoing.OngoingProjectsScreen

@Composable
fun AdminOngoingProjectScreen(
    projects: List<Project>,
    onViewDetail: (Project) -> Unit,
    onBack: () -> Unit,
    onAddProjectClick: (() -> Unit)? = null
){
    OngoingProjectsScreen(
        projects = projects,
        onViewDetail = onViewDetail,
        onBack = onBack,
        isAdmin = true,
        onAddProjectClick = onAddProjectClick
        )

}