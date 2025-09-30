package com.example.projecttars.Admin.TarsMembers

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.TarsMember
import com.example.projecttars.Members.TarsMembers.TarsMembersScreen

@Composable
fun AdminTarsMemberScreen(
    tarsMembers: List<TarsMember>,
    onViewDetail: (TarsMember) -> Unit,
    onBack: () -> Unit,
    onAddMember: () -> Unit = {}
){
    TarsMembersScreen(
        tarsMembers = tarsMembers,
        onViewDetail = onViewDetail,
        onBack = onBack,
        isAdmin = true,
        onAddMember = onAddMember
    )
}