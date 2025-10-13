package com.example.projecttars.Admin.Projects.Completed

import androidx.compose.runtime.Composable
import com.example.projecttars.Admin.Resources.AddCompletedProjectScreen
import com.example.projecttars.DataModels.CompletedProjectDetail
import com.example.projecttars.ViewModels.NavigationData.CompletedProjectNavVM

@Composable
fun EditCompletedProjectScreen(
    onBackClick: () -> Unit,
    onSaveClick: (CompletedProjectDetail) -> Unit,
    completedProjectNavVM: CompletedProjectNavVM
){
    AddCompletedProjectScreen(
        heading="Edit Project",
        onBackClick = onBackClick,
        onSaveClick =onSaveClick,
        completedProjectNavVM = completedProjectNavVM
    )
}