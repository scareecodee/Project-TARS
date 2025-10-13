package com.example.projecttars.Admin.TarsMembers

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.MemberDetail
import com.example.projecttars.Members.TarsMembers.TarsMembersScreen
import com.example.projecttars.ViewModels.Firebase.MembersVM

@Composable
fun AdminTarsMemberScreen(
    onViewDetail: (MemberDetail) -> Unit,
    onBack: () -> Unit,
    onAddMember: () -> Unit = {},
    membersVM: MembersVM
){
    TarsMembersScreen(
        onViewDetail = onViewDetail,
        onBack = onBack,
        isAdmin = true,
        onAddMember = onAddMember,
        membersVM = membersVM
    )
}