package com.example.projecttars.Admin.Projects.Ongoing

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.OngoingProjectDetail
import com.example.projecttars.Members.Projects.Ongoing.OngoingProjectsScreen
import com.example.projecttars.ViewModels.Firebase.OngoingProjectVM

@Composable
fun AdminOngoingProjectScreen(
    onViewDetail: (OngoingProjectDetail) -> Unit,
    onBack: () -> Unit,
    onAddProjectClick: (() -> Unit)? = null,
    ongoingProjectVM: OngoingProjectVM
){
    OngoingProjectsScreen(
        onViewDetail = onViewDetail,
        onBack = onBack,
        isAdmin = true,
        onAddProjectClick = onAddProjectClick,
        ongoingProjectVM = ongoingProjectVM
        )

}