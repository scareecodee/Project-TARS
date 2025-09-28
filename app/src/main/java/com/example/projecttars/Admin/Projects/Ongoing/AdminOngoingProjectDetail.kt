package com.example.projecttars.Admin.Projects.Ongoing

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.OngoingProjectDetail
import com.example.projecttars.Members.Projects.Ongoing.OngoingProjectDetailScreen

@Composable
fun AdminOngoingProjectDetail(
    project: OngoingProjectDetail,
    onBackClick: () -> Unit,
    onEditClick: (() -> Unit)? = null,
    onDeleteClick: (() -> Unit)? = null
){
    OngoingProjectDetailScreen(
        project = project,
        onBackClick = onBackClick,
        isAdmin = true,
        onEditClick = onEditClick,
        onDeleteClick = onDeleteClick
    )
}