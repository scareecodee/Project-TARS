package com.example.projecttars.Admin.TarsMembers

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.MemberDetail
import com.example.projecttars.Members.TarsMembers.MemberDetailScreen

@Composable
fun AdminTarsMemberDetail(
    member: MemberDetail,
    onBackClick: () -> Unit,
    onDeleteClick: (() -> Unit)? = null,
    onEditClick: (() -> Unit)? = null
){
    MemberDetailScreen(
        member = member,
        onBackClick = onBackClick,
        isAdmin = true,
        onDeleteClick = onDeleteClick,
        onEditClick = onEditClick
    )
}