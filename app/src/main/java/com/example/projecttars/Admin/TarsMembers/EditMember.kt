package com.example.projecttars.Admin.TarsMembers

import androidx.compose.runtime.Composable
import com.example.projecttars.Admin.Members.AddTarsMemberScreen
import com.example.projecttars.DataModels.MemberDetail
import com.example.projecttars.ViewModels.NavigationData.MemberNavVM

@Composable
fun EditMember(
    onBackClick: () -> Unit,
    onSaveClick: (MemberDetail) -> Unit,
    memberNavVM: MemberNavVM
){
    AddTarsMemberScreen(
        heading = "Edit TARS Member",
        onBackClick = onBackClick,
        onSaveClick = onSaveClick,
        memberNavVM = memberNavVM
    )
}