package com.example.projecttars.Admin.Projects.Ongoing

import androidx.compose.runtime.Composable
import com.example.projecttars.Admin.Resources.AddOngoingProjectScreen
import com.example.projecttars.DataModels.OngoingProjectDetail
import com.example.projecttars.ViewModels.NavigationData.OngoingProjectNavVM

@Composable
fun EditOngoingProject(
    onBackClick: () -> Unit,
    onSaveClick: (OngoingProjectDetail) -> Unit,
    ongoingProjectNavVM: OngoingProjectNavVM
){
    AddOngoingProjectScreen(
        onBackClick = onBackClick,
        onSaveClick = onSaveClick,
        heading = "Edit Ongoing Project",
        ongoingProjectNavVM =ongoingProjectNavVM
    )
}