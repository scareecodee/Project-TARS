package com.example.projecttars.Admin.Projects.Completed

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.CompletedProjectDetail
import com.example.projecttars.Members.Projects.Completed.CompletedProjectDetailScreen

@Composable
fun AdminCompletedProjectDetail(
    project: CompletedProjectDetail,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
){
    CompletedProjectDetailScreen(
       project = project,
        onBackClick=onBackClick,
        onDeleteClick=onDeleteClick,
        onEditClick=onEditClick,
        isAdmin=true
    )
}