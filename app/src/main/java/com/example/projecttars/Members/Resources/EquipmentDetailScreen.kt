package com.example.projecttars.Members.Resources

import androidx.compose.runtime.Composable
import com.example.projecttars.Admin.Resources.AdminResDetailScreen
import com.example.projecttars.DataModels.TarsLabComponent
@Composable
fun EquipmentDetailScreen(
    equipment: TarsLabComponent,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
) {
    AdminResDetailScreen(
        equipment = equipment,
        onBackClick = onBackClick,
        onDeleteClick = onDeleteClick,
        onEditClick = onEditClick,
        isAdmin = false
    )
}
